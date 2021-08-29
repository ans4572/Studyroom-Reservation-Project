package fullstack.reservation.service;

import fullstack.reservation.domain.Enum.Gender;
import fullstack.reservation.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void 회원가입() {
        User user = User.builder()
                .name("김상운")
                .age(24)
                .gender(Gender.MALE)
                .build();

        User saveUser = userService.join(user);
        User findUser = userService.retrieveOne(saveUser.getId());

        Assertions.assertThat(saveUser).isEqualTo(findUser);
    }
}