package com.ains.myspring.services.modules.mission;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.mission.Collecte;
import com.ains.myspring.repository.modules.mission.CollecteRepository;

@Service
public class CollecteService {
  @Autowired
  private CollecteRepository _contextCollecte;

  public Collecte Save(Collecte collecte) {
    return _contextCollecte.save(collecte);
  }

  public Page<Collecte> getCollecteToValidate(int pagenumber) {
    int size = 15;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextCollecte.getCollecteTovalidate(page);
  }

  public Collecte ValidateCollecte(boolean validate, int idcollecte) throws Exception {
    Collecte collecte = getCollecteByid(idcollecte);
    if (collecte.getStatu() == 210 || collecte.getStatu() == 515) {
      throw new Exception("this collecte are already validate");
    }
    if (validate) {
      collecte.setStatu(210);
    } else {
      collecte.setStatu(515);
    }
    collecte.setDatevalidate_collecte(new Date(System.currentTimeMillis()));

    return _contextCollecte.save(collecte);
  }

  public Collecte getCollecteByid(int idcollecte) throws Exception {
    Optional<Collecte> collecte = _contextCollecte.findById(idcollecte);
    if (collecte.isPresent()) {
      return collecte.get();
    }
    throw new Exception("Collecte not found");
  }

  public Collecte getCollecteByOrdermission(int order) throws Exception {
    Optional<Collecte> collecte = _contextCollecte.getCollecteByOrdermission(order);
    if (collecte.isPresent()) {
      return collecte.get();
    }
    throw new Exception("Collecte not found");
  }
}
