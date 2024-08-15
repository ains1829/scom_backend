package com.ains.myspring.services.modules.equipe;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.equipe.Jsonequipe;
import com.ains.myspring.models.jsontoclass.equipe.Jsonmembre;
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

  public boolean CheckMembreEquipe(Jsonequipe equipe, Jsonmembre membre) throws Exception {
    try {
      _serviceEquipe.CheckMembreHaveEquipe(equipe.getIdadministration());
      for (int i = 0; i < membre.getIdadministration().size(); i++) {
        _serviceEquipe.CheckMembreHaveEquipe(membre.getIdadministration().get(i));
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
    return true;
  }

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Equipe CreateEquipe(Jsonequipe equipe, Jsonmembre membre) throws Exception {
    try {
      CheckMembreEquipe(equipe, membre);
      Equipe newEquipe = new Equipe(equipe.getNameequipe(),
          _serviceAdministration.getAdministrationById(equipe.getIdadministration()),
          equipe.getIdregion());
      Set<Detailequipe> detailequipes = new HashSet<>();
      Detailequipe chefequipe = new Detailequipe(newEquipe, newEquipe.getChefequipe().getIdadministration(), 100,
          newEquipe.getChefequipe().getNameadministration(), newEquipe.getChefequipe().getMatricule(),
          newEquipe.getChefequipe().getEmail(), newEquipe.getChefequipe().getProfil().getDescription());
      _serviceEquipe.SaveDetail(chefequipe);
      detailequipes.add(chefequipe);
      for (int i = 0; i < membre.getIdadministration().size(); i++) {
        Administration admin_staff = _serviceAdministration.getAdministrationById(membre.getIdadministration().get(i));
        detailequipes.add(
            new Detailequipe(newEquipe, admin_staff.getIdadministration(), 0, admin_staff.getNameadministration(),
                admin_staff.getMatricule(), admin_staff.getEmail(), admin_staff.getProfil().getDescription()));
      }
      newEquipe.setDetailequipes(detailequipes);
      return _contextEquipe.save(newEquipe);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Equipe DesactivateEquipe(int idequipe) throws Exception {
    Optional<Equipe> getEquipeById = _contextEquipe.findById(idequipe);
    if (getEquipeById.isPresent()) {
      getEquipeById.get().setIsactive(false);
      _contextEquipe.save(getEquipeById.get());
      _serviceEquipe.DesactivateAccountChef(getEquipeById.get().getChefequipe().getIdadministration());
      _serviceAccount.AccountDisabled(getEquipeById.get().getChefequipe().getEmail());
    }
    throw new Exception("Equipe not found");
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
}
