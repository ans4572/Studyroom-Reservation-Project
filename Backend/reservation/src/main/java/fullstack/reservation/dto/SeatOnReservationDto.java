package fullstack.reservation.dto;

import fullstack.reservation.domain.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SeatOnReservationDto {

    private String name;

    private Gender gender;

    private int seatNumber;

    private LocalDateTime enterDate;

    public SeatOnReservationDto(String name, Gender gender, int seatNumber, LocalDateTime enterDate) {
        this.name = name;
        this.gender = gender;
        this.seatNumber = seatNumber;
        this.enterDate = enterDate;
    }
}
