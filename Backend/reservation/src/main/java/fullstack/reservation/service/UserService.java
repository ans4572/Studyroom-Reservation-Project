package fullstack.reservation.service;

import fullstack.reservation.domain.User;
import fullstack.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    //가입
    @Transactional
    public User join(User user) {
        return userRepository.save(user);
    }
    
    //단건 조회
    public User retrieveOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    //전체 조회
    public List<User> retrieveAll() {
        return userRepository.findAll();
    }
}
