package com.ains.myspring.models.modules.mission;

import java.sql.Date;
import com.ains.myspring.models.modules.Societe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enquete {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idenquete;
  @ManyToOne
  @JoinColumn(name = "idordermission")
  Ordermission ordermission;
  @ManyToOne
  @JoinColumn(name = "idsociete")
  Societe societe;
  int statu;
  String url_fichetechnique;
  Date datefichetechnique;
  String url_convocation;
  Date dateconvocation;
  String urlpvaudition;
  Date datepvaudition;
  String url_pvinfraction;
  Date dateinfraction;
  
  public Enquete() {
  }

  public Enquete(Ordermission ordermission, Societe societe, int statu) {
    this.ordermission = ordermission;
    this.societe = societe;
    this.statu = statu;
  }

  public int getIdenquete() {
    return idenquete;
  }

  public void setIdenquete(int idenquete) {
    this.idenquete = idenquete;
  }

  public Ordermission getOrdermission() {
    return ordermission;
  }

  public void setIdordermission(Ordermission ordermission) {
    this.ordermission = ordermission;
  }

  public int getStatu() {
    return statu;
  }

  public void setStatu(int statu) {
    this.statu = statu;
  }

  public Societe getSociete() {
    return societe;
  }

  public void setSociete(Societe societe) {
    this.societe = societe;
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

  public String getUrl_convocation() {
    return url_convocation;
  }

  public void setUrl_convocation(String url_convocation) {
    this.url_convocation = url_convocation;
  }

  public Date getDateconvocation() {
    return dateconvocation;
  }

  public void setDateconvocation(Date dateconvocation) {
    this.dateconvocation = dateconvocation;
  }

  public String getUrlpvaudition() {
    return urlpvaudition;
  }

  public void setUrlpvaudition(String urlpvaudition) {
    this.urlpvaudition = urlpvaudition;
  }

  public Date getDatepvaudition() {
    return datepvaudition;
  }

  public void setDatepvaudition(Date datepvaudition) {
    this.datepvaudition = datepvaudition;
  }

  public String getUrl_pvinfraction() {
    return url_pvinfraction;
  }

  public void setUrl_pvinfraction(String url_pvinfraction) {
    this.url_pvinfraction = url_pvinfraction;
  }

  public Date getDateinfraction() {
    return dateinfraction;
  }

  public void setDateinfraction(Date dateinfraction) {
    this.dateinfraction = dateinfraction;
  }
}
