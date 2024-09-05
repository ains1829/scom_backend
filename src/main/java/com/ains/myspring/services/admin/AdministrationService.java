package com.ains.myspring.services.admin;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.admin.Profil;
import com.ains.myspring.models.jsontoclass.JsonAdministration;
import com.ains.myspring.models.modules.lieu.Region;
import com.ains.myspring.repository.AdministrationRepository;
import com.ains.myspring.services.function.upload.FileUpload;
import com.ains.myspring.services.modules.lieu.RegionService;

@Service
public class AdministrationService {
  @Autowired
  private AdministrationRepository _contextAdminitration;
  @Autowired
  private ProfilService _serviceProfil;
  @Autowired
  private FileUpload upload;
  @Autowired
  private RegionService _serviceRegion;

  public Optional<Administration> getAdministrationByEmail(String email) {
    return _contextAdminitration.getAdministrationByEmail(email);
  }

  public Administration getAdministrationById(int idadministration) throws Exception {
    Optional<Administration> administration = _contextAdminitration.findById(idadministration);
    if (administration.isPresent()) {
      return administration.get();
    }
    throw new Exception("Administration not found");
  }

  public Administration CreateNewAdministration(MultipartFile photo, JsonAdministration adminjson) throws Exception {
    if (getAdministrationByEmail(adminjson.getEmail()).isPresent()) {
      throw new Exception("Email is already exist");
    }
    Profil getProfil = _serviceProfil.getProfilByid(adminjson.getIdprofil());
    Region region = _serviceRegion.getRegionById(adminjson.getIdregion());
    String urlPhoto = upload.uploadFile(photo);
    boolean haveaccount = true;
    if (adminjson.getIdprofil() == 6 || adminjson.getIdprofil() == 7) {
      haveaccount = false;
    }
    return _contextAdminitration.save(new Administration(adminjson.getNameadministration(),
        adminjson.getMatricule(), adminjson.getEmail(), adminjson.getTelephone(), adminjson.getBirthday(),
        adminjson.getGender(), adminjson.getAddresse(), urlPhoto, getProfil, region, haveaccount));
  }

  public List<Administration> getListAdministrator() {
    return _contextAdminitration.getAdministration();
  }

  public Page<Administration> getListDirecteurRT(int page) {
    int size = 12;
    Pageable pageable = PageRequest.of(page, size);
    return _contextAdminitration.getDirecteurRT(pageable);
  }

  public Page<Administration> getMissionnaire(int page) {
    int size = 20;
    Pageable pageable = PageRequest.of(page, size);
    return _contextAdminitration.getMissionnaire(pageable);
  }

  public Page<Administration> getMissionnaireByregion(int page, int region, String text) {
    int size = 5;
    Pageable pageable = PageRequest.of(page, size);
    return _contextAdminitration.getMissionnaireByRegion(region, pageable, text);
  }

  public Administration AdministrationDisabled(int idadministration) throws Exception {
    Administration administration = getAdministrationById(idadministration);
    administration.setIsactive(false);
    // appel account_service desactivated account if have;
    return _contextAdminitration.save(administration);
  }

  public List<Administration> getAdministrationNoEquipe(int region) {
    return _contextAdminitration.getAdministrationNoEquipe(region);
  }
}
