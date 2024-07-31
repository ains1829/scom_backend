package com.ains.myspring.models.jsontoclass.order;

import java.util.List;

public class ListCollecteprix {
  int idcollecte;
  List<Integer> idproduct;
  List<Integer> prix;

  public int getIdcollecte() {
    return idcollecte;
  }

  public void setIdcollecte(int idcollecte) {
    this.idcollecte = idcollecte;
  }

  public List<Integer> getIdproduct() {
    return idproduct;
  }

  public void setIdproduct(List<Integer> idproduct) {
    this.idproduct = idproduct;
  }

  public List<Integer> getPrix() {
    return prix;
  }

  public void setPrix(List<Integer> prix) {
    this.prix = prix;
  }
}