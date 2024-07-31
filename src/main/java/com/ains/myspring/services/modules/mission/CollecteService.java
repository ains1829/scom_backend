package com.ains.myspring.services.modules.mission;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

  public Collecte getCollecteByOrdermission(int order) throws Exception {
    Optional<Collecte> collecte = _contextCollecte.getCollecteByOrdermission(order);
    if (collecte.isPresent()) {
      return collecte.get();
    }
    throw new Exception("Collecte not found");
  }
}
