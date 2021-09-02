package fullstack.reservation.controller;

import fullstack.reservation.domain.User;
import fullstack.reservation.dto.LoginDto;
import fullstack.reservation.exception.LoginFailedException;
import fullstack.reservation.service.LoginService;
import fullstack.reservation.service.UserService;
import fullstack.reservation.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    //로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto, HttpServletRequest request) {
        User user = loginService.loginService(loginDto.getLoginId(), loginDto.getPassword());

        if (user == null) {
            throw new LoginFailedException("로그인이 실패하였습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, user);

        EntityModel model = EntityModel.of(loginDto);
        WebMvcLinkBuilder self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).login(loginDto, request));

        model.add(self.withSelfRel());
        model.add(Link.of("http://localhost:8080/reservation", "reservationPage"));
        return ResponseEntity.ok(model);
    }

    @GetMapping("/login/test")
    public ResponseEntity test(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        return new ResponseEntity(user, HttpStatus.OK);
    }
}
