package com.ains.myspring.models.jsontoclass.order;

import java.util.List;

public class CollecteJson {
  int id;
  List<String> Prix;
  List<String> observations;

  public List<String> getObservations() {
    return observations;
  }

  public void setObservations(List<String> observations) {
    this.observations = observations;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<String> getPrix() {
    return Prix;
  }

  public void setPrix(List<String> prix) {
    Prix = prix;
  }

}
