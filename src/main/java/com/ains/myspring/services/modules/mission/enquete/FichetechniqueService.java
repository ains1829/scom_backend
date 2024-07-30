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

  public boolean IsRefExist(String ref) throws Exception {
    int return_ref = _fichetechniqueservice.IsRefExist(ref);
    if (return_ref > 0) {
      throw new Exception("Numero Reference is already exists");
    }
    return false;
  }
}
