package fullstack.reservation.repository;

import fullstack.reservation.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Seat findBySeatNumber(int seatNumber);
}
