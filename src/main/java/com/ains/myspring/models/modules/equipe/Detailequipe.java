package com.ains.myspring.models.modules.equipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Detailequipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int iddetailequipe;
  int idequipe;
  int idadministration;
  int statustaff;

  public Detailequipe() {
  }

  public Detailequipe(int idequipe, int idadministration, int statustaff) {
    this.idequipe = idequipe;
    this.idadministration = idadministration;
    this.statustaff = statustaff;
  }

  public int getIddetailequipe() {
    return iddetailequipe;
  }

  public void setIddetailequipe(int iddetailequipe) {
    this.iddetailequipe = iddetailequipe;
  }

  public int getIdequipe() {
    return idequipe;
  }

  public void setIdequipe(int idequipe) {
    this.idequipe = idequipe;
  }

  public int getIdadministration() {
    return idadministration;
  }

  public void setIdadministration(int idadministration) {
    this.idadministration = idadministration;
  }

  public int getStatustaff() {
    return statustaff;
  }

  public void setStatustaff(int statustaff) {
    this.statustaff = statustaff;
  }
}
