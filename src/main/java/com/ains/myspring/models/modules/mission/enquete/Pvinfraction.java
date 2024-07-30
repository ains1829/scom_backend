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
  String urlrapport_pvinfraction;
  Date dateinfraction;

  public Pvinfraction() {
  }

  public Pvinfraction(int idenquete, int idequipe, String urlrapport_pvinfraction, Date dateinfraction) {
    this.idenquete = idenquete;
    this.idequipe = idequipe;
    this.urlrapport_pvinfraction = urlrapport_pvinfraction;
    this.dateinfraction = dateinfraction;
  }

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
