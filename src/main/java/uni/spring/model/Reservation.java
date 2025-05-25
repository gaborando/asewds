package uni.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uni.spring.web.dto.ReservationView;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
    @Id
    private String identifier;

    private String owner;

    @OneToMany(mappedBy = "reservation")
    private List<Seat> seats;

    public ReservationView toView() {
        return new ReservationView(identifier, seats.stream().map(Seat::toReservationSeatView)
                .collect(Collectors.toSet()));
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "identifier='" + identifier + '\'' +
                ", owner='" + owner + '\'' +
                ", seats=" + seats.stream().map(Seat::getCode).collect(Collectors.toSet()) +
                '}';
    }
}
