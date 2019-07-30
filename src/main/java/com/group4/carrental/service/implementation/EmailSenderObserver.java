package com.group4.carrental.service.implementation;

import com.group4.carrental.model.Email;
import com.group4.carrental.service.IObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("EmailSenderObserver")
public class EmailSenderObserver implements IObserver {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(Email email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo();
        message.setTo(email.getReceiver());
        message.setSubject(email.getSubject());
        message.setText(email.getEmailText());

        javaMailSender.send(message);

    }
}
