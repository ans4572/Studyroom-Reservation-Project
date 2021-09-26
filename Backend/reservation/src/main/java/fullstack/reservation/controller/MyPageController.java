package fullstack.reservation.controller;

import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.Reservation;
import fullstack.reservation.domain.User;
import fullstack.reservation.dto.*;
import fullstack.reservation.service.OrderService;
import fullstack.reservation.service.ReservationService;
import fullstack.reservation.service.UserService;
import fullstack.reservation.session.SessionConst;
import fullstack.reservation.vo.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final ReservationService reservationService;
    private final OrderService orderService;

    @GetMapping("/my-page")
    public ResponseEntity myPage(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        User user = userService.retrieveOne(sessionUser.getId());

        List<Reservation> reservations = reservationService.retrieveByUserId(user.getId());

        Reservation userReservation = null;

        boolean status = false;

        for (Reservation r : reservations) {
            if (r.getExitDate() == null) {
                userReservation = r;
                status = true;
                break;
            }
        }

        if (userReservation == null) {
            return ResponseEntity.ok(new UserDetailDtoV2(false, user.getName(), user.getLoginId(),
                    user.getTicketUser().getExpiredDate()));
        }

        LocalDateTime enterDate = userReservation.getEnterDate();
        LocalDateTime currentDate = LocalDateTime.now();

        LocalDateTime expireDate = userReservation.getUser().getTicketUser().getExpiredDate();

        long between = ChronoUnit.MINUTES.between(enterDate, currentDate);

        UserDetailDto userDetailDto = UserDetailDto.builder()
                .currentUsage(true)
                .name(user.getName())
                .loginId(user.getLoginId())
                .enterDate(enterDate)
                .usageTime(between)
                .expireDate(expireDate)
                .seatNumber(userReservation.getSeat().getSeatNumber())
                .build();

        return ResponseEntity.ok(userDetailDto);
    }
    
    //구매내역
    @GetMapping("/my-page/orders")
    public ResponseEntity myOrders(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<Order> orders = orderService.retrieveByUserId(sessionUser.getId());

        List<OrderResultDto> collect = orders.stream().map(o -> new OrderResultDto(o.getUser().getName(),o.getItem().getPrice(), o.getItem().getTicket(), o.getOrderDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }
    
    //예약 내역
    @GetMapping("/my-page/reservations")
    public ResponseEntity myReservations(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<Reservation> reservations = reservationService.retrieveByUserId(sessionUser.getId());

        List<ReservationResultDto> list = new ArrayList<>();

        for (Reservation r : reservations) {
            if (r.getExitDate() != null) {
                ReservationResultDto reservationResultDto = new ReservationResultDto(r.getUser().getName(), r.getEnterDate(), r.getSeat().getSeatNumber(), r.getExitDate());
                list.add(reservationResultDto);
            }
        }

        return ResponseEntity.ok(list);
    }

    @PutMapping("/my-page")
    public ResponseEntity editUser(@RequestBody EditUserDto editUserDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User sessionUser = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        userService.editUser(sessionUser.getId(), editUserDto);

        return ResponseEntity.ok(editUserDto);
    }
}