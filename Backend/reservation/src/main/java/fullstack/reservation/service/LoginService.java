package fullstack.reservation.service;

import fullstack.reservation.domain.User;
import fullstack.reservation.exception.DuplicatedLoginIdException;
import fullstack.reservation.exception.MissMatchedLoginIdException;
import fullstack.reservation.exception.MissMatchedPasswordException;
import fullstack.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final UserRepository userRepository;

    public User loginService(String loginId, String password) {
        User findUser = userRepository.findByLoginId(loginId);

        if (findUser == null) {
            throw new MissMatchedLoginIdException("아이디를 잘못 입력하셨습니다.");
        }

        if (findUser.getPassword().equals(password)) {
            return findUser;
        } else {
            throw new MissMatchedPasswordException("비밀번호가 틀렸습니다.");
        }
    }

    public void findDuplicatedLoginId(String loginId) {
        User findUser = userRepository.findByLoginId(loginId);

        if (findUser != null) {
            throw new DuplicatedLoginIdException("같은 아이디가 존재합니다.");
        }
    }

}
