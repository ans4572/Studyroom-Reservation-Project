package fullstack.reservation.domain;

import fullstack.reservation.domain.Enum.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id @GeneratedValue
    @Column(name = "seat_id")
    private Long id;

    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    public void changSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public void changeSeat(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
