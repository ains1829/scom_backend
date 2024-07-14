package com.ains.myspring.models.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Anomaly {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idanomaly;
  String nameanomaly;

  public int getIdanomaly() {
    return idanomaly;
  }

  public void setIdanomaly(int idanomaly) {
    this.idanomaly = idanomaly;
  }

  public String getNameanomaly() {
    return nameanomaly;
  }

  public void setNameanomaly(String nameanomaly) {
    this.nameanomaly = nameanomaly;
  }
}
