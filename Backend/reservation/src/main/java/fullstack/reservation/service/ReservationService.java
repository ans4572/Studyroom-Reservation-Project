package fullstack.reservation.service;

import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.Reservation;
import fullstack.reservation.domain.Seat;
import fullstack.reservation.domain.User;
import fullstack.reservation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;

    @Transactional
    public Reservation reservation(Long userId, Long seatId, Long orderId) {
        User findUser = userRepository.findById(userId).orElse(null);
        Seat findSeat = seatRepository.findById(seatId).orElse(null);
        Order findOrder = orderRepository.findById(orderId).orElse(null);

        Reservation reservation = Reservation.builder()
                .user(findUser)
                .seat(findSeat)
                .order(findOrder)
                .enterDate(LocalDateTime.now())
                .build();

        return reservation;
    }

    public Reservation retrieveOne(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> retrieveAll() {
        return reservationRepository.findAll();
    }
}
