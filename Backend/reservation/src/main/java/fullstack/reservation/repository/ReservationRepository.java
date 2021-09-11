package fullstack.reservation.repository;

import fullstack.reservation.domain.Enum.SeatStatus;
import fullstack.reservation.domain.Reservation;
import fullstack.reservation.dto.SeatOnReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r join fetch r.user u join fetch r.seat s where u.id = :id")
    List<Reservation> findReservationByUserId(@Param("id") Long id);

    @Query("select r from Reservation r join fetch r.user u join fetch r.seat s where s.seatStatus = :seatStatus")
    List<Reservation> findReservationJoinSeatBySeatStatus(@Param("seatStatus") SeatStatus seatStatus);
}
