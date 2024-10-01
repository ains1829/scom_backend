package com.ains.myspring.services.modules.signal;

import java.util.List;

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

  public List<Signal_photo> getPhotoByidSignal(int idsignal) {
    return _contextSignalPhoto.getPhotoByIdSignal(idsignal);
  }
}
