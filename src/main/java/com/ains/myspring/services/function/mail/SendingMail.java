package com.ains.myspring.services.function.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendingMail {
  @Autowired
  private JavaMailSender javaMailSender;

  public void sendEmail(List<String> to, String subject, String body) throws Exception {
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      String[] recipients = to.toArray(new String[to.size()]);
      helper.setSubject(subject);
      helper.setTo(recipients);
      helper.setText(body, true);
      javaMailSender.send(message);
    } catch (MessagingException e) {
      throw new Exception(e.getMessage());
    }
  }

}
