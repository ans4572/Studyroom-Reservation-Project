package fullstack.reservation.controller;

import fullstack.reservation.domain.Order;
import fullstack.reservation.domain.User;
import fullstack.reservation.dto.OrderDto;
import fullstack.reservation.dto.OrderResultDto;
import fullstack.reservation.dto.OrderResultDtoV2;
import fullstack.reservation.dto.OrderResultPostDto;
import fullstack.reservation.service.OrderService;
import fullstack.reservation.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins ="*")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    
    //주문
    @PostMapping("/orders")
    public ResponseEntity order(@RequestBody OrderDto orderDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(SessionConst.LOGIN_MEMBER);

        Order order = orderService.order(user.getId(), orderDto.getTicket());

        OrderResultPostDto orderResultDto = OrderResultPostDto.builder()
                .ticket(order.getItem().getTicket())
                .name(order.getUser().getName())
                .price(order.getItem().getPrice())
                .orderDate(order.getOrderDate())
                .availableDate(order.getUser().getTicketUser().getExpiredDate())
                .build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        EntityModel model = EntityModel.of(orderResultDto);
        WebMvcLinkBuilder self = linkTo(methodOn(this.getClass()).order(orderDto, request));
        WebMvcLinkBuilder retrieveOne = linkTo(methodOn(this.getClass()).getOrder(order.getId()));

        model.add(self.withSelfRel());
        model.add(retrieveOne.withRel("retrieve"));

        return ResponseEntity.created(uri).body(model);
    }
    
    //단건 조회
    @GetMapping("/orders/{id}")
    public ResponseEntity getOrder(@PathVariable Long id) {
        Order order = orderService.retrieveOne(id);

        OrderResultDto orderResultDto = OrderResultDto.builder()
                .ticket(order.getItem().getTicket())
                .price(order.getItem().getPrice())
                .name(order.getUser().getName())
                .orderDate(order.getOrderDate())
                .build();

        EntityModel model = EntityModel.of(orderResultDto);
        WebMvcLinkBuilder self = linkTo(methodOn(this.getClass()).getOrder(id));

        model.add(self.withSelfRel());

        return ResponseEntity.ok().body(model);
    }
    
    //모두 조회
    @GetMapping("/orders")
    public ResponseEntity getAllOrder() {
        List<Order> orders = orderService.retrieveAll();
        List<EntityModel> list = new ArrayList<>();

        for (Order o : orders) {
            OrderResultDto orderResultDto = new OrderResultDto(o.getUser().getName(),
                    o.getItem().getPrice(), o.getItem().getTicket(), o.getOrderDate());
            EntityModel model = EntityModel.of(orderResultDto);
            WebMvcLinkBuilder retrieve = linkTo(methodOn(this.getClass()).getOrder(o.getId()));
            model.add(retrieve.withRel("retrieveOrder"));

            list.add(model);
        }

        return ResponseEntity.ok().body(list);
    }
}
