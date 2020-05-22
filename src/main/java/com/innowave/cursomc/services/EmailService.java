package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.ClientOrder;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(ClientOrder clientOrder);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(ClientOrder obj);

    void sendHtmlEmail(MimeMessage msg);


}
