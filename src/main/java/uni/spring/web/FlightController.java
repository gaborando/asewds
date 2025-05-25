package uni.spring.web;

import org.springframework.web.bind.annotation.*;
import uni.spring.model.Flight;
import uni.spring.service.FlightService;
import uni.spring.web.dto.FlightCreateRequest;
import uni.spring.web.dto.FlightListItemView;
import uni.spring.web.dto.FlightView;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    // Come utente o manager, voglio visualizzare la lista dei voli disponibili, per consultare le opzioni di viaggio.
    @GetMapping("/")
    public List<FlightListItemView> findAll(){
        return service.findAll();
    }

    // Come utente o manager, voglio visualizzare i dettagli di un volo, inclusi i posti ancora disponibili, per valutare la disponibilità.
    @GetMapping("/{code}")
    public FlightView findByCode(@PathVariable String code){
        return service.findByCode(code);
    }

    // Come manager, voglio creare nuovi voli, per offrire nuove rotte agli utenti.
    @PostMapping("/")
    public FlightView create(@RequestBody FlightCreateRequest flight,
                         @RequestHeader("X-User") String username){
        return service.create(flight.getCode(), username, flight.getSeats());
    }

    // Come manager, voglio cancellare voli esistenti, per gestire l’operatività della compagnia.
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        service.delete(code);
    }
}
