package uni.base.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Reservation {
    private String code;
    private User owner;
    private ArrayList<Seat> seats;
}
