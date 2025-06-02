package uni.spring.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import uni.spring.model.Reservation;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationCreatedEvent {
    private Reservation  reservation;
}
