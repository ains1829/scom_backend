package com.ains.myspring.models.modules.equipe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idequipe;
  String nameequipe;
  @Column(name = "idaministration")
  int chefequipe;
  boolean isactive;
  int idregion;

  public Equipe() {
  }

  public Equipe(String nameequipe, int chefequipe, int idregion) {
    this.nameequipe = nameequipe;
    this.chefequipe = chefequipe;
    this.idregion = idregion;
  }

  public int getIdequipe() {
    return idequipe;
  }

  public void setIdequipe(int idequipe) {
    this.idequipe = idequipe;
  }

  public String getNameequipe() {
    return nameequipe;
  }

  public void setNameequipe(String nameequipe) {
    this.nameequipe = nameequipe;
  }

  public int getChefequipe() {
    return chefequipe;
  }

  public void setChefequipe(int chefequipe) {
    this.chefequipe = chefequipe;
  }

  public boolean isIsactive() {
    return isactive;
  }

  public void setIsactive(boolean isactive) {
    this.isactive = isactive;
  }

  public int getIdregion() {
    return idregion;
  }

  public void setIdregion(int idregion) {
    this.idregion = idregion;
  }

}
