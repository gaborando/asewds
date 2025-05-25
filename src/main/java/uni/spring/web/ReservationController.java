package uni.spring.web;

import org.springframework.web.bind.annotation.*;
import uni.spring.service.ReservationService;
import uni.spring.web.dto.FlightCreateRequest;
import uni.spring.web.dto.FlightView;
import uni.spring.web.dto.ReservationCreateRequest;
import uni.spring.web.dto.ReservationView;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    // Come cliente, voglio prenotare uno o più posti su un volo disponibile, per viaggiare nella data desiderata.
    @PostMapping("/")
    public ReservationView create(@RequestBody ReservationCreateRequest reservation,
                                  @RequestHeader("X-User") String username){
        return service.createReservation(
                username, reservation.getFlightCode(), reservation.getSeatCodes()
        );
    }



    // Come utente, voglio cancellare una mia prenotazione, per modificare i miei piani di viaggio.
    @DeleteMapping("/{identifier}")
    public void cancel(@PathVariable String identifier,
                                  @RequestHeader("X-User") String username){
        service.cancelReservation(
                username, identifier
        );
    }



    // Come utente, voglio visualizzare una mia prenotazione, per verificarne i dettagli.
    // Come utente, voglio visualizzare la lista delle mie prenotazioni, per avere una panoramica dei miei viaggi.
    @GetMapping("/")
    public List<ReservationView> findAllMy(@RequestHeader("X-User") String username){
        return service.findAllMyReservations(
                username
        );
    }
}
