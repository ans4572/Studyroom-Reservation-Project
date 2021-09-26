package fullstack.reservation.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationStatus {

    //독서실 사용 여부(true, false), 사용중이라면 얼만큼 이용했는지
    private boolean status;
    

}
