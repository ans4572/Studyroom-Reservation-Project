package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.OrderType;
import fullstack.reservation.domain.Enum.Ticket;
import fullstack.reservation.domain.Item;
import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.TicketUser;
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
    public Order order(Long userId, Ticket ticket) {
        User findUser = userRepository.findById(userId).orElse(null);
        TicketUser ticketUser = findUser.getTicketUser();

        if (ticket == Ticket.DAY) {
            //이용권 기간이 만료되었으면
            if (ticketUser.getExpiredDate().isBefore(LocalDateTime.now())) {
                ticketUser.changeExpireDate(LocalDateTime.now().plusDays(1));
            } else {
                ticketUser.changeExpireDate(ticketUser.getExpiredDate().plusDays(1));
            }
        } else if (ticket == Ticket.MONTH) {
            //이용권 기간이 만료되었으면
            if (ticketUser.getExpiredDate().isBefore(LocalDateTime.now())) {
                ticketUser.changeExpireDate(LocalDateTime.now().plusMonths(1));
            } else {
                ticketUser.changeExpireDate(ticketUser.getExpiredDate().plusMonths(1));
            }
        }

        Item findItem = itemRepository.findByTicketType(ticket);

        Order order = Order.builder()
                .user(findUser)
                .item(findItem)
                .orderDate(LocalDateTime.now())
                .build();
        if (ticket == Ticket.DAY) {
            order.changeOrderType(OrderType.DAY);
        } else {
            order.changeOrderType(OrderType.MONTH);
        }

        return orderRepository.save(order);
    }

    public Order retrieveOne(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> retrieveAll() {
        return orderRepository.findAll();
    }
}
