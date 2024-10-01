package com.ains.myspring.models.modules.signal;

import java.sql.Date;
import java.util.List;
import com.ains.myspring.models.modules.Societe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

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
  int idanomaly;
  int idregion;
  String addressesociete;
  String namesociete;
  String nameanomaly;
  @Transient
  List<Signal_photo> photo;

  public Signal(String email_sending, String numberphone, Societe societe, String description, Date datesignal,
      int idanomaly, int idregion, String addressesociete, String namesociete, String nameanomaly) {
    this.email_sending = email_sending;
    this.numberphone = numberphone;
    this.societe = societe;
    this.description = description;
    this.datesignal = datesignal;
    this.idanomaly = idanomaly;
    this.idregion = idregion;
    this.addressesociete = addressesociete;
    this.namesociete = namesociete;
    this.nameanomaly = nameanomaly;
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

  public int getIdanomaly() {
    return idanomaly;
  }

  public void setIdanomaly(int idanomaly) {
    this.idanomaly = idanomaly;
  }

  public int getIdregion() {
    return idregion;
  }

  public void setIdregion(int idregion) {
    this.idregion = idregion;
  }

  public String getAddressesociete() {
    return addressesociete;
  }

  public void setAddressesociete(String addressesociete) {
    this.addressesociete = addressesociete;
  }

  public String getNamesociete() {
    return namesociete;
  }

  public void setNamesociete(String namesociete) {
    this.namesociete = namesociete;
  }

  public String getNameanomaly() {
    return nameanomaly;
  }

  public void setNameanomaly(String nameanomaly) {
    this.nameanomaly = nameanomaly;
  }

  public List<Signal_photo> getPhoto() {
    return photo;
  }

  public void setPhoto(List<Signal_photo> photo) {
    this.photo = photo;
  }
}
