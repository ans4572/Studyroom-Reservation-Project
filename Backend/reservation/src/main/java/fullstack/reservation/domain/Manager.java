package fullstack.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

    @Id @GeneratedValue
    @Column(name = "manager_id")
    private Long id;

    private String name;

    private String loginId;

    private String password;
}
