package com.ains.myspring.services.modules.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.modules.mission.Autresuivi;
import com.ains.myspring.repository.modules.mission.AutresuiviRepository;

@Service
public class AutresuiviService {
  @Autowired
  private AutresuiviRepository _contextAutresuivi;

  public Autresuivi Save(Autresuivi autresuivi) {
    return _contextAutresuivi.save(autresuivi);
  }
}
