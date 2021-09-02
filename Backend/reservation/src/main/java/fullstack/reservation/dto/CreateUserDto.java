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

    @NotBlank
    private String name;

    @NotNull
    private int age;

    @NotBlank
    private String gender;
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    
    @NotBlank
    private String phoneNumber;
}
