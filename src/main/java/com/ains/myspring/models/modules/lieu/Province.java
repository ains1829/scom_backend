package com.ains.myspring.models.modules.lieu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Province {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idprovince;
  String nameprovince;

  public int getIdprovince() {
    return idprovince;
  }

  public void setIdprovince(int idprovince) {
    this.idprovince = idprovince;
  }

  public String getNameprovince() {
    return nameprovince;
  }

  public void setNameprovince(String nameprovince) {
    this.nameprovince = nameprovince;
  }
}
