package com.ains.myspring.services.modules.mission.enquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.modules.mission.enquete.Fichetechnique;
import com.ains.myspring.repository.modules.mission.enquete.FichetechniqueRepository;

@Service
public class FichetechniqueService {
  @Autowired
  private FichetechniqueRepository _fichetechniqueservice;

  public Fichetechnique Save(Fichetechnique fichetechnique) {
    return _fichetechniqueservice.save(fichetechnique);
  }
}
