package fullstack.reservation.service;

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
    @Autowired
    private ItemService itemService;

    @Test
    void reservation() {
        User user1 = User.builder()
                .name("김상운")
                .age(24)
                .build();
        User user2 = User.builder()
                .name("김민지")
                .age(27)
                .build();

        userService.join(user1);
        userService.join(user2);

        Item day = Item.builder()
                .ticket(Ticket.DAY)
                .price(10000)
                .build();

        Item month = Item.builder()
                .ticket(Ticket.MONTH)
                .price(200000)
                .build();

        itemService.register(day);
        itemService.register(month);

        //1번 유저가 일일권 구매
        Order order1 = orderService.order(user1.getId(), Ticket.DAY);
        //2번 유저가 한달권 구매
        Order order2 = orderService.order(user2.getId(), Ticket.MONTH);


        reservationService.reservation(user1.getId(), 1, order1.getId());
        reservationService.exit(user1.getId());

        if (reservationService.isExistOnUsing(user1.getId())) {
            reservationService.reservation(user1.getId(), 1);
            System.out.println("사용중인 이용권이 있을 때");
        } else {
            reservationService.reservation(user1.getId(), 1, order1.getId());
            System.out.println("사용중인 이용권이 없을 때");
        }





        //퇴실하지 않은 상태에서 이용 시 에러던짐
        //reservationService.reservation(user1.getId(), 3, order1.getId());
    }
}