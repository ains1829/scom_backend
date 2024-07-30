package com.ains.myspring.models.modules.mission;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Autresuivi {
  @Id
  int idautresuivi;
  int idordermission;
  String numero_reference;
  String urlrapport;

  public int getIdautresuivi() {
    return idautresuivi;
  }

  public void setIdautresuivi(int idautresuivi) {
    this.idautresuivi = idautresuivi;
  }

  public int getIdordermission() {
    return idordermission;
  }

  public void setIdordermission(int idordermission) {
    this.idordermission = idordermission;
  }

  public String getNumero_reference() {
    return numero_reference;
  }

  public void setNumero_reference(String numero_reference) {
    this.numero_reference = numero_reference;
  }

  public String getUrlrapport() {
    return urlrapport;
  }

  public void setUrlrapport(String urlrapport) {
    this.urlrapport = urlrapport;
  }
}
