package com.ains.myspring.models.modules.mission.collecte;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Detailcollecte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int iddetailcollecte;
  int idcollecte;
  int idproduct;
  double prix;

  public Detailcollecte() {

  }

  public Detailcollecte(int idcollecte, int idproduct, double prix) {
    this.idcollecte = idcollecte;
    this.idproduct = idproduct;
    this.prix = prix;
  }

  public int getIddetailcollecte() {
    return iddetailcollecte;
  }

  public void setIddetailcollecte(int iddetailcollecte) {
    this.iddetailcollecte = iddetailcollecte;
  }

  public int getIdcollecte() {
    return idcollecte;
  }

  public void setIdcollecte(int idcollecte) {
    this.idcollecte = idcollecte;
  }

  public int getIdproduct() {
    return idproduct;
  }

  public void setIdproduct(int idproduct) {
    this.idproduct = idproduct;
  }

  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }
}
