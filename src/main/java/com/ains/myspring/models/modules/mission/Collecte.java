package com.ains.myspring.models.modules.mission;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Collecte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idcollecte;
  @ManyToOne
  @JoinColumn(name = "idordermission")
  Ordermission ordermission;
  int iddistrict;
  int statu;
  Date datecollecte;
  Date datevalidate_collecte;

  public Collecte() {
  }

  public Collecte(Ordermission ordermission, int iddistrict, int statu, Date datecollecte) {
    this.ordermission = ordermission;
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

  public Ordermission getOrdermission() {
    return ordermission;
  }

  public void setOrdermission(Ordermission ordermission) {
    this.ordermission = ordermission;
  }

  public Date getDatevalidate_collecte() {
    return datevalidate_collecte;
  }

  public void setDatevalidate_collecte(Date datevalidate_collecte) {
    this.datevalidate_collecte = datevalidate_collecte;
  }
}
