package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.Gender;
import fullstack.reservation.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void createUser() {
        User user = User.builder()
                .name("김상운")
                .age(24)
                .gender(Gender.MALE)
                .loginId("issiscv")
                .password("369369rt")
                .phoneNumber("01054866730")
                .build();

        userService.join(user);
    }
}