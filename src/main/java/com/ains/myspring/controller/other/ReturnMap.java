package com.ains.myspring.controller.other;

import java.util.HashMap;

public class ReturnMap {
  int status;
  Object data;

  public ReturnMap(int status, Object object) {
    this.status = status;
    this.data = object;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Object getObject() {
    return data;
  }

  public void setObject(Object object) {
    this.data = object;
  }

  public HashMap<String, Object> Mapping() {
    HashMap<String, Object> mapEntity = new HashMap<>();
    mapEntity.put("status", this.status);
    mapEntity.put("data", this.data);
    return mapEntity;
  }
}
