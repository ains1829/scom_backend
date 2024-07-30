package com.ains.myspring.services.modules.mission.enquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.mission.enquete.Pvinfraction;
import com.ains.myspring.repository.modules.mission.enquete.PvinfractionRepository;

@Service
public class PvinfractionService {
  @Autowired
  private PvinfractionRepository _contextPvinfraction;

  public Pvinfraction Save(Pvinfraction pvinfraction) {
    return _contextPvinfraction.save(pvinfraction);
  }
}
