package com.ains.myspring.models.admin;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idaccount;
  @ManyToOne
  @JoinColumn(name = "idadministration")
  Administration administration;
  String email;
  String password;
  boolean accountvalidate;
  @ManyToOne
  @JoinColumn(name = "idprofil")
  Profil profil;
  Date datevalidate;
  boolean chefequipe;
  boolean isactive = true;

  public Account() {
  }

  public Account(Administration administration, String email, String password, Profil profil) {
    this.administration = administration;
    this.email = email;
    this.password = password;
    this.profil = profil;
  }

  public int getIdaccount() {
    return idaccount;
  }

  public void setIdaccount(int idaccount) {
    this.idaccount = idaccount;
  }

  public Administration getAdministration() {
    return administration;
  }

  public void setAdministration(Administration administration) {
    this.administration = administration;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isAccountvalidate() {
    return accountvalidate;
  }

  public void setAccountvalidate(boolean accountvalidate) {
    this.accountvalidate = accountvalidate;
  }

  public Profil getProfil() {
    return profil;
  }

  public void setProfil(Profil profil) {
    this.profil = profil;
  }

  public Date getDatevalidate() {
    return datevalidate;
  }

  public void setDatevalidate(Date datevalidate) {
    this.datevalidate = datevalidate;
  }

  public boolean isChefequipe() {
    return chefequipe;
  }

  public void setChefequipe(boolean chefequipe) {
    this.chefequipe = chefequipe;
  }

  public boolean isIsactive() {
    return isactive;
  }

  public void setIsactive(boolean isactive) {
    this.isactive = isactive;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_" + profil.getNameprofil()));
    if (chefequipe) {
      authorities.add(new SimpleGrantedAuthority("ROLE_CHEF_EQUIPE"));
    }
    return authorities;
  }

  @Override
  public String getUsername() {
    return email;
  }
}
