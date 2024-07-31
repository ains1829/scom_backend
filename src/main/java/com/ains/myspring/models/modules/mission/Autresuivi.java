package com.ains.myspring.models.modules.mission;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Autresuivi {
  @Id
  int idautresuivi;
  int idordermission;
  String urlrapport;
  int statu;
  int iddistrict;

  public Autresuivi() {
  }

  public Autresuivi(int idordermission, String urlrapport, int statu, int iddistrict) {
    this.idordermission = idordermission;
    this.urlrapport = urlrapport;
    this.statu = statu;
    this.iddistrict = iddistrict;
  }

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

  public String getUrlrapport() {
    return urlrapport;
  }

  public void setUrlrapport(String urlrapport) {
    this.urlrapport = urlrapport;
  }

  public int getStatu() {
    return statu;
  }

  public void setStatu(int statu) {
    this.statu = statu;
  }

  public int getIddistrict() {
    return iddistrict;
  }

  public void setIddistrict(int iddistrict) {
    this.iddistrict = iddistrict;
  }
}
