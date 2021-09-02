package fullstack.reservation.dto;

import fullstack.reservation.domain.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotNull(message = "나이를 입력해주세요.")
    private int age;

    @NotBlank(message = "성별을 선택해주세요.")
    private String gender;
    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    
    @NotBlank(message = "핸드폰 번호를 입력해주세요.")
    private String phoneNumber;
}
