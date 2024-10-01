package com.ains.myspring.models.admin;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import com.ains.myspring.models.modules.lieu.Region;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Administration {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idadministration;
  String nameadministration;
  String matricule;
  String email;
  String telephone;
  int gender;
  String addresse;
  Date birthday;
  String photo;
  @ManyToOne
  @JoinColumn(name = "idprofil")
  Profil profil;
  @ManyToOne
  @JoinColumn(name = "idregion")
  Region region;
  boolean haveaccount = true;
  boolean isactive = true;
  @Transient
  String role;
  @Transient
  int age;

  public Administration() {
  }

  public Administration(String nameadministration, String matricule, String email, String telephone,
      int gender, String addresse, String photo, Profil profil, Region region, boolean haveaccount) {
    this.nameadministration = nameadministration;
    this.matricule = matricule;
    this.email = email;
    this.telephone = telephone;
    this.gender = gender;
    this.addresse = addresse;
    this.photo = photo;
    this.profil = profil;
    this.region = region;
    this.haveaccount = haveaccount;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public String getAddresse() {
    return addresse;
  }

  public void setAddresse(String addresse) {
    this.addresse = addresse;
  }

  public int getIdadministration() {
    return idadministration;
  }

  public void setIdadministration(int idadministration) {
    this.idadministration = idadministration;
  }

  public String getNameadministration() {
    return nameadministration;
  }

  public void setNameadministration(String nameadministration) {
    this.nameadministration = nameadministration;
  }

  public String getMatricule() {
    return matricule;
  }

  public void setMatricule(String matricule) {
    this.matricule = matricule;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Profil getProfil() {
    return profil;
  }

  public void setProfil(Profil profil) {
    this.profil = profil;
  }

  public boolean isHaveaccount() {
    return haveaccount;
  }

  public void setHaveaccount(boolean haveaccount) {
    this.haveaccount = haveaccount;
  }

  public boolean isIsactive() {
    return isactive;
  }

  public void setIsactive(boolean isactive) {
    this.isactive = isactive;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public int getAge() {
    LocalDate birth_day = birthday.toLocalDate();
    LocalDate anne_actuel = new Date(System.currentTimeMillis()).toLocalDate();
    return Period.between(birth_day, anne_actuel).getYears();
  }
}
