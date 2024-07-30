package com.ains.myspring.services.modules.mission.enquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.mission.enquete.Convocation;
import com.ains.myspring.repository.modules.mission.enquete.ConvocationRepository;

@Service
public class ConvocationService {
  @Autowired
  private ConvocationRepository _contextConvocation;

  public Convocation Save(Convocation convocation) {
    return _contextConvocation.save(convocation);
  }

  public boolean RefConvocationExist(String ref) throws Exception {
    if (_contextConvocation.RefConvocationExist(ref) > 0) {
      throw new Exception("Reference already exist");
    }
    return false;
  }
}
