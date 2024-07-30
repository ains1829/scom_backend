package com.ains.myspring.models.modules.mission.enquete;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Convocation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idconvocation;
  int idenquete;
  String urlfileconvocation;
  Date dateconvocation;

  public Convocation() {
  }

  public Convocation(int idenquete, String urlfileconvocation, Date date) {
    this.idenquete = idenquete;
    this.urlfileconvocation = urlfileconvocation;
    this.dateconvocation = date;
  }

  public int getIdconvocation() {
    return idconvocation;
  }

  public void setIdconvocation(int idconvocation) {
    this.idconvocation = idconvocation;
  }

  public int getIdenquete() {
    return idenquete;
  }

  public void setIdenquete(int idenquete) {
    this.idenquete = idenquete;
  }

  public String getUrlfileconvocation() {
    return urlfileconvocation;
  }

  public void setUrlfileconvocation(String urlfileconvocation) {
    this.urlfileconvocation = urlfileconvocation;
  }

  public Date getDateconvocation() {
    return dateconvocation;
  }

  public void setDateconvocation(Date dateconvocation) {
    this.dateconvocation = dateconvocation;
  }
}
