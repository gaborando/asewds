package uni.base;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        var flightService = new FlightService();

        var code = "F01";
        var seats = new ArrayList<String>(
                List.of("A1","A2","B1","B2")
        );

        var flight = flightService.create(code, seats);

        var flights = flightService.findAll();

        System.out.println(flights);

        flight = flightService.findByCode(code);

        System.out.println(flight);

        flightService.delete(code);

        flights = flightService.findAll();
        System.out.println(flights);

    }
}