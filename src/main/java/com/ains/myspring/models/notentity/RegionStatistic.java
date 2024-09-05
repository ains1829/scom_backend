package com.ains.myspring.models.notentity;

public class RegionStatistic {
  String namregion;
  int region;
  int t_mission;
  int missionfini;
  int nissionencours;
  String nameprovince;

  public String getNamregion() {
    return namregion;
  }

  public void setNamregion(String namregion) {
    this.namregion = namregion;
  }

  public int getRegion() {
    return region;
  }

  public void setRegion(int region) {
    this.region = region;
  }

  public int getT_mission() {
    return t_mission;
  }

  public void setT_mission(int t_mission) {
    this.t_mission = t_mission;
  }

  public int getMissionfini() {
    return missionfini;
  }

  public void setMissionfini(int missionfini) {
    this.missionfini = missionfini;
  }

  public int getNissionencours() {
    return nissionencours;
  }

  public void setNissionencours(int nissionencours) {
    this.nissionencours = nissionencours;
  }

  public String getNameprovince() {
    return nameprovince;
  }

  public void setNameprovince(String nameprovince) {
    this.nameprovince = nameprovince;
  }
}
