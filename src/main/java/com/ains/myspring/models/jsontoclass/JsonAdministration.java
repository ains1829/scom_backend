package com.ains.myspring.models.jsontoclass;

import java.sql.Date;

public class JsonAdministration {
  String nameadministration;
  String matricule;
  String email;
  String telephone;
  Date birthday;
  int gender;
  int idregion;
  int idprofil;
  String addresse;

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

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date date) {
    this.birthday = date;
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

  public int getIdregion() {
    return idregion;
  }

  public void setIdregion(int idregion) {
    this.idregion = idregion;
  }

  public int getIdprofil() {
    return idprofil;
  }

  public void setIdprofil(int idprofil) {
    this.idprofil = idprofil;
  }

}
