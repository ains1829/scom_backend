package com.ains.myspring.services.modules.signal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.Anomaly;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.models.modules.signal.Signal;
import com.ains.myspring.models.modules.signal.Signal_cause;
import com.ains.myspring.models.modules.signal.Signal_photo;
import com.ains.myspring.repository.modules.signal.SignalRepository;
import com.ains.myspring.services.modules.AnomalyService;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.lieu.DistrictService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SignalService {
  @Autowired
  private SignalRepository _contextSignal;
  @Autowired
  private DistrictService serviceDistrict;
  @Autowired
  private SocieteService serviceSociete;
  @Autowired
  private Signal_photoService serviceSignalPhoto;
  @Autowired
  private Signal_causeService serviceSignalCause;
  @Autowired
  private AnomalyService serviceAnomaly;

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Signal Save(List<MultipartFile> photo, List<Integer> idanomaly, String email, String number,
      int iddistrict, int societe, String description)
      throws Exception {
    Societe societeObject = null;
    if (number.equals("") && email.equals("")) {
      throw new Exception("one contact required");
    }
    if (societe == 0) {
      societeObject = serviceSociete.getSocieteNotFound();
    } else {
      societeObject = serviceSociete.getSocieteById(societe);
    }
    District district = serviceDistrict.getById(iddistrict);
    Date date = new Date(System.currentTimeMillis());
    Signal signal = _contextSignal.save(new Signal(email, number, societeObject, description, date, district));
    SaveSignalCause(signal, idanomaly);
    SaveSignPhoto(signal, photo);
    return signal;
  }

  @Transactional
  public List<Signal_cause> SaveSignalCause(Signal signal, List<Integer> idanomaly) throws Exception {
    List<Signal_cause> signal_causes = new ArrayList<>();
    for (int i = 0; i < idanomaly.size(); i++) {
      Anomaly anomaly = serviceAnomaly.getById(idanomaly.get(i));
      signal_causes.add(serviceSignalCause.Save(new Signal_cause(signal.getIdsignal(), anomaly)));
    }
    return signal_causes;
  }

  @Transactional
  public List<Signal_photo> SaveSignPhoto(Signal signal, List<MultipartFile> photo) {
    List<Signal_photo> photos = new ArrayList<>();
    if (photo == null) {
      return null;
    }
    for (int i = 0; i < photo.size(); i++) {
      String url_photo = photo.get(i).getOriginalFilename(); // url image in server should here
      photos.add(serviceSignalPhoto.Save(new Signal_photo(signal.getIdsignal(), url_photo)));
    }
    return photos;
  }
}
