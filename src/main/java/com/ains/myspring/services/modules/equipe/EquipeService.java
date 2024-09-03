package com.ains.myspring.services.modules.equipe;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.equipe.Jsonequipe;
import com.ains.myspring.models.modules.equipe.Detailequipe;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.repository.modules.equipe.EquipeRepository;
import com.ains.myspring.services.admin.AccountService;
import com.ains.myspring.services.admin.AdministrationService;

@Service
public class EquipeService {
  @Autowired
  private EquipeRepository _contextEquipe;
  @Autowired
  private DetailequipeService _serviceEquipe;
  @Autowired
  private AccountService _serviceAccount;
  @Autowired
  private AdministrationService _serviceAdministration;

  public Equipe Save(Equipe equipe) {
    return _contextEquipe.save(equipe);
  }

  public boolean CheckMembreEquipe(Jsonequipe equipe) throws Exception {
    try {
      _serviceEquipe.CheckMembreHaveEquipe(equipe.getIdadministration());
      for (int i = 0; i < equipe.getMembres().size(); i++) {
        _serviceEquipe.CheckMembreHaveEquipe(equipe.getMembres().get(i));
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    return true;
  }

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Equipe CreateEquipe(int idregion, Jsonequipe equipe) throws Exception {
    try {
      CheckMembreEquipe(equipe);
      Equipe newEquipe = new Equipe(equipe.getNameequipe(),
          _serviceAdministration.getAdministrationById(equipe.getIdadministration()), idregion);
      Set<Detailequipe> detailequipes = new HashSet<>();
      if (!CheckIdInArray(equipe.getIdadministration(), equipe.getMembres())) {
        detailequipes.add(getDetailequipe(newEquipe));
        for (int i = 0; i < equipe.getMembres().size(); i++) {
          Administration admin_staff = _serviceAdministration.getAdministrationById(equipe.getMembres().get(i));
          detailequipes.add(getDetailequipe(newEquipe, admin_staff, 0));
        }
      } else {
        for (int i = 0; i < equipe.getMembres().size(); i++) {
          Administration admin_staff = _serviceAdministration.getAdministrationById(equipe.getMembres().get(i));
          int status = 0;
          if (equipe.getIdadministration() == equipe.getMembres().get(i)) {
            status = 100;
          }
          detailequipes.add(getDetailequipe(newEquipe, admin_staff, status));
        }
      }
      newEquipe.setDetailequipes(detailequipes);
      return _contextEquipe.save(newEquipe);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public void DesactivateEquipe(int idequipe) throws Exception {
    Optional<Equipe> getEquipeById = _contextEquipe.findById(idequipe);
    if (getEquipeById.isPresent()) {
      if ((getEquipeById.get().getNombre_mission() != getEquipeById.get().getMission_fini()) ||
          CheckEquipeInMission(idequipe)) {
        throw new Exception("Equipe en Mission");
      } else if (getEquipeById.get().getNombre_mission() != 0
          && (getEquipeById.get().getNombre_mission() == getEquipeById.get().getMission_fini())) {
        Desactive(getEquipeById.get());
      } else {
        _contextEquipe.delete(getEquipeById.get());
        _serviceEquipe.DesactivateAccountChef(getEquipeById.get().getChefequipe().getIdadministration());
      }
    } else {
      throw new Exception("Equipe not found");
    }
  }

  public void Desactive(Equipe equipe) {
    equipe.setIsactive(false);
    _contextEquipe.save(equipe);
    _serviceEquipe.DesactivateAccountChef(equipe.getChefequipe().getIdadministration());
    _serviceAccount.AccountDisabled(equipe.getChefequipe().getEmail());
  }

  public boolean CheckEquipeInMission(int idequipe) {
    if (_contextEquipe.EquipeinPeddingMission(idequipe) > 0) {
      return true;
    }
    return false;
  }

  public Equipe getById(int idequipe, int region) throws Exception {
    Optional<Equipe> equipe = _contextEquipe.getEquipeByid(idequipe, region);
    if (equipe.isPresent()) {
      return equipe.get();
    }
    throw new Exception("Equipe not found");
  }

  public Equipe getEquipeByChef(int chef) throws Exception {
    Optional<Equipe> equipe = _contextEquipe.getEquipeByChef(chef);
    if (equipe.isPresent()) {
      return equipe.get();
    }
    throw new Exception("Equipe not found");
  }

  public List<Equipe> getEquipeByRegion(int region) {
    return _contextEquipe.getEquipeByRegion(region);
  }

  private boolean CheckIdInArray(int id, List<Integer> membres) {
    for (int i = 0; i < membres.size(); i++) {
      if (id == membres.get(i)) {
        return true;
      }
    }
    return false;
  }

  private Detailequipe getDetailequipe(Equipe newEquipe) {
    Detailequipe equipe = new Detailequipe(newEquipe, newEquipe.getChefequipe().getIdadministration(), 100,
        newEquipe.getChefequipe().getNameadministration(), newEquipe.getChefequipe().getMatricule(),
        newEquipe.getChefequipe().getEmail(), newEquipe.getChefequipe().getProfil().getDescription(),
        newEquipe.getChefequipe().getPhoto());
    return equipe;
  }

  private Detailequipe getDetailequipe(Equipe newEquipe, Administration admin_staff, int status) {
    return new Detailequipe(newEquipe, admin_staff.getIdadministration(), status,
        admin_staff.getNameadministration(),
        admin_staff.getMatricule(), admin_staff.getEmail(), admin_staff.getProfil().getDescription(),
        admin_staff.getPhoto());
  }
}
