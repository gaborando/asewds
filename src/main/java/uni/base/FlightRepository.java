package uni.base;

import uni.base.model.Flight;

import java.util.ArrayList;

public class FlightRepository {

    private ArrayList<Flight> memory = new ArrayList<>();

    public Flight save(Flight flight){
        memory.add(flight);
        return flight;
    }

    public ArrayList<Flight> findAll(){
        return memory;
    }

    public void delete(Flight flight){
        memory.remove(flight);
    }
}
