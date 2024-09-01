package com.ains.myspring.models.notentity;

public class EnqueteStat {
  int enquete_encours;
  int enquete_fini;
  int enquete_clean;
  int enquete_infraction;

  public int getEnquete_encours() {
    return enquete_encours;
  }

  public void setEnquete_encours(int enquete_encours) {
    this.enquete_encours = enquete_encours;
  }

  public int getEnquete_fini() {
    return enquete_fini;
  }

  public void setEnquete_fini(int enquete_fini) {
    this.enquete_fini = enquete_fini;
  }

  public int getEnquete_clean() {
    return enquete_clean;
  }

  public void setEnquete_clean(int enquete_clean) {
    this.enquete_clean = enquete_clean;
  }

  public int getEnquete_infraction() {
    return enquete_infraction;
  }

  public void setEnquete_infraction(int enquete_infraction) {
    this.enquete_infraction = enquete_infraction;
  }

  public EnqueteStat() {
  }

  public EnqueteStat(int enquete_encours, int enquete_fini, int enquete_clean, int enquete_infraction) {
    this.enquete_encours = enquete_encours;
    this.enquete_fini = enquete_fini;
    this.enquete_clean = enquete_clean;
    this.enquete_infraction = enquete_infraction;
  }
}
