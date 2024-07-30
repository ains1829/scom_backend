package com.ains.myspring.models.modules.lieu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Region {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idregion;
  @ManyToOne
  @JoinColumn(name = "idprovince")
  Province province;
  String nameregion;

  public int getIdregion() {
    return idregion;
  }

  public void setIdregion(int idregion) {
    this.idregion = idregion;
  }

  public String getNameregion() {
    return nameregion;
  }

  public void setNameregion(String nameregion) {
    this.nameregion = nameregion;
  }

  public Province getProvince() {
    return province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

}
