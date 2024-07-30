package com.ains.myspring.models.modules.mission;

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
}
