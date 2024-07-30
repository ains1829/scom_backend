package com.ains.myspring.models.modules.mission.enquete;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pvinfraction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idpvinfraction;
  int idenquete;
  int idequipe;
  String numero_reference;
  String urlrapport_pvinfraction;
  Date dateinfraction;

  public int getIdpvinfraction() {
    return idpvinfraction;
  }

  public void setIdpvinfraction(int idpvinfraction) {
    this.idpvinfraction = idpvinfraction;
  }

  public int getIdenquete() {
    return idenquete;
  }

  public void setIdenquete(int idenquete) {
    this.idenquete = idenquete;
  }

  public int getIdequipe() {
    return idequipe;
  }

  public void setIdequipe(int idequipe) {
    this.idequipe = idequipe;
  }

  public String getNumero_reference() {
    return numero_reference;
  }

  public void setNumero_reference(String numero_reference) {
    this.numero_reference = numero_reference;
  }

  public String getUrlrapport_pvinfraction() {
    return urlrapport_pvinfraction;
  }

  public void setUrlrapport_pvinfraction(String urlrapport_pvinfraction) {
    this.urlrapport_pvinfraction = urlrapport_pvinfraction;
  }

  public Date getDateinfraction() {
    return dateinfraction;
  }

  public void setDateinfraction(Date dateinfraction) {
    this.dateinfraction = dateinfraction;
  }
}
