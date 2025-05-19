package uni.base;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class FlightHttpController {

    public static void main(String[] args) throws IOException {

        var service = new FlightService();

        var code = "F01";
        var seats = new ArrayList<String>(
                List.of("A1","A2","B1","B2")
        );

        var flight = service.create(code, seats);


        var server = HttpServer.create(new InetSocketAddress(8080),0);

        server.createContext("/flights", exchange -> {

            if(exchange.getRequestMethod().equals("GET")){
                var flights = service.findAll();
                var response = flights.toString();
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.close();
            }else{
                var br = "BAD REQUEST";
                exchange.sendResponseHeaders(400, br.length());
                exchange.getResponseBody().write(br.getBytes());
                exchange.close();
            }
        });

        server.start();
    }
}
