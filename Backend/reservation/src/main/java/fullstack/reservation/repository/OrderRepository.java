package fullstack.reservation.repository;

import fullstack.reservation.domain.Enum.OrderStatus;
import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderStatusAndUser(OrderStatus orderStatus, User user);
}
