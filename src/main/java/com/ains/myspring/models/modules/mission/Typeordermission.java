package com.ains.myspring.models.modules.mission;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Typeordermission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idtypeordermission;
  String nameordermission;

  public int getIdtypeordermission() {
    return idtypeordermission;
  }

  public void setIdtypeordermission(int idtypeordermission) {
    this.idtypeordermission = idtypeordermission;
  }

  public String getNameordermission() {
    return nameordermission;
  }

  public void setNameordermission(String nameordermission) {
    this.nameordermission = nameordermission;
  }
}