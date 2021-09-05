package fullstack.reservation.repository;

import fullstack.reservation.domain.Enum.Ticket;
import fullstack.reservation.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.ticket = :ticket")
    Item findByTicketType(@Param("ticket")Ticket ticket);

}
