package uni.spring.web.dto;

import lombok.Data;

import java.util.Set;

@Data
public class FlightCreateRequest {
    private String code;
    private Set<String> seats;
}
