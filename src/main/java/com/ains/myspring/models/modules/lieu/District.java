package com.ains.myspring.models.modules.lieu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class District {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int iddistrict;
  @ManyToOne
  @JoinColumn(name = "idregion")
  Region region;
  String nameville;

  public District() {
  }

  public District(Region region, String nameville) {
    this.region = region;
    this.nameville = nameville;
  }

  public int getIddistrict() {
    return iddistrict;
  }

  public void setIddistrict(int iddistrict) {
    this.iddistrict = iddistrict;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public String getNameville() {
    return nameville;
  }

  public void setNameville(String nameville) {
    this.nameville = nameville;
  }
}
