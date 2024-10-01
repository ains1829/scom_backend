package com.ains.myspring.models.modules.mission;

import java.sql.Date;

import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.models.modules.lieu.Region;

import jakarta.persistence.Column;
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
  @ManyToOne
  @JoinColumn(name = "sender")
  Administration sender;
  @Column(name = "idsociete", nullable = true)
  Integer idsociete;
  String nomsociete;
  String addressesociete;
  @Column(name = "iddistrict", nullable = true)
  Integer iddistrict;
  String nomdistrict;
  String context;
  String lieu_controle;
  Date date_validation_om;

  public Ordermission() {
  }

  public Ordermission(int idtypeordermission, Equipe equipe, Region region, String motifs, String numeroserie,
      Date dateorder, Date datedescente, Integer idsociete, String nomsociete, String addressesociete,
      Integer iddistrict, String nomdistrict, String context, String lieu_controle) {
    this.idtypeordermission = idtypeordermission;
    this.equipe = equipe;
    this.region = region;
    this.motifs = motifs;
    this.numeroserie = numeroserie;
    this.dateorder = dateorder;
    this.datedescente = datedescente;
    this.idsociete = idsociete;
    this.nomsociete = nomsociete;
    this.addressesociete = addressesociete;
    this.iddistrict = iddistrict;
    this.nomdistrict = nomdistrict;
    this.context = context;
    this.lieu_controle = lieu_controle;
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

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public Administration getSender() {
    return sender;
  }

  public void setSender(Administration sender) {
    this.sender = sender;
  }

  public Integer getIddistrict() {
    return iddistrict;
  }

  public void setIddistrict(Integer iddistrict) {
    this.iddistrict = iddistrict;
  }

  public String getNomdistrict() {
    return nomdistrict;
  }

  public void setNomdistrict(String nomDistrict) {
    this.nomdistrict = nomDistrict;
  }

  public Integer getIdsociete() {
    return idsociete;
  }

  public void setIdsociete(Integer idsociete) {
    this.idsociete = idsociete;
  }

  public String getNomsociete() {
    return nomsociete;
  }

  public void setNomsociete(String nomSociete) {
    this.nomsociete = nomSociete;
  }

  public String getAddressesociete() {
    return addressesociete;
  }

  public void setAddresseSociete(String addresseSociete) {
    this.addressesociete = addresseSociete;
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

  public Date getDate_validation_om() {
    return date_validation_om;
  }

  public void setDate_validation_om(Date date_validation_om) {
    this.date_validation_om = date_validation_om;
  }

}
