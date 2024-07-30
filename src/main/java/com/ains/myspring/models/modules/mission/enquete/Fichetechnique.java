package com.ains.myspring.models.modules.mission.enquete;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fichetechnique {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idfichetechnique;
  int idenquete;
  int idequipe;
  String url_fichetechnique;
  Date datefichetechnique;

  public Fichetechnique() {
  }

  public Fichetechnique(int idenquete, int idequipe, String url_fichetechnique,
      Date datefichetechnique) {
    this.idenquete = idenquete;
    this.idequipe = idequipe;
    this.url_fichetechnique = url_fichetechnique;
    this.datefichetechnique = datefichetechnique;
  }

  public int getIdfichetechnique() {
    return idfichetechnique;
  }

  public void setIdfichetechnique(int idfichetechnique) {
    this.idfichetechnique = idfichetechnique;
  }

  public int getIdenquete() {
    return idenquete;
  }

  public void setIdenquete(int idenquete) {
    this.idenquete = idenquete;
  }

  public int getIdequipe() {
    return idequipe;
  }

  public void setIdequipe(int idequipe) {
    this.idequipe = idequipe;
  }

  public String getUrl_fichetechnique() {
    return url_fichetechnique;
  }

  public void setUrl_fichetechnique(String url_fichetechnique) {
    this.url_fichetechnique = url_fichetechnique;
  }

  public Date getDatefichetechnique() {
    return datefichetechnique;
  }

  public void setDatefichetechnique(Date datefichetechnique) {
    this.datefichetechnique = datefichetechnique;
  }
}
