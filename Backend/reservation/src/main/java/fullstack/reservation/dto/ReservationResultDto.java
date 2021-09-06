package fullstack.reservation.dto;

import fullstack.reservation.domain.Enum.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResultDto {

    private String name;
    private LocalDateTime reservationTime;
    private int seatNumber;
}
