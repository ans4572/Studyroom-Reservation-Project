package fullstack.reservation.service;

import fullstack.reservation.domain.*;
import fullstack.reservation.domain.Enum.Gender;
import fullstack.reservation.domain.Enum.OrderStatus;
import fullstack.reservation.domain.Enum.SeatStatus;
import fullstack.reservation.domain.Enum.Ticket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ReservationServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private SeatService seatService;

    @Test
    void 주문() {
        User user = User.builder()
                .name("김상운")
                .gender(Gender.MALE)
                .age(24)
                .build();

        Item item = Item.builder()
                .ticket(Ticket.MONTH)
                .price(10000)
                .build();

        Seat seat = Seat.builder()
                .seatNumber(1)
                .seatStatus(SeatStatus.AVAILABLE)
                .build();

        User saveUser = userService.join(user);
        Item registerItem = itemService.register(item);
        Seat registerSeat = seatService.register(seat);
        
        //이용권 구매
        Order order = orderService.order(saveUser.getId(), registerItem.getId());
        
        //예약
        //예약 시 이용권 상태 = UNAVAILABLE, 좌석 상태 = AVAILABLE
        Reservation reservation = reservationService.reservation(saveUser.getId(), registerSeat.getId(), order.getId());


        assertThat(reservation.getUser()).isEqualTo(saveUser);
        assertThat(reservation.getSeat()).isEqualTo(registerSeat);
        assertThat(reservation.getOrder()).isEqualTo(order);
        assertThat(registerSeat.getSeatStatus()).isEqualTo(SeatStatus.UNAVAILABLE);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.UNAVAILABLE);

        System.out.println(reservation.getEnterDate());
    }

    @Test
    void 주문_실패() {
        User user = User.builder()
                .name("김상운")
                .gender(Gender.MALE)
                .age(24)
                .build();

        User user2 = User.builder()
                .name("홍길동")
                .gender(Gender.MALE)
                .age(24)
                .build();

        Item item = Item.builder()
                .ticket(Ticket.MONTH)
                .price(10000)
                .build();

        Seat seat = Seat.builder()
                .seatNumber(1)
                .seatStatus(SeatStatus.AVAILABLE)
                .build();

        Seat seat2 = Seat.builder()
                .seatNumber(2)
                .seatStatus(SeatStatus.AVAILABLE)
                .build();

        User saveUser = userService.join(user);
        User saveUser2 = userService.join(user2);
        Item registerItem = itemService.register(item);
        Seat registerSeat = seatService.register(seat);
        Seat registerSeat2 = seatService.register(seat2);

        //이용권 구매
        Order order = orderService.order(saveUser.getId(), registerItem.getId());
        Order order2 = orderService.order(saveUser.getId(), registerItem.getId());

        //예약
        //예약 시 이용권 상태 = UNAVAILABLE, 좌석 상태 = AVAILABLE
        Reservation reservation = reservationService.reservation(saveUser.getId(), registerSeat.getId(), order.getId());
        Reservation reservation2 = reservationService.reservation(saveUser2.getId(), registerSeat2.getId(), order2.getId());

        List<Reservation> reservations = reservationService.retrieveAll();
        List<Order> orders = orderService.retrieveAll();

        assertThat(reservations.size()).isEqualTo(2);
        assertThat(orders.size()).isEqualTo(2);
    }
}