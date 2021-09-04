package fullstack.reservation.domain.Enum;

import com.fasterxml.jackson.annotation.JsonProperty;

//이용권의 종류
public enum Ticket {
    HOUR,
    @JsonProperty("DAY")
    DAY,
    WEEK,
    @JsonProperty("MONTH")
    MONTH
}
