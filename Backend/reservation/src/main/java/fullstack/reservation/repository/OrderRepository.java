package fullstack.reservation.repository;

import fullstack.reservation.domain.Enum.OrderType;
import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

}
