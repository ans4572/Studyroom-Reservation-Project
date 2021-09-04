package fullstack.reservation.init;

import fullstack.reservation.domain.Enum.Ticket;
import fullstack.reservation.domain.Item;
import fullstack.reservation.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class InitService {

    private final ItemService itemService;
//
//    @PostConstruct
//    public void postInit() {
//        Item day = Item.builder()
//                .ticket(Ticket.DAY)
//                .price(10000)
//                .build();
//
//        Item month = Item.builder()
//                .ticket(Ticket.MONTH)
//                .price(100000)
//                .build();
//        itemService.register(day);
//        itemService.register(month);
//    }
}
