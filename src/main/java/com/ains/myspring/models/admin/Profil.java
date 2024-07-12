package com.ains.myspring.models.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profil {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idprofil;
  String nameprofil;
  String description;

  public Profil() {
  }

  public Profil(int idprofil, String nameprofil, String description) {
    this.idprofil = idprofil;
    this.nameprofil = nameprofil;
    this.description = description;
  }

  public int getIdprofil() {
    return idprofil;
  }

  public void setIdprofil(int idprofil) {
    this.idprofil = idprofil;
  }

  public String getNameprofil() {
    return nameprofil;
  }

  public void setNameprofil(String nameprofil) {
    this.nameprofil = nameprofil;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
