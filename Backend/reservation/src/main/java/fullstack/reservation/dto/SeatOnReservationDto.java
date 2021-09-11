package fullstack.reservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullstack.reservation.domain.Enum.Gender;
import fullstack.reservation.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatOnReservationDto {

    @JsonIgnore
    private User user;

    private String name;

    private Gender gender;

    private int seatNumber;

    private LocalDateTime enterDate;

}
