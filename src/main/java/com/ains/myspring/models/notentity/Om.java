package com.ains.myspring.models.notentity;

public class Om {
  int total;
  int valider;
  int non_valider;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getValider() {
    return valider;
  }

  public void setValider(int valider) {
    this.valider = valider;
  }

  public int getNon_valider() {
    return non_valider;
  }

  public void setNon_valider(int non_valider) {
    this.non_valider = non_valider;
  }

  public Om() {

  }

  public Om(int total, int valider, int non_valider) {
    this.total = total;
    this.valider = valider;
    this.non_valider = non_valider;
  }
}
