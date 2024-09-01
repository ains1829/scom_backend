package com.ains.myspring.models.notentity;

public class MissionStat {
  int total_missions;
  int missions_en_cours;
  int missions_fini;

  public int getTotal_missions() {
    return total_missions;
  }

  public void setTotal_missions(int total_missions) {
    this.total_missions = total_missions;
  }

  public int getMissions_en_cours() {
    return missions_en_cours;
  }

  public void setMissions_en_cours(int missions_en_cours) {
    this.missions_en_cours = missions_en_cours;
  }

  public int getMissions_fini() {
    return missions_fini;
  }

  public void setMissions_fini(int missions_fini) {
    this.missions_fini = missions_fini;
  }

  public MissionStat() {

  }

  public MissionStat(int total_missions, int missions_en_cours, int missions_fini) {
    this.total_missions = total_missions;
    this.missions_en_cours = missions_en_cours;
    this.missions_fini = missions_fini;
  }
}
