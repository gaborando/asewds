package uni.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationSeatView {
    private String identifier;
    private String flightCode;
    private String code;
}
