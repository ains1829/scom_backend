package com.ains.myspring.services.modules.signal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.signal.Signal_cause;
import com.ains.myspring.repository.modules.signal.Signal_causeRepository;

@Service
public class Signal_causeService {
  @Autowired
  private Signal_causeRepository _contextSignalCause;

  public Signal_cause Save(Signal_cause cause) {
    return _contextSignalCause.save(cause);
  }
}
