package com.ains.myspring.models.jsontoclass;

import jakarta.servlet.http.HttpServletRequest;

public class JsonAdministration {
  String nameadministration;
  String matricule;
  String email;
  String telephone;
  int gender;
  int idregion;
  int idprofil;
  String addresse;

  public JsonAdministration() {
  }

  public JsonAdministration(HttpServletRequest request) {
    setNameadministration(request.getParameter("nameadministration"));
    setEmail(request.getParameter("email"));
    setAddresse(request.getParameter("addresse"));
    setMatricule(request.getParameter("matricule"));
    setIdprofil(Integer.valueOf(request.getParameter("profil")));
    setIdregion(Integer.valueOf(request.getParameter("region")));
    setTelephone(request.getParameter("telephone"));
    setGender(Integer.valueOf(request.getParameter("gender")));
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
