package com.ains.myspring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "etudiant")
public class Etudiant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id_etudiant;
  String name_etudiant;
  String username;
  String password;

  public int getId_etudiant() {
    return id_etudiant;
  }

  public void setId_etudiant(int id_etudiant) {
    this.id_etudiant = id_etudiant;
  }

  public String getName_etudiant() {
    return name_etudiant;
  }

  public void setName_etudiant(String name_etudiant) {
    this.name_etudiant = name_etudiant;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Etudiant() {
  }
}
