package uni.spring.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import uni.spring.model.ReservationRepository;
import uni.spring.model.event.ReservationCreatedEvent;

@Service
public class ReservationProjector {

    private final ReservationRepository reservationRepository;

    public ReservationProjector(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @EventListener
    public void on(ReservationCreatedEvent event) {
        reservationRepository.save(event.getReservation());
    }
    
}
