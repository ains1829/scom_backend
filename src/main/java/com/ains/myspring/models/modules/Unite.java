package com.ains.myspring.models.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Unite {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idunite;
  String nameunite;

  public int getIdunite() {
    return idunite;
  }

  public void setIdunite(int idunite) {
    this.idunite = idunite;
  }

  public String getNameunite() {
    return nameunite;
  }

  public void setNameunite(String nameunite) {
    this.nameunite = nameunite;
  }
}
