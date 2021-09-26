package fullstack.reservation.dto;

import fullstack.reservation.vo.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDto {
    
    //현재 사용중인지
    private boolean currentUsage;

    //이름
    private String name;
    
    //아이디
    private String loginId;

    //입실 시간
    private LocalDateTime enterDate;

    //현재 이용 시간
    private long usageTime;

    //이용권 만료일
    private LocalDateTime expireDate;
    
    //사용중인 좌석 정보
    private int seatNumber;
}
