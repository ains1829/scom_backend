package com.ains.myspring.services.modules.mission;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.mission.Enquete;
import com.ains.myspring.repository.modules.mission.EnqueteRepository;

@Service
public class EnqueteService {
  @Autowired
  private EnqueteRepository _contextEnquete;

  public Enquete Save(Enquete enquete) {
    return _contextEnquete.save(enquete);
  }

  public Enquete FindById(int idenquete) throws Exception {
    Optional<Enquete> enquete = _contextEnquete.findById(idenquete);
    if (enquete.isPresent()) {
      return enquete.get();
    }
    throw new Exception("Enquete not found");
  }
}
