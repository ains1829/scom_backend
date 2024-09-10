package com.ains.myspring.models.modules.mission;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Autresuivi {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idautresuivi;
  @ManyToOne
  @JoinColumn(name = "idordermission")
  Ordermission ordermission;
  String urlrapport;
  int statu;
  int iddistrict;

  public Autresuivi() {
  }

  public Autresuivi(Ordermission ordermission, String urlrapport, int statu, int iddistrict) {
    this.ordermission = ordermission;
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

  public Ordermission getOrdermission() {
    return ordermission;
  }

  public void setOrdermission(Ordermission ordermission) {
    this.ordermission = ordermission;
  }

}
