package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.ClientOrder;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(ClientOrder clientOrder);

    void sendEmail(SimpleMailMessage msg);


}
