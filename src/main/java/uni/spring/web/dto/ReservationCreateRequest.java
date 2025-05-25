package uni.spring.web.dto;

import lombok.Data;

import java.util.Set;
@Data
public class ReservationCreateRequest {
    private String flightCode;
    private Set<String> seatCodes;
}
