package com.ains.myspring.services.modules.equipe;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.jsontoclass.equipe.Jsonequipe;
import com.ains.myspring.models.jsontoclass.equipe.Jsonmembre;
import com.ains.myspring.models.modules.equipe.Detailequipe;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.repository.modules.equipe.EquipeRepository;

@Service
public class EquipeService {
  @Autowired
  private EquipeRepository _contextEquipe;
  @Autowired
  private DetailequipeService _serviceEquipe;

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

  public Equipe CreateEquipe(Jsonequipe equipe, Jsonmembre membre) throws Exception {
    try {
      CheckMembreEquipe(equipe, membre);
      Equipe newEquipe = _contextEquipe.save(
          new Equipe(equipe.getNameequipe(), equipe.getIdadministration(), equipe.getIdregion()));
      Detailequipe chefequipe = new Detailequipe(newEquipe.getIdequipe(), newEquipe.getChefequipe(), 100);
      _serviceEquipe.SaveDetail(chefequipe);
      for (int i = 0; i < membre.getIdadministration().size(); i++) {
        _serviceEquipe.SaveDetail(new Detailequipe(newEquipe.getIdequipe(), membre.getIdadministration().get(i), 0));
      }
      return newEquipe;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Equipe DesactivateEquipe(int idequipe) throws Exception {
    Optional<Equipe> getEquipeById = _contextEquipe.findById(idequipe);
    if (getEquipeById.isPresent()) {
      getEquipeById.get().setIsactive(false);
      _contextEquipe.save(getEquipeById.get());
      _serviceEquipe.DesactivateAccountChef(getEquipeById.get().getChefequipe());
    }
    throw new Exception("Equipe not found");
  }
}
