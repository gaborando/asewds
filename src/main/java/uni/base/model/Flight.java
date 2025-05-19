package uni.base.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Flight {
    private String code;
    private ArrayList<Seat> seats;

    @Override
    public String toString() {
        return "Flight{" +
                "code='" + code + '\'' +
                ", seats=" + seats +
                '}';
    }
}
