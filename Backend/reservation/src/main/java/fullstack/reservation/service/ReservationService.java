package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.OrderStatus;
import fullstack.reservation.domain.Enum.SeatStatus;
import fullstack.reservation.domain.Enum.Ticket;
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

        List<Reservation> reservations = retrieveByUserId(userId);

        for (Reservation r : reservations) {
            if (r.getExitDate() == null) {
                throw new IllegalStateException("아직 퇴실하지 않는 정보가 있습니다.");
            }
        }

        if (findOrder.getOrderStatus() == OrderStatus.UNAVAILABLE) {
            throw new IllegalStateException("이미 사용한 사용권 입니다.");
        }
        if (findSeat.getSeatStatus() == SeatStatus.UNAVAILABLE) {
            throw new IllegalStateException("이미 좌석이 사용중입니다.");
        }

        Reservation reservation = Reservation.builder()
                .user(findUser)
                .seat(findSeat)
                .order(findOrder)
                .enterDate(LocalDateTime.now())
                .build();

        Ticket ticket = findOrder.getItem().getTicket();

        if (ticket == Ticket.MONTH) {
            reservation.changeExpireDate(reservation.getEnterDate().plusMonths(1));
        } else if (ticket == Ticket.DAY) {
            reservation.changeExpireDate(reservation.getEnterDate().plusDays(1));
        }

        findSeat.changSeatStatus(SeatStatus.UNAVAILABLE);
        findOrder.changOrderStatus(OrderStatus.UNAVAILABLE);

        return reservationRepository.save(reservation);
    }

    public Reservation retrieveOne(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> retrieveByUserId(Long userId) {
        return reservationRepository.findReservationByUserId(userId);
    }

    public List<Reservation> retrieveAll() {
        return reservationRepository.findAll();
    }

    public void exit(Long userId) {
        List<Reservation> reservations = retrieveByUserId(userId);

        for (Reservation r : reservations) {
            if (r.getExitDate() == null) {
                r.changeExitDate(LocalDateTime.now());
                r.getSeat().changSeatStatus(SeatStatus.AVAILABLE);
            }
        }
    }

    //test5
    //test6
}
