package fullstack.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResultV2 {
    //퇴실하지 않은
    private String name;
    private LocalDateTime reservationTime;
    private int seatNumber;
}
