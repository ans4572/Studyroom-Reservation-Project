package fullstack.reservation.service;

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
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void order() {
        User user = User.builder()
                .name("김상운")
                .build();

        Item day = Item.builder()
                .ticket(Ticket.DAY)
                .price(10000)
                .build();
        Item month = Item.builder()
                .ticket(Ticket.MONTH)
                .price(200000)
                .build();

        userRepository.save(user);
        itemRepository.save(day);
        itemRepository.save(month);

        Order order = orderService.order(user.getId(), Ticket.DAY);
        System.out.println(order.getId() + " " + order.getOrderStatus() + " " );

        Assertions.assertThat(order.getItem()).isEqualTo(day);
    }
}