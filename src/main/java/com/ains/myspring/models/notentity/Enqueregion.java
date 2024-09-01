package com.ains.myspring.models.notentity;

public class Enqueregion {
  String nameregion;
  String nameprovince;
  int region;
  int t_enquete;
  int encours;
  int fini;
  int clean;
  int infraction;

  public String getNameregion() {
    return nameregion;
  }

  public void setNameregion(String nameregion) {
    this.nameregion = nameregion;
  }

  public String getNameprovince() {
    return nameprovince;
  }

  public void setNameprovince(String nameprovince) {
    this.nameprovince = nameprovince;
  }

  public int getRegion() {
    return region;
  }

  public void setRegion(int region) {
    this.region = region;
  }

  public int getT_enquete() {
    return t_enquete;
  }

  public void setT_enquete(int t_enquete) {
    this.t_enquete = t_enquete;
  }

  public int getEncours() {
    return encours;
  }

  public void setEncours(int encours) {
    this.encours = encours;
  }

  public int getFini() {
    return fini;
  }

  public void setFini(int fini) {
    this.fini = fini;
  }

  public int getClean() {
    return clean;
  }

  public void setClean(int clean) {
    this.clean = clean;
  }

  public int getInfraction() {
    return infraction;
  }

  public void setInfraction(int infraction) {
    this.infraction = infraction;
  }

  public Enqueregion() {
  }

  public Enqueregion(String nameregion, String nameprovince, int region, int t_enquete, int encours, int fini,
      int clean, int infraction) {
    this.nameregion = nameregion;
    this.nameprovince = nameprovince;
    this.region = region;
    this.t_enquete = t_enquete;
    this.encours = encours;
    this.fini = fini;
    this.clean = clean;
    this.infraction = infraction;
  }
}
