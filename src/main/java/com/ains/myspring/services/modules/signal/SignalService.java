package com.ains.myspring.services.modules.signal;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.models.modules.lieu.Region;
import com.ains.myspring.models.modules.signal.Signal;
import com.ains.myspring.repository.modules.signal.SignalRepository;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.lieu.DistrictService;
import com.ains.myspring.services.modules.lieu.RegionService;

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

  @Transactional
  public Signal Save(Signal signal, List<MultipartFile> photo, List<Integer> idanomaly, String email, String number,
      int iddistrict, int societe, String description)
      throws Exception {
    int idsociete = 0;
    Societe societeObject = null;
    if (number.equals("") && email.equals("")) {
      throw new Exception("one contact required");
    }
    if (societe == 0) {
      societeObject = null;
    } else {
      Optional<Societe> societe_class = serviceSociete.getSocieteById(idsociete);
      if (societe_class.isPresent()) {
        societeObject = societe_class.get();
      } else {
        throw new Exception("Societe not found");
      }
    }
    District district = serviceDistrict.getById(iddistrict);
    Date date = new Date(System.currentTimeMillis());
    new Signal(email, number, societeObject, description, date, district);
    return _contextSignal.save(signal);
  }
}
