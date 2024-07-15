package com.ains.myspring.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coderecuperation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idcoderecuperation;
  String email;
  int code;
  LocalDateTime datebegin;
  LocalDateTime dateexpiration;
  boolean isexpired;
  boolean isavailable = true;

  public Coderecuperation() {
  }

  public Coderecuperation(int idcoderecuperation, String email, int code, LocalDateTime datebegin,
      LocalDateTime dateexpiration,
      boolean isexpired) {
    this.idcoderecuperation = idcoderecuperation;
    this.email = email;
    this.code = code;
    this.datebegin = datebegin;
    this.dateexpiration = dateexpiration;
    this.isexpired = isexpired;
  }

  public Coderecuperation(String email, int code, LocalDateTime datebegin, LocalDateTime dateexpiration) {
    this.email = email;
    this.code = code;
    this.datebegin = datebegin;
    this.dateexpiration = dateexpiration;
  }

  public int getIdcoderecuperation() {
    return idcoderecuperation;
  }

  public void setIdcoderecuperation(int idcoderecuperation) {
    this.idcoderecuperation = idcoderecuperation;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public LocalDateTime getDatebegin() {
    return datebegin;
  }

  public void setDatebegin(LocalDateTime datebegin) {
    this.datebegin = datebegin;
  }

  public LocalDateTime getDateexpiration() {
    return dateexpiration;
  }

  public void setDateexpiration(LocalDateTime dateexpiration) {
    this.dateexpiration = dateexpiration;
  }

  public boolean isIsexpired() {
    return isexpired;
  }

  public void setIsexpired(boolean isexpired) {
    this.isexpired = isexpired;
  }

  public boolean isIsavailable() {
    return isavailable;
  }

  public void setIsavailable(boolean isavailable) {
    this.isavailable = isavailable;
  }
}
