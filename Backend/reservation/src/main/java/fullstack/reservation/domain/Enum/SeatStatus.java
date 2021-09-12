package fullstack.reservation.domain.Enum;

import com.fasterxml.jackson.annotation.JsonProperty;

//좌석을 이용가능한지, 아닌지
public enum SeatStatus {

    @JsonProperty("AVAILABLE")
    AVAILABLE,
    @JsonProperty("UNAVAILABLE")
    UNAVAILABLE
}
