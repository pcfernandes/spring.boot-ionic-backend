package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.ClientOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${default.sender}")
    private String sender;
    @Override
    public void sendOrderConfirmationEmail(ClientOrder clientOrder){
        SimpleMailMessage sm = prepareSimpleMailMessageFromClientOrder(clientOrder);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromClientOrder(ClientOrder clientOrder){

        SimpleMailMessage sm =  new SimpleMailMessage();
        sm.setTo(clientOrder.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Request Confirmation. Code: " + clientOrder.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(clientOrder.toString());
        return sm;
    }

    protected String htmlFromTemplateClientOrder(ClientOrder obj){
        Context context = new Context();
        context.setVariable("clientOrder",obj);
        return templateEngine.process("email/clientOrderConfirmationEmail",context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(ClientOrder obj){
        MimeMessage mm = null;
        try {
            mm = prepareMemeeMessageFromClientOrder(obj);
            sendHtmlEmail(mm);

        } catch (MessagingException e) {
           sendOrderConfirmationEmail(obj);
        }
    }

    protected MimeMessage prepareMemeeMessageFromClientOrder(ClientOrder obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(obj.getClient().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Order confirmed! Code: " + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateClientOrder(obj));
        return mimeMessage;
    }
}
