package fullstack.reservation.controller;

import fullstack.reservation.domain.User;
import fullstack.reservation.dto.CreateUserDto;
import fullstack.reservation.dto.LoginDto;
import fullstack.reservation.exception.LoginFailedException;
import fullstack.reservation.service.LoginService;
import fullstack.reservation.service.UserService;
import fullstack.reservation.session.SessionConst;
import fullstack.reservation.vo.Message;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

        //uri
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        //hateoas
        EntityModel model = EntityModel.of(createUserDto);
        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).createUser(createUserDto));

        //자기 자신
        model.add(self.withSelfRel());
        //로그인 페이지 전이
        model.add(Link.of("http://localhost:8080/users/login", "loginPage"));

        return ResponseEntity.created(uri).body(model);
    }

    //로그인
    @PostMapping("/users/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto, HttpServletRequest request) {
        User user = loginService.loginService(loginDto.getLoginId(), loginDto.getPassword());

        if (user == null) {
            throw new LoginFailedException("로그인이 실패하였습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, user);

        EntityModel model = EntityModel.of(new Message("로그인에 성공하였습니다."));
        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).login(loginDto, request));
        WebMvcLinkBuilder logoutLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).logout(request));

        //self
        model.add(self.withSelfRel());
        //reservation
        model.add(Link.of("http://localhost:8080/reservation", "reservationPage"));
        //logout
        model.add(logoutLink.withRel("logout"));

        return ResponseEntity.ok(model);
    }
    
    //로그아웃
    @DeleteMapping("/users/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        Message message = new Message("successfully logout");
        EntityModel model = EntityModel.of(message);
        //self
        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).logout(request));

        model.add(self.withSelfRel());
        //login
        model.add(Link.of("http://localhost:8080/users/login", "login"));

        return ResponseEntity.ok().body(model);
    }
    
    //테스트
    @GetMapping("/login/test")
    public ResponseEntity test(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        return ResponseEntity.ok().body(user);
    }
}
