package com.ains.myspring.models.modules.mission;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Collecte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idcollecte;
  int idordermission;
  int iddistrict;
  int statu;
  Date datecollecte;

  public Collecte() {
  }

  public Collecte(int idordermission, int iddistrict, int statu, Date datecollecte) {
    this.idordermission = idordermission;
    this.iddistrict = iddistrict;
    this.statu = statu;
    this.datecollecte = datecollecte;
  }

  public int getIdcollecte() {
    return idcollecte;
  }

  public void setIdcollecte(int idcollecte) {
    this.idcollecte = idcollecte;
  }

  public int getIdordermission() {
    return idordermission;
  }

  public void setIdordermission(int idordermission) {
    this.idordermission = idordermission;
  }

  public int getIddistrict() {
    return iddistrict;
  }

  public void setIddistrict(int iddistrict) {
    this.iddistrict = iddistrict;
  }

  public int getStatu() {
    return statu;
  }

  public void setStatu(int statu) {
    this.statu = statu;
  }

  public Date getDatecollecte() {
    return datecollecte;
  }

  public void setDatecollecte(Date datecollecte) {
    this.datecollecte = datecollecte;
  }
}
