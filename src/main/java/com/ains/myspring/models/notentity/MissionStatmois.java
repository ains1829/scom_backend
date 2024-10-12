package com.ains.myspring.models.notentity;

public class MissionStatmois {
  int idmois;
  int nb_mission;

  public MissionStatmois() {
  }

  public MissionStatmois(int idmois, int nb_mission) {
    this.idmois = idmois;
    this.nb_mission = nb_mission;
  }

  public int getIdmois() {
    return idmois;
  }

  public void setIdmois(int idmois) {
    this.idmois = idmois;
  }

  public int getNb_mission() {
    return nb_mission;
  }

  public void setNb_mission(int nb_mission) {
    this.nb_mission = nb_mission;
  }
}
