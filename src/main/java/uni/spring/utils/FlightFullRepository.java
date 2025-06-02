package uni.spring.utils;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import uni.spring.model.Flight;
import uni.spring.model.FlightRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class FlightFullRepository {

    private final FlightRepository flightRepository;


    public FlightFullRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @SneakyThrows
    public void save(Flight flight){
        flightRepository.save(flight);
        Thread.sleep(10*1000);
    }

    public Collection<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Optional<Flight> findById(String code) {
        return  flightRepository.findById(code);
    }

    public void deleteById(String code) {
        flightRepository.deleteById(code);
    }
}
