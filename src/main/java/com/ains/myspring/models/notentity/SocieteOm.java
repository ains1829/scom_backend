package com.ains.myspring.models.notentity;

import java.sql.Date;

public class SocieteOm {
  int idorderdemission;
  String numeroserie;
  Date date_om;

  public int getIdorderdemission() {
    return idorderdemission;
  }

  public void setIdorderdemission(int idorderdemission) {
    this.idorderdemission = idorderdemission;
  }

  public String getNumeroserie() {
    return numeroserie;
  }

  public void setNumeroserie(String numeroserie) {
    this.numeroserie = numeroserie;
  }

  public Date getDate_om() {
    return date_om;
  }

  public void setDate_om(Date date_om) {
    this.date_om = date_om;
  }

}
