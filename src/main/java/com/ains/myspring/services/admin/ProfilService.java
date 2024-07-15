package com.ains.myspring.services.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.admin.Profil;
import com.ains.myspring.repository.ProfilRepository;

@Service
public class ProfilService {
  @Autowired
  private ProfilRepository _contextProfil;

  public Profil getProfilByid(int idprofil) throws Exception {
    Optional<Profil> getProfil = _contextProfil.findById(idprofil);
    if (getProfil.isPresent()) {
      return getProfil.get();
    }
    throw new Exception("Profil not found");
  }
}
