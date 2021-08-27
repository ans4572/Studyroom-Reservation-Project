package fullstack.reservation.domain;

import fullstack.reservation.domain.Enum.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id @GeneratedValue
    @Column(name = "seat_id")
    private Long id;

    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
}
