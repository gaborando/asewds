package uni.spring.service;

import org.springframework.stereotype.Service;
import uni.spring.model.Flight;
import uni.spring.model.FlightRepository;
import uni.spring.web.dto.FlightListItemView;
import uni.spring.web.dto.FlightView;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public FlightView create(String code, String username){
        var f = new Flight();
        f.setCode(code);
        f.setCreatedBy(username);
        f.setCreatedAt(ZonedDateTime.now());
        f = repository.save(f);
        return new FlightView(
                f.getCode(),
                f.getCreatedBy(),
                f.getCreatedAt());
    }

    public List<FlightListItemView> findAll(){
        return repository.findAll()
                .stream()
                .map(e -> new FlightListItemView(e.getCode()))
                .toList();
    }

    public FlightView findByCode(String code){
        return repository.findById(code)
                .map(f -> new FlightView(f.getCode(), f.getCreatedBy(), f.getCreatedAt()))
                .orElseThrow();
    }

    public void delete(String code){
        repository.deleteById(code);
    }
}
