package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.SeatStatus;
import fullstack.reservation.domain.Seat;
import fullstack.reservation.repository.ReservationRepository;
import fullstack.reservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    public Seat register(Seat seat) {
        return seatRepository.save(seat);
    }

    public Seat retrieveOne(Long id) {
        return seatRepository.findById(id).orElse(null);
    }

    public List<Seat> retrieveAll() {
        return seatRepository.findAll();
    }
}
