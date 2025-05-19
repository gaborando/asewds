package uni.base.model;

import lombok.Data;

@Data
public class Seat {
    private String code;

    @Override
    public String toString() {
        return "Seat{" +
                "code='" + code + '\'' +
                '}';
    }
}
