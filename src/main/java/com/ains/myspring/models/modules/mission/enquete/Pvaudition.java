package com.ains.myspring.models.modules.mission.enquete;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pvaudition {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idpvaudition;
  int idenquete;
  int idequipe;
  String urlrapport_pvaudition;
  Date dateaudition;

  public Pvaudition() {
  }

  public Pvaudition(int idenquete, int idequipe, String urlrapport_pvaudition, Date dateaudition) {
    this.idenquete = idenquete;
    this.idequipe = idequipe;
    this.urlrapport_pvaudition = urlrapport_pvaudition;
    this.dateaudition = dateaudition;
  }

  public int getIdpvaudition() {
    return idpvaudition;
  }

  public void setIdpvaudition(int idpvaudition) {
    this.idpvaudition = idpvaudition;
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

  public String getUrlrapport_pvaudition() {
    return urlrapport_pvaudition;
  }

  public void setUrlrapport_pvaudition(String urlrapport_pvaudition) {
    this.urlrapport_pvaudition = urlrapport_pvaudition;
  }

  public Date getDateaudition() {
    return dateaudition;
  }

  public void setDateaudition(Date dateaudition) {
    this.dateaudition = dateaudition;
  }
}
