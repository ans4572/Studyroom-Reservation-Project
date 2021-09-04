package fullstack.reservation.dto;

import fullstack.reservation.domain.Enum.Ticket;
import lombok.Data;

@Data
public class OrderDto {

    private Ticket ticket;
}
