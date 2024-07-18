package com.ains.myspring.services.function.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendingMail {
  @Autowired
  private JavaMailSender javaMailSender;

  public void sendEmail(String email, int code) throws Exception {
    MimeMessage message = javaMailSender.createMimeMessage();
    String body = "Veuillez ne pas partager le code de vérification reçu : " + code;
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setSubject("Code de verification");
      helper.setTo(email);
      helper.setText(body, true);
      javaMailSender.send(message);
    } catch (MessagingException e) {
      throw new Exception(e.getMessage());
    }
  }

}
