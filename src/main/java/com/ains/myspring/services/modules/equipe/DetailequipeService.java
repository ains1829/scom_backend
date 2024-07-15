package com.ains.myspring.services.modules.equipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.equipe.Detailequipe;
import com.ains.myspring.repository.modules.equipe.DetailequipeRepository;

@Service
public class DetailequipeService {
  @Autowired
  private DetailequipeRepository _context;

  public boolean CheckMembreHaveEquipe(int idadministration) throws Exception {
    PersonIsExist(idadministration);
    if (_context.MembreHaveEquipe(idadministration) > 0) {
      throw new Exception("this person have a equipe");
    } else {
      return false;
    }
  }

  public boolean PersonIsExist(int idadministration) throws Exception {
    if (_context.PersonExist(idadministration) > 0) {
      return true;
    } else {
      throw new Exception("Person not found");
    }
  }

  public Detailequipe SaveDetail(Detailequipe detailequipe) throws Exception {
    try {
      if (!CheckMembreHaveEquipe(detailequipe.getIdadministration())) {
        return _context.save(detailequipe);
      } else {
        throw new Exception("Probleme please try later");
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public void DesactivateAccountChef(int administration) {
    _context.DesactiveCompteChefEquipe(administration);
  }
}
