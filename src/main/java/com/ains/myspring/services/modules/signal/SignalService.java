package com.ains.myspring.services.modules.signal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.lieu.Region;
import com.ains.myspring.models.modules.signal.Signal;
import com.ains.myspring.models.modules.signal.Signal_photo;
import com.ains.myspring.repository.modules.signal.SignalRepository;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.function.mail.SendingMail;
import com.ains.myspring.services.modules.AnomalyService;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.lieu.RegionService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SignalService {
  @Autowired
  private SignalRepository _contextSignal;
  @Autowired
  private SocieteService serviceSociete;
  @Autowired
  private Signal_photoService serviceSignalPhoto;
  @Autowired
  private AnomalyService serviceAnomaly;
  @Autowired
  private AdministrationService _serviceAdministration;
  @Autowired
  private SendingMail _servicemail;
  @Autowired
  private RegionService _serviceRegion;

  public Page<Signal> getSignalbyregion(int pagenumber, int annee, int region) {
    int size = 5;
    Pageable page = PageRequest.of(pagenumber, size);
    Page<Signal> signal = _contextSignal.getSignal(annee, region, page);
    for (int i = 0; i < signal.getContent().size(); i++) {
      int idsignal = signal.getContent().get(i).getIdsignal();
      signal.getContent().get(i).setPhoto(serviceSignalPhoto.getPhotoByidSignal(idsignal));
    }
    return _contextSignal.getSignal(annee, region, page);
  }

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Signal Save(List<MultipartFile> photo, int idanomaly, String email, String number,
      int societe, String description, int region, String addresse, String namesociete)
      throws Exception {
    Societe societeObject = null;
    Region region_object = _serviceRegion.getRegionById(region);
    if (number.equals("") && email.equals("")) {
      throw new Exception("one contact required");
    }
    if (societe == 0) {
      societeObject = serviceSociete.getSocieteNotFound();
    } else {
      societeObject = serviceSociete.getSocieteById(societe);
      region = societeObject.getRegion().getIdregion();
      addresse = societeObject.getAddresse();
      namesociete = societeObject.getNamesociete();
    }
    Date date = new Date(System.currentTimeMillis());
    Signal signal = _contextSignal.save(
        new Signal(email, number, societeObject, description, date, idanomaly, region, addresse, namesociete,
            serviceAnomaly.getById(idanomaly).getNameanomaly()));
    SaveSignPhoto(signal, photo);
    Administration administration = null;
    if (_serviceAdministration.getdrbyregion(region) != null) {
      administration = _serviceAdministration.getdrbyregion(region);
    } else {
      administration = _serviceAdministration.getDg();
    }
    _servicemail.SendSignal(namesociete, description, administration.getEmail(), region_object.getNameregion());
    return signal;
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
