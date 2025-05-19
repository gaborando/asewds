package uni.base;

import uni.base.model.Flight;
import uni.base.model.Seat;

import java.util.ArrayList;

public class FlightService {

    private FlightRepository repository = new FlightRepository();

    public Flight create(String code, ArrayList<String> seats){
        var f = new Flight();
        f.setCode(code);
        f.setSeats(new ArrayList<>());
        for (String seat : seats) {
            var s = new Seat();
            s.setCode(seat);
            f.getSeats().add(s);
        }
        return repository.save(f);
    }

    public ArrayList<Flight> findAll(){
        return repository.findAll();
    }

    public Flight findByCode(String code){
        return repository
                .findAll()
                .stream()
                .filter(f -> f.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }

    public void delete(String code){
        repository.delete(findByCode(code));
    }


}
