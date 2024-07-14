package com.ains.myspring.models.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idproduct;
  @ManyToOne
  @JoinColumn(name = "idtypeproduct")
  Typeproduct typeproduct;
  String nameproduct;

  public Product() {
  }

  public Product(Typeproduct typeproduct, String nameproduct) {
    this.typeproduct = typeproduct;
    this.nameproduct = nameproduct;
  }

  public int getIdproduct() {
    return idproduct;
  }

  public void setIdproduct(int idproduct) {
    this.idproduct = idproduct;
  }

  public Typeproduct getTypeproduct() {
    return typeproduct;
  }

  public void setTypeproduct(Typeproduct typeproduct) {
    this.typeproduct = typeproduct;
  }

  public String getNameproduct() {
    return nameproduct;
  }

  public void setNameproduct(String nameproduct) {
    this.nameproduct = nameproduct;
  }
}
