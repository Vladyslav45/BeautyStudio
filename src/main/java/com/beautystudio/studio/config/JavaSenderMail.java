package com.beautystudio.studio.config;

import com.beautystudio.studio.model.SubCategory;
import com.beautystudio.studio.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class JavaSenderMail {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String adminEmail;

    public void sendEmail(User user, LocalDateTime dateTime, SubCategory subcategory){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy h:mm");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("Rezerwacja");
        message.setText(user.getName() + " chce zarezerwować miejsce na " + subcategory.getName() + " w dniu " + dateTimeFormatter.format(dateTime));
        javaMailSender.send(message);
    }

    public void sendEmailWhenUserMakeUpdate(User user, LocalDateTime dateTime, SubCategory subcategory){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy h:mm");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("Zmiana rezerwacji");
        message.setText(user.getName() + " chce zmienić miejsce na " + subcategory.getName() + " w dniu " + dateTimeFormatter.format(dateTime));
        javaMailSender.send(message);
    }
}
