package com.ains.myspring.models.notentity;

public class MissionType {
  int idtypeordermission;
  int nombre_mission;
  int mission_finished;
  int mission_pending;
  double progressing;

  public int getMission_finished() {
    return mission_finished;
  }

  public void setMission_finished(int mission_finished) {
    this.mission_finished = mission_finished;
  }

  public int getMission_pending() {
    return mission_pending;
  }

  public void setMission_pending(int mission_pending) {
    this.mission_pending = mission_pending;
  }

  public double getProgressing() {
    return progressing;
  }

  public void setProgressing(double progressing) {
    this.progressing = progressing;
  }

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
