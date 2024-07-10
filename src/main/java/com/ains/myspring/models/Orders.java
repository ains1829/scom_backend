package com.ains.myspring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id_order;
  String client;
  double salary_client;

  public int getId_order() {
    return id_order;
  }

  public void setId_order(int id_order) {
    this.id_order = id_order;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public double getSalary_client() {
    return salary_client;
  }

  public void setSalary_client(double salary_client) {
    this.salary_client = salary_client;
  }

  public Orders() {
  }
}
