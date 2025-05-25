package uni.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightSeatView {
    private String code;
    private boolean reserved;
}
