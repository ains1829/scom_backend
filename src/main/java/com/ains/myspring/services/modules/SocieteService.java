package com.ains.myspring.services.modules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.repository.modules.SocieteRepository;

@Service
public class SocieteService {
  @Autowired
  private SocieteRepository _contextsociete;

  public boolean NifIsExist(String nif) throws Exception {
    if (_contextsociete.NifIsExist(nif) > 0) {
      throw new Exception("Nif is already exist");
    }
    return false;
  }

  public boolean StatIsExist(String stat) throws Exception {
    if (_contextsociete.StatIsExist(stat) > 0) {
      throw new Exception("Stat is already exist");
    }
    return false;
  }

  public boolean FiscalIsExist(String fiscal) throws Exception {
    if (_contextsociete.FiscalIsExist(fiscal) > 0) {
      throw new Exception("Fiscal is already exist");
    }
    return false;
  }

  public Societe AddNewSociete(Societe societe) throws Exception {
    try {
      if (!FiscalIsExist(societe.getNumerofiscal()) && !StatIsExist(societe.getStat())
          && !NifIsExist(societe.getNif())) {
        return _contextsociete.save(societe);
      } else {
        throw new Exception("Add societe failed");
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Optional<Societe> getSocieteById(int idsociete) throws Exception {
    Optional<Societe> getSociete = _contextsociete.findById(idsociete);
    if (getSociete.isEmpty()) {
      throw new Exception("Societe not found");
    }
    return getSociete;
  }

  public Societe UpdateSociete(Societe societe) throws Exception {
    try {
      getSocieteById(societe.getIdsociete());
      if (!FiscalIsExist(societe.getNumerofiscal()) && !StatIsExist(societe.getStat())
          && !NifIsExist(societe.getNif())) {
        return _contextsociete.save(societe);
      } else {
        throw new Exception("Update societe failed");
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Societe DesactivateSociete(int idsociete) throws Exception {
    Optional<Societe> getSociete;
    try {
      getSociete = getSocieteById(idsociete);
      getSociete.get().setSocieteactive(false);
      return _contextsociete.save(getSociete.get());
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
