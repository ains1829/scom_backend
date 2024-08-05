package com.ains.myspring.services.modules.signal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.signal.Signal_photo;
import com.ains.myspring.repository.modules.signal.Signal_photoRepository;

@Service
public class Signal_photoService {
  @Autowired
  private Signal_photoRepository _contextSignalPhoto;

  public Signal_photo Save(Signal_photo Signal_photo) {
    return _contextSignalPhoto.save(Signal_photo);
  }
}
