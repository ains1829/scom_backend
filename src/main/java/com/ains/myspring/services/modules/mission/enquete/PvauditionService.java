package com.ains.myspring.services.modules.mission.enquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.mission.enquete.Pvaudition;
import com.ains.myspring.repository.modules.mission.enquete.PvauditionRepository;

@Service
public class PvauditionService {
  @Autowired
  private PvauditionRepository _contextPvaudition;

  public Pvaudition Save(Pvaudition pvaudition) {
    return _contextPvaudition.save(pvaudition);
  }
}
