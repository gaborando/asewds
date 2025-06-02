package uni.spring.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import uni.spring.model.event.ReservationCreatedEvent;

@Deprecated
// @Service
public class ReservationNotificationService {
    
    private final MailService mailService;

    public ReservationNotificationService(MailService mailService) {
        this.mailService = mailService;
    }
    
    @EventListener
    public void on(ReservationCreatedEvent event){
        mailService.senEmail();
    }
    
}
