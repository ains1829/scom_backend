package com.ains.myspring.services.function.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.mission.Ordermission;
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

  public void NotifDemande(String email, Ordermission ordermission, boolean isValidateDg) throws Exception {
    MimeMessage message = javaMailSender.createMimeMessage();
    String sender = ordermission.getSender().getNameadministration();
    String profil = ordermission.getSender().getProfil().getDescription();
    String reference = ordermission.getNumeroserie();
    String region = ordermission.getRegion().getNameregion();
    String body = "";
    if (isValidateDg) {
      body = SetbodyAftervalidateDg(sender, profil, region, reference);
    } else {
      body = Setbody(sender, profil, region, reference);
    }
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setSubject("Validation de l'ordre de mission" + "(" + ordermission.getNumeroserie() + ")");
      helper.setTo(email);
      helper.setText(body, true);
      javaMailSender.send(message);
    } catch (MessagingException e) {
      throw new Exception(e.getMessage());
    }
  }

  public void NotifAboutmission(String email, Ordermission ordermission, boolean MissionIsFinished) throws Exception {
    MimeMessage message = javaMailSender.createMimeMessage();
    String body = "";
    String Subject = "";
    if (MissionIsFinished) {
      Subject = "Mission terminée - Réf : " + ordermission.getNumeroserie();
      body = NotifMissionFinished(ordermission);
    } else {
      Subject = "Votre ordre de mission a été validé";
      body = NotifMission(ordermission);
    }
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setSubject(Subject);
      helper.setTo(email);
      helper.setText(body, true);
      javaMailSender.send(message);
    } catch (MessagingException e) {
      throw new Exception(e.getMessage());
    }
  }

  public void SendSignal(String societe, String description, String email, String region) throws Exception {
    MimeMessage message = javaMailSender.createMimeMessage();
    String body = "<h2 style='color: #2E86C1;'>Nouvelle demande d'ordre de mission</h2>"
        + "<p>Bonjour,</p>"
        + "<p>Une nouveau signalement mission a été soumise et attend votre attention : <strong>"
        + "<p><strong>Societe :</strong> " + societe + "</p>"
        + "<p><strong>Description :</strong> " + description + "</p>"
        + "<p><strong>Region :</strong> " + region + "</p>"
        + "<p>Merci de bien vouloir examiner ce signalement et prendre les mesures necessaires.</p>"
        + "<p style='color: #AEB6BF;'>Ceci est un message automatique, veuillez ne pas répondre.</p>"
        + "<p>Cordialement,</p>";
    String Subject = "Nouveau signalement soumis";
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setSubject(Subject);
      helper.setTo(email);
      helper.setText(body, true);
      javaMailSender.send(message);
    } catch (MessagingException e) {
      throw new Exception(e.getMessage());
    }
  }

  private String Setbody(String sender, String profil, String region, String references) {
    String body = "<h2 style='color: #2E86C1;'>Nouvelle demande d'ordre de mission</h2>"
        + "<p>Bonjour,</p>"
        + "<p>Une nouvelle demande d'ordre de mission a été soumise par : <strong>"
        + "<ul>"
        + "<li><strong>Nom :</strong> " + sender + "</li>"
        + "<li><strong>Profil :</strong> " + profil + "</li>"
        + "<li><strong>Région :</strong> " + region + "</li>"
        + "</ul>"
        + "<p>Référence de l'OM : <strong>" + references + "</strong>.</p>"
        + "<p>Merci de bien vouloir la valider dès que possible.</p>"
        + "<p style='color: #AEB6BF;'>Ceci est un message automatique, veuillez ne pas répondre.</p>"
        + "<p>Cordialement,</p>";
    return body;
  }

  private String SetbodyAftervalidateDg(String sender, String profil, String region, String references) {
    String body = "<h2 style='color: #2E86C1;'>Validation finale requise pour l'ordre de mission</h2>"
        + "<p>Bonjour,</p>"
        + "<p>Nous vous informons que l'ordre de mission portant la référence <strong>" + references
        + "</strong> a déjà été validé par le Directeur Général (DG).</p>"
        + "<p>Il est désormais en attente de votre validation finale.</p>"
        + "<p>Voici les détails de la demande :</p>"
        + "<ul>"
        + "<li><strong>Nom du demandeur :</strong> " + sender + "</li>"
        + "<li><strong>Profil :</strong> " + profil + "</li>"
        + "<li><strong>Région :</strong> " + region + "</li>"
        + "<li><strong>Référence de l'OM :</strong> " + references + "</li>"
        + "</ul>"
        + "<p>Merci de procéder à la validation dès que possible afin de permettre la continuité de la mission.</p>"
        + "<p style='color: #AEB6BF;'>Ceci est un message automatique, veuillez ne pas répondre.</p>"
        + "<p>Cordialement,</p>";
    return body;
  }

  private String NotifMission(Ordermission ordermission) {
    String body = "<p>Bonjour " + ordermission.getEquipe().getChefequipe().getNameadministration() + ",</p>"
        + "<p>Votre ordre de mission portant la référence <strong>" + ordermission.getNumeroserie()
        + "</strong> a été validé avec succès.</p>"
        + "<p>Vous êtes désormais en mission.</p>"
        + "<p>Merci de bien vouloir vous conformer aux instructions transmises.</p>"
        + "<p>Cordialement,</p>";
    return body;
  }

  private String NotifMissionFinished(Ordermission ordermission) {
    String body = "<p>Bonjour ,</p>"
        + "<p>La mission portant la référence <strong>" + ordermission.getNumeroserie()
        + " est maintenant terminée.</p>"
        + "<p>Merci de bien vouloir consulter le rapport de mission pour plus de détails.</p>"
        + "<p>Cordialement,</p>";
    return body;
  }
}
