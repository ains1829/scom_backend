package com.ains.myspring.models.modules.equipe;

import com.ains.myspring.models.admin.Administration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Detailequipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int iddetailequipe;
  int idequipe;
  @ManyToOne
  @JoinColumn(name = "idadministration")
  Administration administration;

  int statustaff;

  public Detailequipe() {
  }

  public Detailequipe(int idequipe, Administration administration, int statustaff) {
    this.idequipe = idequipe;
    this.administration = administration;
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

  public int getStatustaff() {
    return statustaff;
  }

  public void setStatustaff(int statustaff) {
    this.statustaff = statustaff;
  }

  public Administration getAdministration() {
    return administration;
  }

  public void setAdministration(Administration administration) {
    this.administration = administration;
  }
}
