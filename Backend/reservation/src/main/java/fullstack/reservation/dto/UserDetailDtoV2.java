package fullstack.reservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDtoV2 {
    
    //현재 사용중인지
    private boolean currentUsage;

    //이름
    private String name;
    
    //아이디
    private String loginId;

    //이용권 만료일
    private LocalDateTime expireDate;

}
