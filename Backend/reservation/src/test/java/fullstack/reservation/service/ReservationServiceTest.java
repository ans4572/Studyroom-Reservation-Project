package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.Gender;
import fullstack.reservation.domain.Enum.Ticket;
import fullstack.reservation.domain.Item;
import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ReservationServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ReservationService reservationService;

    @Test
    void reservation() throws InterruptedException {
        User user1 = User.builder()
                .name("김상운")
                .age(24)
                .gender(Gender.MALE)
                .loginId("issiscv")
                .password("369369rt")
                .phoneNumber("01054866730")
                .build();
        
        User user2 = User.builder()
                .name("김민지")
                .age(24)
                .gender(Gender.MALE)
                .loginId("애댕이123")
                .password("새댕이123")
                .phoneNumber("01051580598")
                .build();

        userService.join(user1);
        userService.join(user2);

        //1번 유저가 일일권 구매
        Order order1 = orderService.order(user1.getId(), Ticket.DAY);
        //2번 유저가 한달권 구매
        Order order2 = orderService.order(user2.getId(), Ticket.MONTH);
        orderService.order(user2.getId(), Ticket.MONTH);
        orderService.order(user2.getId(), Ticket.MONTH);
        orderService.order(user2.getId(), Ticket.MONTH);

        reservationService.reservation(user1.getId(), 1);
        Thread.sleep(2000);
        reservationService.exit(user1.getId());
    }
}