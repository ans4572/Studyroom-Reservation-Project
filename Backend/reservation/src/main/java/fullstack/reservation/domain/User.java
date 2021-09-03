package fullstack.reservation.domain;

import fullstack.reservation.domain.Enum.Gender;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private String loginId;

    private String password;

    private String phoneNumber;
}

