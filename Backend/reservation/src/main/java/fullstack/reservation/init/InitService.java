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
    private final ItemService itemService;

    @PostConstruct
    public void postInit() {
        for (int i = 0; i < 10; i++) {
            Seat seat = Seat.builder()
                    .seatNumber(i + 1)
                    .seatStatus(SeatStatus.AVAILABLE)
                    .build();
            seatService.register(seat);
        }
        Item day = Item.builder()
                .price(10000)
                .ticket(Ticket.DAY)
                .build();
        Item month = Item.builder()
                .price(200000)
                .ticket(Ticket.MONTH)
                .build();
        itemService.register(day);
        itemService.register(month);
    }
}
