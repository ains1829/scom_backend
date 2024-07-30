package com.ains.myspring.models.modules.mission;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enquete {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idenquete;
  int idordermission;
  int idsociete;
  int statu;

  public Enquete() {
  }

  public Enquete(int idordermission, int idsociete, int statu) {
    this.idordermission = idordermission;
    this.idsociete = idsociete;
    this.statu = statu;
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

  public int getIdsociete() {
    return idsociete;
  }

  public void setIdsociete(int idsociete) {
    this.idsociete = idsociete;
  }

  public int getStatu() {
    return statu;
  }

  public void setStatu(int statu) {
    this.statu = statu;
  }
}
