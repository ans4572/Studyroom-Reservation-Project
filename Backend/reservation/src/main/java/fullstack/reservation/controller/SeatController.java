package fullstack.reservation.controller;

import fullstack.reservation.domain.Reservation;
import fullstack.reservation.dto.SeatOnReservationDto;
import fullstack.reservation.service.ReservationService;
import fullstack.reservation.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SeatController {

    private final ReservationService reservationService;
    
    //사용중인 좌석 찾기
    @GetMapping("/seats")
    public ResponseEntity getAllSeat() {

        List<Reservation> seatOnReservation = reservationService.findSeatOnReservation();

        List<SeatOnReservationDto> collect = seatOnReservation.stream().map(r -> new SeatOnReservationDto(r.getUser().getName(), r.getUser().getGender(),
                r.getSeat().getSeatNumber(), r.getEnterDate())).collect(Collectors.toList());

        //주문 내역, 예약 내역

        return ResponseEntity.ok(collect);
    }
}
