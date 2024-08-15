package com.ains.myspring.models.modules.equipe;

import java.util.Set;

import com.ains.myspring.models.admin.Administration;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Equipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idequipe;
  String nameequipe;
  @ManyToOne
  @JoinColumn(name = "idadministration")
  Administration chefequipe;
  boolean isactive = true;
  int idregion;
  @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  Set<Detailequipe> detailequipes;

  public Equipe() {
  }

  public Equipe(String nameequipe, Administration chefequipe, int idregion) {
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

  public Administration getChefequipe() {
    return chefequipe;
  }

  public void setChefequipe(Administration chefequipe) {
    this.chefequipe = chefequipe;
  }

  public Set<Detailequipe> getDetailequipes() {
    return detailequipes;
  }

  public void setDetailequipes(Set<Detailequipe> detailequipes) {
    this.detailequipes = detailequipes;
  }

}
