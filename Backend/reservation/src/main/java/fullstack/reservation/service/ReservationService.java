package fullstack.reservation.service;

import fullstack.reservation.domain.*;
import fullstack.reservation.domain.Enum.OrderType;
import fullstack.reservation.domain.Enum.SeatStatus;
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

    //사용중인 이용권이 있을 경우
    @Transactional
    public Reservation reservation(Long userId, int seatNumber) {
        User findUser = userRepository.findById(userId).orElse(null);
        Seat findSeat = seatRepository.findBySeatNumber(seatNumber);
        //해당 사용자의 사용중인 이용권 조회

        if (findUser.getTicketUser().getExpiredDate().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("이용권이 없습니다.");
        }

        List<Reservation> reservations = retrieveByUserId(userId);

        for (Reservation r : reservations) {
            if (r.getExitDate() == null) {
                throw new IllegalStateException("아직 퇴실하지 않는 정보가 있습니다.");
            }
        }
        
//        //사용중인 이용권의 기간이 만료가 되었는지
//        if (LocalDateTime.now().isAfter(order.getExpireDate())) {
//            order.changOrderStatus(OrderStatus.UNAVAILABLE);
//            throw new IllegalStateException("이용권이 만료되었습니다.");
//        }

        //좌석 상태
        if (findSeat.getSeatStatus() == SeatStatus.UNAVAILABLE) {
            throw new IllegalStateException("이미 좌석이 사용중입니다.");
        }

        Reservation reservation = Reservation.builder()
                .user(findUser)
                .seat(findSeat)
                .enterDate(LocalDateTime.now())
                .build();
        
        //예약 좌석 이용불가로 변경
        findSeat.changSeatStatus(SeatStatus.UNAVAILABLE);

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

    @Transactional
    public void exit(Long userId) {
        List<Reservation> reservations = retrieveByUserId(userId);

        for (Reservation r : reservations) {
            if (r.getExitDate() == null) {
                r.changeExitDate(LocalDateTime.now());
                r.getSeat().changSeatStatus(SeatStatus.AVAILABLE);
            }
        }
    }

}
