package com.ains.myspring.models.feedback;

import java.sql.Date;
import com.ains.myspring.models.modules.Societe;
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
  @ManyToOne
  @JoinColumn(name = "idsociete")
  Societe societe;
  String feedback;
  Date date_feedback;

  public Feedbackdescente() {
  }

  public Feedbackdescente(Ordermission ordermission, Societe societe, String feedback, Date date_feedback) {
    this.ordermission = ordermission;
    this.societe = societe;
    this.feedback = feedback;
    this.date_feedback = date_feedback;
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

  public Societe getSociete() {
    return societe;
  }

  public void setSociete(Societe societe) {
    this.societe = societe;
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
}
