package com.ains.myspring.models.jsontoclass;

import java.sql.Date;

public class RegisterUser {
  String nameadministration;
  String matricule;
  String email;
  String telephone;
  String mdp;
  Date birthday;
  int gender;
  String addresse;
  String photo;
  int idprofil;
  int idregion;

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

  public String getMdp() {
    return mdp;
  }

  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public String getAddresse() {
    return addresse;
  }

  public void setAddresse(String addresse) {
    this.addresse = addresse;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public int getIdprofil() {
    return idprofil;
  }

  public void setIdprofil(int idprofil) {
    this.idprofil = idprofil;
  }

  public int getIdregion() {
    return idregion;
  }

  public void setIdregion(int idregion) {
    this.idregion = idregion;
  }
}
