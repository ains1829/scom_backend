package com.ains.myspring.models.feedback;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Feedbackdescentephoto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idfeedbackdescentephoto;
  int idfeedbackdescente;
  String url_photo;

  public Feedbackdescentephoto() {
  }

  public Feedbackdescentephoto(int idfeedbackdescente, String url_photo) {
    this.idfeedbackdescente = idfeedbackdescente;
    this.url_photo = url_photo;
  }

  public int getIdfeedbackdescentephoto() {
    return idfeedbackdescentephoto;
  }

  public void setIdfeedbackdescentephoto(int idfeedbackdescentephoto) {
    this.idfeedbackdescentephoto = idfeedbackdescentephoto;
  }

  public int getIdfeedbackdescente() {
    return idfeedbackdescente;
  }

  public void setIdfeedbackdescente(int idfeedbackdescente) {
    this.idfeedbackdescente = idfeedbackdescente;
  }

  public String getUrl_photo() {
    return url_photo;
  }

  public void setUrl_photo(String url_photo) {
    this.url_photo = url_photo;
  }
}
