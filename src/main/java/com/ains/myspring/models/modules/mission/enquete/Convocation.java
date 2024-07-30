package com.ains.myspring.models.modules.mission.enquete;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Convocation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idconvocation;
  String numeroconvocation;
  int idenquete;
  int idordermission;
  String muni;

  public int getIdconvocation() {
    return idconvocation;
  }

  public void setIdconvocation(int idconvocation) {
    this.idconvocation = idconvocation;
  }

  public String getNumeroconvocation() {
    return numeroconvocation;
  }

  public void setNumeroconvocation(String numeroconvocation) {
    this.numeroconvocation = numeroconvocation;
  }

  public int getIdenquete() {
    return idenquete;
  }

  public void setIdenquete(int idenquete) {
    this.idenquete = idenquete;
  }

  public int getIdordermission() {
    return idordermission;
  }

  public void setIdordermission(int idordermission) {
    this.idordermission = idordermission;
  }

  public String getMuni() {
    return muni;
  }

  public void setMuni(String muni) {
    this.muni = muni;
  }
}
