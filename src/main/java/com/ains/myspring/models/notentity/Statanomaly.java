package com.ains.myspring.models.notentity;

public class Statanomaly {
  int idanomaly;
  int nb_anomaly;
  double percent;
  String nameanomaly;

  public int getIdanomaly() {
    return idanomaly;
  }

  public void setIdanomaly(int idanomaly) {
    this.idanomaly = idanomaly;
  }

  public int getNb_anomaly() {
    return nb_anomaly;
  }

  public void setNb_anomaly(int nb_anomaly) {
    this.nb_anomaly = nb_anomaly;
  }

  public double getPercent() {
    return percent;
  }

  public void setPercent(double percent) {
    this.percent = percent;
  }

  public String getNameanomaly() {
    return nameanomaly;
  }

  public void setNameanomaly(String nameanomaly) {
    this.nameanomaly = nameanomaly;
  }
}
