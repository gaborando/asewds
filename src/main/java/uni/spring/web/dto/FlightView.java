package uni.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class FlightView {
    private String code;
    private String createdBy;
    private ZonedDateTime createdAt;
    private Set<FlightSeatView> seats;
}
