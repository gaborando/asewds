package uni.spring.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import uni.spring.model.FlightRepository;
import uni.spring.model.Reservation;
import uni.spring.model.ReservationRepository;
import uni.spring.model.Seat;
import uni.spring.web.dto.ReservationView;
import uni.spring.web.dto.ReservationSeatView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private static final Logger LOGGER = LogManager.getLogger(ReservationService.class);

    private final FlightRepository flightRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(FlightRepository flightRepository, ReservationRepository reservationRepository) {
        this.flightRepository = flightRepository;
        this.reservationRepository = reservationRepository;
    }

    public ReservationView createReservation(String username, String flight, Set<String> seats) {
        var f = flightRepository.findById(flight).orElseThrow();

        LOGGER.info("Flight: {}", f);

        var reservation = reservationRepository.save(new Reservation(
                UUID.randomUUID().toString(),
                username,
                new ArrayList<>()
        ));

        LOGGER.info("Reservation: {}", reservation);

        for (String seat : seats) {
            var s = f.getSeats().stream().filter(e -> e.getCode().equals(seat)).findFirst().orElseThrow();
            Assert.isTrue(s.getReservation() == null, "Seat is already reserved");

            s.setReservation(reservation);
            reservation.getSeats().add(s);
            LOGGER.info("Seat: {}", s);

        }

        LOGGER.info("Reservation Seats: {}", reservation.getSeats());
        reservationRepository.save(reservation);

        return reservation.toView();

    }

    public void cancelReservation(String username, String identifier) {
        var r = reservationRepository.findById(identifier).orElseThrow();
        Assert.isTrue(r.getOwner().equals(username), "Reservation does not belong to user");
        for (Seat seat : r.getSeats()) {
            seat.setReservation(null);
        }
        reservationRepository.delete(r);
    }

    public List<ReservationView> findAllMyReservations(String username) {

        return reservationRepository.findAllByOwner(username).stream()
                .map(Reservation::toView)
                .toList();
    }
}
