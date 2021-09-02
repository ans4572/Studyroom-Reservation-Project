package fullstack.reservation.controller;

import fullstack.reservation.domain.User;
import fullstack.reservation.dto.CreateUserDto;
import fullstack.reservation.dto.LoginDto;
import fullstack.reservation.exception.LoginFailed;
import fullstack.reservation.service.LoginService;
import fullstack.reservation.service.UserService;
import fullstack.reservation.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final LoginService loginService;

    //회원 가입
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(createUserDto, User.class);
        User saveUser = userService.join(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        EntityModel model = EntityModel.of(createUserDto);
        model.add(Link.of("/login", "loginPage"));

        return ResponseEntity.created(uri).body(model);
    }

}
