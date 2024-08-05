package com.ains.myspring.models.modules.signal;

import java.sql.Date;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.lieu.District;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Signal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idsignal;
  String email_sending;
  String numberphone;
  @ManyToOne
  @JoinColumn(name = "idsociete")
  Societe societe;
  String description;
  Date datesignal;
  @ManyToOne
  @JoinColumn(name = "iddistrict")
  District district;

  public Signal(String email_sending, String numberphone, Societe societe, String description, Date datesignal,
      District district) {
    this.email_sending = email_sending;
    this.numberphone = numberphone;
    this.societe = societe;
    this.description = description;
    this.datesignal = datesignal;
    this.district = district;
  }

  public Signal() {
  }

  public int getIdsignal() {
    return idsignal;
  }

  public void setIdsignal(int idsignal) {
    this.idsignal = idsignal;
  }

  public String getEmail_sending() {
    return email_sending;
  }

  public void setEmail_sending(String email_sending) {
    this.email_sending = email_sending;
  }

  public String getNumberphone() {
    return numberphone;
  }

  public void setNumberphone(String numberphone) {
    this.numberphone = numberphone;
  }

  public Societe getSociete() {
    return societe;
  }

  public void setSociete(Societe societe) {
    this.societe = societe;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDatesignal() {
    return datesignal;
  }

  public void setDatesignal(Date datesignal) {
    this.datesignal = datesignal;
  }

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

}
