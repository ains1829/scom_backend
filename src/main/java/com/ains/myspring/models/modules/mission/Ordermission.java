package com.ains.myspring.models.modules.mission;

import java.sql.Date;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.models.modules.lieu.Region;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ordermission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idordermission;
  int idtypeordermission;
  @ManyToOne
  @JoinColumn(name = "idequipe")
  Equipe equipe;
  @ManyToOne
  @JoinColumn(name = "idregion")
  Region region;
  String motifs;
  String numeroserie;
  Date dateorder;
  Date datedescente;
  Date dateorderend;
  boolean isbasculed;
  int status_validation;
  String fileordermission;

  public Ordermission() {
  }

  public Ordermission(int idtypeordermission, Equipe equipe, Region region, String motifs, String numeroserie,
      Date dateorder, Date datedescente) {
    this.idtypeordermission = idtypeordermission;
    this.equipe = equipe;
    this.region = region;
    this.motifs = motifs;
    this.numeroserie = numeroserie;
    this.dateorder = dateorder;
    this.datedescente = datedescente;
  }

  public int getIdordermission() {
    return idordermission;
  }

  public void setIdordermission(int idordermission) {
    this.idordermission = idordermission;
  }

  public int getIdtypeordermission() {
    return idtypeordermission;
  }

  public void setIdtypeordermission(int idtypeordermission) {
    this.idtypeordermission = idtypeordermission;
  }

  public Equipe getEquipe() {
    return equipe;
  }

  public void setEquipe(Equipe equipe) {
    this.equipe = equipe;
  }

  public String getMotifs() {
    return motifs;
  }

  public void setMotifs(String motifs) {
    this.motifs = motifs;
  }

  public String getNumeroserie() {
    return numeroserie;
  }

  public void setNumeroserie(String numeroserie) {
    this.numeroserie = numeroserie;
  }

  public Date getDateorder() {
    return dateorder;
  }

  public void setDateorder(Date dateorder) {
    this.dateorder = dateorder;
  }

  public Date getDatedescente() {
    return datedescente;
  }

  public void setDatedescente(Date datedescente) {
    this.datedescente = datedescente;
  }

  public Date getDateorderend() {
    return dateorderend;
  }

  public void setDateorderend(Date dateorderend) {
    this.dateorderend = dateorderend;
  }

  public boolean isIsbasculed() {
    return isbasculed;
  }

  public void setIsbasculed(boolean isbasculed) {
    this.isbasculed = isbasculed;
  }

  public int getStatus_validation() {
    return status_validation;
  }

  public void setStatus_validation(int status_validation) {
    this.status_validation = status_validation;
  }

  public String getFileordermission() {
    return fileordermission;
  }

  public void setFileordermission(String fileordermission) {
    this.fileordermission = fileordermission;
  }

}
