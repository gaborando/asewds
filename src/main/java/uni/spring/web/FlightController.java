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

    @GetMapping("/")
    public List<FlightListItemView> findAll(){
        return service.findAll();
    }

    @GetMapping("/{code}")
    public FlightView findByCode(@PathVariable String code){
        return service.findByCode(code);
    }

    @PostMapping("/")
    public FlightView create(@RequestBody FlightCreateRequest flight,
                         @RequestHeader("X-User") String username){
        return service.create(flight.getCode(), username);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        service.delete(code);
    }
}
