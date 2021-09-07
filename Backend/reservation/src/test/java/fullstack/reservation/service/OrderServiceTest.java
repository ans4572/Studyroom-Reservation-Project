package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.Gender;
import fullstack.reservation.domain.Enum.Ticket;
import fullstack.reservation.domain.Item;
import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.User;
import fullstack.reservation.repository.ItemRepository;
import fullstack.reservation.repository.OrderRepository;
import fullstack.reservation.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService UserService;

    @Test
    void order() {
        User user = User.builder()
                .name("김상운")
                .age(24)
                .gender(Gender.MALE)
                .loginId("issiscv")
                .password("369369rt")
                .phoneNumber("01054866730")
                .build();


        User saveUser = UserService.join(user);

        Order order = orderService.order(saveUser.getId(), Ticket.DAY);
    }
}