package com.ains.myspring.models.modules.signal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Signal_photo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idsignal_photo;
  int idsignal;
  String url_photo;

  public Signal_photo() {
  }

  public int getIdsignal_photo() {
    return idsignal_photo;
  }

  public void setIdsignal_photo(int idsignal_photo) {
    this.idsignal_photo = idsignal_photo;
  }

  public int getIdsignal() {
    return idsignal;
  }

  public void setIdsignal(int idsignal) {
    this.idsignal = idsignal;
  }

  public String getUrl_photo() {
    return url_photo;
  }

  public void setUrl_photo(String url_photo) {
    this.url_photo = url_photo;
  }
}
