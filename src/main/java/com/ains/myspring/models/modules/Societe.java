package com.ains.myspring.models.modules;

import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.models.modules.lieu.Region;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Societe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idsociete;
  String namesociete;
  String description;
  String nif;
  String stat;
  @ManyToOne(optional = false)
  @JoinColumn(name = "idregion")
  Region region;
  @ManyToOne(optional = false)
  @JoinColumn(name = "iddistrict")
  District district;
  String addresse;
  String responsable;
  String telephone;
  String numerofiscal;

  public Societe() {
  }

  public Societe(String namesociete, String description, String nif, String stat, Region region, District district,
      String addresse, String responsable, String telephone, String numerofiscal) {
    this.namesociete = namesociete;
    this.description = description;
    this.nif = nif;
    this.stat = stat;
    this.region = region;
    this.district = district;
    this.addresse = addresse;
    this.responsable = responsable;
    this.telephone = telephone;
    this.numerofiscal = numerofiscal;
  }

  boolean societeactive = true;

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

  public boolean isSocieteactive() {
    return societeactive;
  }

  public void setSocieteactive(boolean societeactive) {
    this.societeactive = societeactive;
  }

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }
}
