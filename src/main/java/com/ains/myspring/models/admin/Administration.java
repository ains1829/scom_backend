package com.ains.myspring.models.admin;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Administration {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idadministration;
  String nameadministration;
  String matricule;
  String email;
  String telephone;
  Date BIRTHDAY;
  int GENDER;
  String ADDRESSE;
  String photo;
  @ManyToOne
  @JoinColumn(name = "idprofil")
  Profil profil;

  boolean haveaccount;
  boolean isactive;

  public int getIdadministration() {
    return idadministration;
  }

  public void setIdadministration(int idadministration) {
    this.idadministration = idadministration;
  }

  public String getNameadministration() {
    return nameadministration;
  }

  public void setNameadministration(String nameadministration) {
    this.nameadministration = nameadministration;
  }

  public String getMatricule() {
    return matricule;
  }

  public void setMatricule(String matricule) {
    this.matricule = matricule;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Date getBIRTHDAY() {
    return BIRTHDAY;
  }

  public void setBIRTHDAY(Date bIRTHDAY) {
    BIRTHDAY = bIRTHDAY;
  }

  public int getGENDER() {
    return GENDER;
  }

  public void setGENDER(int gENDER) {
    GENDER = gENDER;
  }

  public String getADDRESSE() {
    return ADDRESSE;
  }

  public void setADDRESSE(String aDDRESSE) {
    ADDRESSE = aDDRESSE;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Profil getProfil() {
    return profil;
  }

  public void setProfil(Profil profil) {
    this.profil = profil;
  }

  public boolean isHaveaccount() {
    return haveaccount;
  }

  public void setHaveaccount(boolean haveaccount) {
    this.haveaccount = haveaccount;
  }

  public boolean isIsactive() {
    return isactive;
  }

  public void setIsactive(boolean isactive) {
    this.isactive = isactive;
  }
}
