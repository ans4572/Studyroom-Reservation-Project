package fullstack.reservation.domain.Enum;

import com.fasterxml.jackson.annotation.JsonProperty;

//성별
public enum Gender {
    @JsonProperty("MALE")
    MALE,
    @JsonProperty("FEMALE")
    FEMALE
}
