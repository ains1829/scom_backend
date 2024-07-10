package com.ains.myspring.models;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Administration implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idadministration;
  String nameadministration;
  String matricule;
  String email;
  String telephone;
  String mdp;
  Date birthday;
  int gender;
  String addresse;
  String photo;
  int idprofil;
  int idregion;
  boolean isactive;

  public Administration(String nameadministration, String matricule, String email, String telephone, String mdp,
      Date birthday, int gender, String addresse, String photo, int idprofil, int idregion) {
    this.nameadministration = nameadministration;
    this.matricule = matricule;
    this.email = email;
    this.telephone = telephone;
    this.mdp = mdp;
    this.birthday = birthday;
    this.gender = gender;
    this.addresse = addresse;
    this.photo = photo;
    this.idprofil = idprofil;
    this.idregion = idregion;
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

  public String getMdp() {
    return mdp;
  }

  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
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

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public int getIdprofil() {
    return idprofil;
  }

  public void setIdprofil(int idprofil) {
    this.idprofil = idprofil;
  }

  public int getIdregion() {
    return idregion;
  }

  public void setIdregion(int idregion) {
    this.idregion = idregion;
  }

  public boolean isIsactive() {
    return isactive;
  }

  public void setIsactive(boolean isactive) {
    this.isactive = isactive;
  }

  public Administration() {
  }

  public Administration(int idadministration, String nameadministration, String matricule, String email,
      String telephone, String mdp, Date birthday, int gender, String addresse, String photo, int idprofil,
      int idregion, boolean isactive) {
    this.idadministration = idadministration;
    this.nameadministration = nameadministration;
    this.matricule = matricule;
    this.email = email;
    this.telephone = telephone;
    this.mdp = mdp;
    this.birthday = birthday;
    this.gender = gender;
    this.addresse = addresse;
    this.photo = photo;
    this.idprofil = idprofil;
    this.idregion = idregion;
    this.isactive = isactive;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("administration"));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return mdp;
  }
}
