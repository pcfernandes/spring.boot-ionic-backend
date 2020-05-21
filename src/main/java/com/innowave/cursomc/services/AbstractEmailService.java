package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.ClientOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;
    @Override
    public void sendOrderConfirmationEmail(ClientOrder clientOrder){
        SimpleMailMessage sm = prepareSimpleMailMessageFromClientOrder(clientOrder);
        sendEmail(sm);
    }

    private SimpleMailMessage prepareSimpleMailMessageFromClientOrder(ClientOrder clientOrder){

        SimpleMailMessage sm =  new SimpleMailMessage();
        sm.setTo(clientOrder.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Request Confirmation. Code: " + clientOrder.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(clientOrder.toString());
        return sm;
    }
}
