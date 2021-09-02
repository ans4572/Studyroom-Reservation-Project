package fullstack.reservation.service;

import fullstack.reservation.domain.User;
import fullstack.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User loginService(String loginId, String password) {
        List<User> findLoginIdList = userRepository.findByLoginId(loginId);
        User tmp = null;

        for (User user : findLoginIdList) {
            if (user.getPassword().equals(password)) {
                tmp = user;
                break;
            }
        }
        return tmp;
    }
}
