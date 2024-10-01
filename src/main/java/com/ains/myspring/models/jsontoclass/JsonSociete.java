package com.ains.myspring.models.jsontoclass;

public class JsonSociete {
  String namesociete;
  String description;
  String nif;
  String stat;
  int idregion;
  int iddistrict;
  String addresse;
  String responsable;
  String telephone;
  String numerofiscal;

  public JsonSociete() {

  }

  public JsonSociete(String namesociete, String description, String nif, String stat, int idregion, int iddistrict,
      String addresse, String responsable, String telephone, String numerofiscal) {
    this.namesociete = namesociete;
    this.description = description;
    this.nif = nif;
    this.stat = stat;
    this.idregion = idregion;
    this.iddistrict = iddistrict;
    this.addresse = addresse;
    this.responsable = responsable;
    this.telephone = telephone;
    this.numerofiscal = numerofiscal;
  }

  public String getNamesociete() {
    return namesociete;
  }

  public void setNamesociete(String namesociete) {
    this.namesociete = namesociete;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public String getStat() {
    return stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }

  public int getIdregion() {
    return idregion;
  }

  public void setIdregion(int idregion) {
    this.idregion = idregion;
  }

  public int getIddistrict() {
    return iddistrict;
  }

  public void setIddistrict(int iddistrict) {
    this.iddistrict = iddistrict;
  }

  public String getAddresse() {
    return addresse;
  }

  public void setAddresse(String addresse) {
    this.addresse = addresse;
  }

  public String getResponsable() {
    return responsable;
  }

  public void setResponsable(String responsable) {
    this.responsable = responsable;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getNumerofiscal() {
    return numerofiscal;
  }

  public void setNumerofiscal(String numerofiscal) {
    this.numerofiscal = numerofiscal;
  }
}
