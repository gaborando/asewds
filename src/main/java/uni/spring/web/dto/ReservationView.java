package uni.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ReservationView {
    private String identifier;
    private Set<ReservationSeatView> seats;
}
