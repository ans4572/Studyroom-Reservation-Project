package fullstack.reservation.domain;

import fullstack.reservation.domain.Enum.HourType;
import fullstack.reservation.domain.Enum.Ticket;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    //(2,4,6,8)시간, 일일권, 1주권, 한달권,
    @Enumerated(EnumType.STRING)
    private Ticket ticket;

    //시간제 사용 시 에만 2, 4, 6, 8시간
    @Enumerated(EnumType.STRING)
    private HourType hourType;

    private int price;
}
