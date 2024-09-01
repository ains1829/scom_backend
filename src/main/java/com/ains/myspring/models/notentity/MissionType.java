package com.ains.myspring.models.notentity;

public class MissionType {
  int idtypeordermission;
  int nombre_mission;

  public int getIdtypeordermission() {
    return idtypeordermission;
  }

  public void setIdtypeordermission(int idtypeordermission) {
    this.idtypeordermission = idtypeordermission;
  }

  public int getNombre_mission() {
    return nombre_mission;
  }

  public void setNombre_mission(int nombre_mission) {
    this.nombre_mission = nombre_mission;
  }

  public MissionType() {

  }

  public MissionType(int idtypeordermission, int nombre_mission) {
    this.idtypeordermission = idtypeordermission;
    this.nombre_mission = nombre_mission;
  }
}
