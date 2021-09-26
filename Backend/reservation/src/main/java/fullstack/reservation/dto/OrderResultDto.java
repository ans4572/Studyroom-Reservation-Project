package fullstack.reservation.dto;

import fullstack.reservation.domain.Enum.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResultDto {
    //이름 가격 날짜 이용권 종류, 이용자의 이용 가능 시간
    private String name;
    private int price;
    private Ticket ticket;
    private LocalDateTime orderDate;

    public OrderResultDto(int price, Ticket ticket, LocalDateTime orderDate) {
        this.price = price;
        this.ticket = ticket;
        this.orderDate = orderDate;
    }
}
