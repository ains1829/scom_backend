package com.ains.myspring.models.jsontoclass.order;

import java.sql.Date;

public class MissionJson {
  int idtypeordermission;
  int idequipe;
  String motifs;
  Date datedescente;
  int societe;
  int district;
  String context;
  String lieu_controle;

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

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getLieu_controle() {
    return lieu_controle;
  }

  public void setLieu_controle(String lieu_controle) {
    this.lieu_controle = lieu_controle;
  }

}
