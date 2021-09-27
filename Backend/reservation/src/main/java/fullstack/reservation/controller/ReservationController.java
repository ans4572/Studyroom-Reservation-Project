package fullstack.reservation.controller;

import fullstack.reservation.domain.Reservation;
import fullstack.reservation.domain.User;
import fullstack.reservation.dto.*;
import fullstack.reservation.exception.NoReservationNowException;
import fullstack.reservation.service.ReservationService;
import fullstack.reservation.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    
    //예약 후 로그아웃
    @PostMapping("/reservations")
    public ResponseEntity reservation(@RequestBody ReservationDto reservationDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        Reservation reservation = reservationService.reservation(sessionUser.getId(), reservationDto.getSeatNumber());

        if (session != null) {
            session.invalidate();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservation.getId())
                .toUri();

        ReservationResultV2 reservationResultDto = ReservationResultV2.builder()
                .name(sessionUser.getName())
                .seatNumber(reservationDto.getSeatNumber())
                .reservationTime(reservation.getEnterDate())
                .build();

        EntityModel model = EntityModel.of(reservationResultDto);

        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).reservation(reservationDto, request));
        WebMvcLinkBuilder retrieve = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getReservation(reservation.getId()));

        model.add(self.withSelfRel());
        model.add(retrieve.withRel("retrieve"));

        return ResponseEntity.created(uri).body(model);
    }
    
    
    //퇴실
    @DeleteMapping("/reservations")
    public ResponseEntity leaving(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);



        Reservation exit = reservationService.exit(sessionUser.getId());

        if (exit == null) {
            throw new NoReservationNowException("현재 이용중이지 않습니다.");
        }

        ReservationResultDto reservationResultDto = ReservationResultDto.builder()
                .name(sessionUser.getName())
                .seatNumber(exit.getSeat().getSeatNumber())
                .reservationTime(exit.getEnterDate())
                .exitTime(exit.getExitDate()).build();

        if (session != null) {
            session.invalidate();
        }

        EntityModel model = EntityModel.of(reservationResultDto);
        //self
        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).leaving(request));
        WebMvcLinkBuilder login = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).login(new LoginDto(), request));
        model.add(self.withSelfRel());

        //login
        model.add(login.withRel("login"));

        return ResponseEntity.ok().body(model);
    }
    
    //단건 조회
    @GetMapping("/reservations/{id}")
    public ResponseEntity getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.retrieveOne(id);

        ReservationResultDto reservationResultDto = ReservationResultDto.builder()
                .name(reservation.getUser().getName())
                .seatNumber(reservation.getSeat().getSeatNumber())
                .reservationTime(reservation.getEnterDate())
                .exitTime(reservation.getExitDate())
                .build();

        EntityModel model = EntityModel.of(reservationResultDto);

        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getReservation(id));

        model.add(self.withSelfRel());

        return new ResponseEntity(model, HttpStatus.OK);
    }

    @GetMapping("/reservations")
    public ResponseEntity getAllReservation() {
        List<Reservation> reservations = reservationService.retrieveAll();
        List<EntityModel> list = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationResultDto reservationResultDto = ReservationResultDto.builder()
                    .name(reservation.getUser().getName())
                    .seatNumber(reservation.getSeat().getSeatNumber())
                    .reservationTime(reservation.getEnterDate())
                    .exitTime(reservation.getExitDate())
                    .build();

            WebMvcLinkBuilder retrieve = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getReservation(reservation.getId()));

            EntityModel model = EntityModel.of(reservationResultDto);
            model.add(retrieve.withRel("retrieve"));

            list.add(model);
        }

        return ResponseEntity.ok().body(list);
    }
    
    //좌석 이동
    @PutMapping("/reservations")//유저 정보, 예약 정보, 좌석 정보
    public ResponseEntity changeSeatOnReservation(@RequestBody ChangeSeatNumberDto changeSeatNumberDto,
                                                  HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);
        
        List<Reservation> reservations = reservationService.retrieveByUserId(sessionUser.getId());
        
        //유저가 현재 사용중인 예약
        Reservation tmp = null;

        for (Reservation reservation : reservations) {
            if (reservation.getExitDate() == null) {
                tmp = reservation;
                break;
            }
        }

        if (tmp == null) {
            throw new IllegalStateException("현재 사용중인 좌석이 없습니다.");
        }

        ChangeSeatResultDto changeSeatResultDto = ChangeSeatResultDto.builder()
                .name(sessionUser.getName())
                .reservationTime(tmp.getEnterDate())
                .prevSeatNumber(tmp.getSeat().getSeatNumber())
                .build();
        
        //좌석변경 서비스
        reservationService.changeSeat(tmp, changeSeatNumberDto.getSeatNumber());

        changeSeatResultDto.setCurrentSeatNumber(tmp.getSeat().getSeatNumber());

        //좌석 변경 후 로그아웃
        if (session!= null) {
            session.invalidate();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tmp.getSeat().getSeatNumber())
                .toUri();


        EntityModel model = EntityModel.of(changeSeatResultDto);

        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(this.getClass()).changeSeatOnReservation(changeSeatNumberDto, request));

        model.add(self.withSelfRel());

        return ResponseEntity.created(uri).body(model);
    }
}
