package fullstack.reservation.init;

import fullstack.reservation.domain.Enum.SeatStatus;
import fullstack.reservation.domain.Enum.Ticket;
import fullstack.reservation.domain.Item;
import fullstack.reservation.domain.Seat;
import fullstack.reservation.service.ItemService;
import fullstack.reservation.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class InitService {

    private final SeatService seatService;

    @PostConstruct
    public void postInit() {
        for (int i = 0; i < 45; i++) {
            Seat seat = Seat.builder()
                    .seatNumber(i + 1)
                    .seatStatus(SeatStatus.AVAILABLE)
                    .build();
            seatService.register(seat);
        }
    }
}
