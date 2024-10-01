package com.ains.myspring.models.notentity;

public class Om {
  int total;
  int valider;
  int non_valider;
  int attente_dg;
  int supprimer;

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

  public int getAttente_dg() {
    return attente_dg;
  }

  public void setAttente_dg(int attente_dg) {
    this.attente_dg = attente_dg;
  }

  public Om() {

  }

  public Om(int total, int valider, int non_valider, int attente_dg, int supprimer) {
    this.total = total;
    this.valider = valider;
    this.non_valider = non_valider;
    this.attente_dg = attente_dg;
    this.supprimer = supprimer;
  }

  public int getSupprimer() {
    return supprimer;
  }

  public void setSupprimer(int supprimer) {
    this.supprimer = supprimer;
  }
}
