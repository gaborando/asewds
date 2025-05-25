package uni.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uni.spring.web.dto.FlightSeatView;
import uni.spring.web.dto.ReservationSeatView;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Seat {

    @Id
    private String identifier;
    private String code;
    @ManyToOne
    private Flight flight;
    @ManyToOne
    private Reservation reservation;

    public FlightSeatView toFlightSeatView() {
        return new FlightSeatView(code, reservation != null);
    }

    public ReservationSeatView toReservationSeatView() {
        return new ReservationSeatView(identifier, flight.getCode(), code);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "identifier='" + identifier + '\'' +
                ", code='" + code + '\'' +
                ", flight=" + flight.getCode() +
                ", reserved=" + (reservation != null) +
                '}';
    }
}
