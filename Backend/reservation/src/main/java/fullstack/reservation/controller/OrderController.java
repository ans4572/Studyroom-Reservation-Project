package fullstack.reservation.controller;

import fullstack.reservation.domain.User;
import fullstack.reservation.dto.OrderDto;
import fullstack.reservation.service.OrderService;
import fullstack.reservation.session.SessionConst;
import fullstack.reservation.vo.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity order(@RequestBody OrderDto orderDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        orderService.order(user.getId(), orderDto.getTicket());

        return new ResponseEntity(new Message("성공"), HttpStatus.CREATED);
    }
}
