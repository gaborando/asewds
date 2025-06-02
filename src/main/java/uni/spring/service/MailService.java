package uni.spring.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Deprecated
// @Service
public class MailService {

    public void senEmail(){
        System.out.println("Sending email...");
    }
}
