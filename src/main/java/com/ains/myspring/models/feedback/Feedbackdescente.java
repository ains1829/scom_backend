package com.ains.myspring.models.feedback;

import java.sql.Date;
import com.ains.myspring.models.modules.mission.Ordermission;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedbackdescente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idfeedbackdescente;
  @ManyToOne
  @JoinColumn(name = "idordermission")
  Ordermission ordermission;
  String feedback;
  Date date_feedback;
  String contact;
  String email;

  public Feedbackdescente() {
  }

  public Feedbackdescente(Ordermission ordermission, String feedback, Date date_feedback, String contact,
      String email) {
    this.ordermission = ordermission;
    this.feedback = feedback;
    this.date_feedback = date_feedback;
    this.contact = contact;
    this.email = email;
  }

  public int getIdfeedbackdescente() {
    return idfeedbackdescente;
  }

  public void setIdfeedbackdescente(int idfeedbackdescente) {
    this.idfeedbackdescente = idfeedbackdescente;
  }

  public Ordermission getOrdermission() {
    return ordermission;
  }

  public void setOrdermission(Ordermission ordermission) {
    this.ordermission = ordermission;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public Date getDate_feedback() {
    return date_feedback;
  }

  public void setDate_feedback(Date date_feedback) {
    this.date_feedback = date_feedback;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
