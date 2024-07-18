package com.ains.myspring.models.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Typeproduct {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idtypeproduct;
  String nametypeproduct;
  @ManyToOne
  @JoinColumn(name = "idunite")
  Unite unite;

  public Unite getUnite() {
    return unite;
  }

  public void setUnite(Unite unite) {
    this.unite = unite;
  }

  public int getIdtypeproduct() {
    return idtypeproduct;
  }

  public void setIdtypeproduct(int idtypeproduct) {
    this.idtypeproduct = idtypeproduct;
  }

  public String getNametypeproduct() {
    return nametypeproduct;
  }

  public void setNametypeproduct(String nametypeproduct) {
    this.nametypeproduct = nametypeproduct;
  }
}
