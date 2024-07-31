package com.ains.myspring.models.jsontoclass.order;

import java.sql.Date;

public class MissionJson {
  int idtypeordermission;
  int idequipe;
  String motifs;
  Date datedescente;
  int societe;
  int district;

  public int getIdtypeordermission() {
    return idtypeordermission;
  }

  public void setIdtypeordermission(int idtypeordermission) {
    this.idtypeordermission = idtypeordermission;
  }

  public int getIdequipe() {
    return idequipe;
  }

  public void setIdequipe(int idequipe) {
    this.idequipe = idequipe;
  }

  public String getMotifs() {
    return motifs;
  }

  public void setMotifs(String motifs) {
    this.motifs = motifs;
  }

  public Date getDatedescente() {
    return datedescente;
  }

  public void setDatedescente(Date datedescente) {
    this.datedescente = datedescente;
  }

  public int getSociete() {
    return societe;
  }

  public void setSociete(int societe) {
    this.societe = societe;
  }

  public int getDistrict() {
    return district;
  }

  public void setDistrict(int district) {
    this.district = district;
  }
}
