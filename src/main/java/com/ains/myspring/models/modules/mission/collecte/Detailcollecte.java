package com.ains.myspring.models.modules.mission.collecte;

import com.ains.myspring.models.modules.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Detailcollecte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int iddetailcollecte;
  int idcollecte;
  @ManyToOne
  @JoinColumn(name = "idproduct")
  Product product;
  double prix;
  String observation;

  public Detailcollecte() {
  }

  public Detailcollecte(int idcollecte, Product product, double prix, String observation) {
    this.idcollecte = idcollecte;
    this.product = product;
    this.prix = prix;
    this.observation = observation;
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

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

}
