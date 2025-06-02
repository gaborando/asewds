package uni.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uni.spring.web.dto.FlightListItemView;
import uni.spring.web.dto.FlightSeatView;
import uni.spring.web.dto.FlightView;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {
    @Id
    private String code;

    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;

    private String createdBy;
    private ZonedDateTime createdAt;
    @OneToMany(mappedBy = "flight", cascade = jakarta.persistence.CascadeType.ALL)
    private List<Seat> seats;

    public FlightView toView() {
        return new FlightView(code, createdBy, createdAt,
                seats.stream().map(Seat::toFlightSeatView)
                        .collect(Collectors.toSet()));
    }

    public FlightListItemView toListItemView() {
        return new FlightListItemView(code);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "code='" + code + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", seats=" + seats +
                '}';
    }

}
