package uni.spring.service;

import org.springframework.stereotype.Service;
import uni.spring.model.Flight;
import uni.spring.model.FlightRepository;
import uni.spring.model.Seat;
import uni.spring.utils.FlightFullRepository;
import uni.spring.web.dto.FlightListItemView;
import uni.spring.web.dto.FlightSeatView;
import uni.spring.web.dto.FlightView;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public FlightView create(String code, String username, Set<String> seats) {
        final var f = new Flight();
        f.setCode(code);
        f.setCreatedBy(username);
        f.setCreatedAt(ZonedDateTime.now());
        f.setSeats(seats.stream().map(s -> new Seat(
                code + "_" + s,
                s,
                f,
                null)).collect(Collectors.toList()));
        repository.save(f);
        return f.toView();
    }

    public List<FlightListItemView> findAll() {
        return repository.findAll()
                .stream()
                .map(Flight::toListItemView)
                .toList();
    }

    public FlightView findByCode(String code) {
        return repository.findById(code)
                .map(Flight::toView)
                .orElseThrow();
    }

    public void delete(String code) {
        repository.deleteById(code);
    }
}
