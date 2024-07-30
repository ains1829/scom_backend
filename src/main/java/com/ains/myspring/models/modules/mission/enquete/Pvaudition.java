package com.ains.myspring.models.modules.mission.enquete;

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
  String numero_reference;
  String urlrapport_pvaudition;
  String dateaudition;

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

  public String getNumero_reference() {
    return numero_reference;
  }

  public void setNumero_reference(String numero_reference) {
    this.numero_reference = numero_reference;
  }

  public String getUrlrapport_pvaudition() {
    return urlrapport_pvaudition;
  }

  public void setUrlrapport_pvaudition(String urlrapport_pvaudition) {
    this.urlrapport_pvaudition = urlrapport_pvaudition;
  }

  public String getDateaudition() {
    return dateaudition;
  }

  public void setDateaudition(String dateaudition) {
    this.dateaudition = dateaudition;
  }
}
