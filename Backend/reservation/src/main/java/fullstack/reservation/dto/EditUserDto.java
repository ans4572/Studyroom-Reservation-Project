package fullstack.reservation.dto;

import fullstack.reservation.domain.Enum.Gender;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserDto {

    //이름 비밀번호 성별 나이
    private String name;

    private String password;

    private Gender gender;

    private int age;
}
