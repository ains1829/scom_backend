package com.ains.myspring.models.notentity;

public class Ppnprice {
  int province;
  int product;
  double p_moyenne;
  double p_max;
  double p_min;
  int mois;
  int annee;

  public int getProvince() {
    return province;
  }

  public void setProvince(int province) {
    this.province = province;
  }

  public int getProduct() {
    return product;
  }

  public void setProduct(int product) {
    this.product = product;
  }

  public double getP_moyenne() {
    return p_moyenne;
  }

  public void setP_moyenne(double p_moyenne) {
    this.p_moyenne = p_moyenne;
  }

  public double getP_max() {
    return p_max;
  }

  public void setP_max(double p_max) {
    this.p_max = p_max;
  }

  public double getP_min() {
    return p_min;
  }

  public void setP_min(double p_min) {
    this.p_min = p_min;
  }

  public int getMois() {
    return mois;
  }

  public void setMois(int mois) {
    this.mois = mois;
  }

  public int getAnnee() {
    return annee;
  }

  public void setAnnee(int annee) {
    this.annee = annee;
  }
}
