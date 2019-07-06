package com.group4.carrental.service.implementation;

import com.group4.carrental.model.Email;
import com.group4.carrental.service.ISendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("SendMailService")
public class SendMailService implements ISendMailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(Email emailData) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo();
        message.setTo(emailData.getReceiver());
        message.setSubject(emailData.getSubject());
        message.setText(emailData.getEmailText());

        javaMailSender.send(message);

    }
}
