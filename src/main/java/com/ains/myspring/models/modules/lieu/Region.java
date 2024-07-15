package com.ains.myspring.models.modules.lieu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Region {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idregion;
  int idprovince;
  String nameregion;

  public int getIdregion() {
    return idregion;
  }

  public void setIdregion(int idregion) {
    this.idregion = idregion;
  }

  public int getIdprovince() {
    return idprovince;
  }

  public void setIdprovince(int idprovince) {
    this.idprovince = idprovince;
  }

  public String getNameregion() {
    return nameregion;
  }

  public void setNameregion(String nameregion) {
    this.nameregion = nameregion;
  }
}
