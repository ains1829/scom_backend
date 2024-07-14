package com.ains.myspring.models.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Typeproduct {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idtypeproduct;
  String nametypeproduct;
  int idunite;

  public int getIdtypeproduct() {
    return idtypeproduct;
  }

  public void setIdtypeproduct(int idtypeproduct) {
    this.idtypeproduct = idtypeproduct;
  }

  public String getNametypeproduct() {
    return nametypeproduct;
  }

  public void setNametypeproduct(String nametypeproduct) {
    this.nametypeproduct = nametypeproduct;
  }

  public int getIdunite() {
    return idunite;
  }

  public void setIdunite(int idunite) {
    this.idunite = idunite;
  }
}
