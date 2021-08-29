package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.Gender;
import fullstack.reservation.domain.Enum.Ticket;
import fullstack.reservation.domain.Item;
import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;


    @Test
    void 주문() {
        User user = User.builder()
                .name("김상운")
                .gender(Gender.MALE)
                .age(24)
                .build();

        Item item = Item.builder()
                .ticket(Ticket.DAY)
                .price(10000)
                .build();

        User saveUser = userService.join(user);
        Item registerItem = itemService.register(item);

        Order order = orderService.order(saveUser.getId(), registerItem.getId());

        System.out.println(saveUser.getId());
        System.out.println(registerItem.getId());
        System.out.println(order.getId());

        Assertions.assertThat(order.getUser()).isEqualTo(saveUser);
        Assertions.assertThat(order.getItem()).isEqualTo(registerItem);
    }
}