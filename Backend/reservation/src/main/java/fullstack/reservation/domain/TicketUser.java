package fullstack.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketUser {

    @Id @GeneratedValue
    @Column(name = "ticket_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDateTime expiredDate;

    public void setUser(User user) {
        this.user = user;
    }

    public void changeExpireDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }
}
