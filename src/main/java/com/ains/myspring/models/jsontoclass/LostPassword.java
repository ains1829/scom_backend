package com.ains.myspring.models.jsontoclass;

public class LostPassword {
  int code;
  String email;
  String password;
  String confirpassword;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirpassword() {
    return confirpassword;
  }

  public void setConfirpassword(String confirpassword) {
    this.confirpassword = confirpassword;
  }
}
