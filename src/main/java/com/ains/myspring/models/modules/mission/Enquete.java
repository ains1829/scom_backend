package com.ains.myspring.models.modules.mission;

import com.ains.myspring.models.modules.Societe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enquete {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idenquete;
  int idordermission;
  @ManyToOne
  @JoinColumn(name = "idsociete")
  Societe societe;

  int statu;

  public Enquete() {
  }

  public Enquete(int idordermission, Societe societe, int statu) {
    this.idordermission = idordermission;
    this.societe = societe;
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

  public int getStatu() {
    return statu;
  }

  public void setStatu(int statu) {
    this.statu = statu;
  }

  public Societe getSociete() {
    return societe;
  }

  public void setSociete(Societe societe) {
    this.societe = societe;
  }
}
