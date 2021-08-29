package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.OrderStatus;
import fullstack.reservation.domain.Item;
import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.User;
import fullstack.reservation.repository.ItemRepository;
import fullstack.reservation.repository.OrderRepository;
import fullstack.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order order(Long userId, Long itemId) {
        User findUser = userRepository.findById(userId).orElse(null);
        Item findItem = itemRepository.findById(itemId).orElse(null);

        Order order = Order.builder()
                .user(findUser)
                .item(findItem)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.AVAILABLE)
                .build();

        return orderRepository.save(order);
    }

    public Order retrieveOne(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> retrieveAll() {
        return orderRepository.findAll();
    }
}
