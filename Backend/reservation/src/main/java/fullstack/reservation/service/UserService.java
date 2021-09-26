package fullstack.reservation.service;

import fullstack.reservation.domain.TicketUser;
import fullstack.reservation.domain.User;
import fullstack.reservation.dto.EditUserDto;
import fullstack.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    //가입
    @Transactional
    public User join(User user) {
        TicketUser ticketUser = new TicketUser();
        ticketUser.changeExpireDate(LocalDateTime.now());
        user.setTicketUser(ticketUser);
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

    @Transactional
    public void editUser(Long userId, EditUserDto editUserDto) {
        User findUser = retrieveOne(userId);
        findUser.editUser(editUserDto);
    }
}
