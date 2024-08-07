package com.ains.myspring.services.modules.equipe;

import java.util.List;

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

  public List<Detailequipe> getDetailEquipe(int idequipe) {
    return _context.getDetailEquipe(idequipe);
  }

  public boolean PersonIsExist(int idadministration) throws Exception {
    if (_context.PersonExist(idadministration) > 0) {
      return true;
    } else {
      throw new Exception("Person not found");
    }
  }

  public Detailequipe SaveDetail(Detailequipe detailequipe) throws Exception {
    if (!CheckMembreHaveEquipe(detailequipe.getAdministration().getIdadministration())) {
      return _context.save(detailequipe);
    }
    return null;
  }

  public void DesactivateAccountChef(int administration) {
    _context.DesactiveCompteChefEquipe(administration);
  }
}
