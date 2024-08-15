package com.ains.myspring.models.modules.equipe;

import com.ains.myspring.models.admin.Administration;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
  @ManyToOne
  @JoinColumn(name = "idequipe")
  @JsonBackReference
  Equipe equipe;
  int idadministration;
  int statustaff;
  String nameadministration;
  String matricule;
  String email;
  String profil;

  public Detailequipe() {
  }

  public Detailequipe(Equipe equipe, int idadministration, int statustaff, String nameadministration, String matricule,
      String email, String profil) {
    this.equipe = equipe;
    this.idadministration = idadministration;
    this.statustaff = statustaff;
    this.nameadministration = nameadministration;
    this.matricule = matricule;
    this.email = email;
    this.profil = profil;
  }

  public int getIddetailequipe() {
    return iddetailequipe;
  }

  public void setIddetailequipe(int iddetailequipe) {
    this.iddetailequipe = iddetailequipe;
  }

  public Equipe getEquipe() {
    return equipe;
  }

  public void setEquipe(Equipe equipe) {
    this.equipe = equipe;
  }

  public int getStatustaff() {
    return statustaff;
  }

  public void setStatustaff(int statustaff) {
    this.statustaff = statustaff;
  }

  public int getIdadministration() {
    return idadministration;
  }

  public void setIdadministration(int idadministration) {
    this.idadministration = idadministration;
  }

  public String getNameadministration() {
    return nameadministration;
  }

  public void setNameadministration(String nameadministration) {
    this.nameadministration = nameadministration;
  }

  public String getMatricule() {
    return matricule;
  }

  public void setMatricule(String matricule) {
    this.matricule = matricule;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getProfil() {
    return profil;
  }

  public void setProfil(String profil) {
    this.profil = profil;
  }
}
