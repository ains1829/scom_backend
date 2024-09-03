package com.ains.myspring.models.jsontoclass.equipe;

import java.util.List;

public class Jsonequipe {
  String nameequipe;
  int idadministration;
  List<Integer> membres;

  public String getNameequipe() {
    return nameequipe;
  }

  public void setNameequipe(String nameequipe) {
    this.nameequipe = nameequipe;
  }

  public int getIdadministration() {
    return idadministration;
  }

  public void setIdadministration(int idadministration) {
    this.idadministration = idadministration;
  }

  public List<Integer> getMembres() {
    return membres;
  }

  public void setMembres(List<Integer> membres) {
    this.membres = membres;
  }
}
