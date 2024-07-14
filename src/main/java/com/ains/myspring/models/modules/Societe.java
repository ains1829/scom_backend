package com.ains.myspring.models.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Societe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idsociete;
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
  boolean societeactive;

  public int getIdsociete() {
    return idsociete;
  }

  public void setIdsociete(int idsociete) {
    this.idsociete = idsociete;
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

  public int getIddistrict() {
    return iddistrict;
  }

  public void setIddistrict(int iddistrict) {
    this.iddistrict = iddistrict;
  }

  public boolean isSocieteactive() {
    return societeactive;
  }

  public void setSocieteactive(boolean societeactive) {
    this.societeactive = societeactive;
  }

}
