package fullstack.reservation.controller;

import fullstack.reservation.domain.Reservation;
import fullstack.reservation.domain.User;
import fullstack.reservation.dto.ReservationDto;
import fullstack.reservation.dto.ReservationResultDto;
import fullstack.reservation.service.ReservationService;
import fullstack.reservation.session.SessionConst;
import fullstack.reservation.vo.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
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
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    
    //입실 후 로그아웃
    @PostMapping("/reservation")
    public ResponseEntity reservation(@RequestBody ReservationDto reservationDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        Reservation reservation = reservationService.reservation(sessionUser.getId(), reservationDto.getSeatNumber());


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservation.getId())
                .toUri();

        ReservationResultDto reservationResultDto = ReservationResultDto.builder()
                .name(sessionUser.getName())
                .seatNumber(reservationDto.getSeatNumber())
                .reservationTime(reservation.getEnterDate())
                .build();

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.created(uri).body(reservationResultDto);
    }
    
    
    //퇴실
    @DeleteMapping("/reservation")
    public ResponseEntity leaving(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (session != null) {
            session.invalidate();
        }

        reservationService.exit(sessionUser.getId());

        Message message = new Message("퇴실이 완료되었습니다.");
        EntityModel model = EntityModel.of(message);
        //self
        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).leaving(request));

        model.add(self.withSelfRel());
        //login
        model.add(Link.of("http://localhost:8080/users/login", "login"));

        return ResponseEntity.ok().body(model);
    }
    
    //단건 조회
    @GetMapping("/reservation/{id}")
    public ResponseEntity getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.retrieveOne(id);
        ReservationResultDto reservationResultDto = ReservationResultDto.builder()
                .name(reservation.getUser().getName())
                .seatNumber(reservation.getSeat().getSeatNumber())
                .reservationTime(reservation.getEnterDate())
                .build();
        return new ResponseEntity(reservationResultDto, HttpStatus.OK);
    }
}
