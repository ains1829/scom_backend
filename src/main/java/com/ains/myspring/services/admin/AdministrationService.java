package com.ains.myspring.services.admin;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
  private RegionService _serviceRegion;

  public Optional<Administration> getAdministrationByEmail(String email) {
    return _contextAdminitration.getAdministrationByEmail(email);
  }

  public Administration CreateNewAdministration(MultipartFile photo, JsonAdministration adminjson) throws Exception {
    if (getAdministrationByEmail(adminjson.getEmail()).isPresent()) {
      throw new Exception("Email is already exist");
    }
    Profil getProfil = _serviceProfil.getProfilByid(adminjson.getIdprofil());
    Region region = _serviceRegion.getRegionById(adminjson.getIdregion());
    String urlPhoto = new FileUpload().uploadFile(photo);
    boolean haveaccount = true;
    if (adminjson.getIdprofil() == 6 || adminjson.getIdprofil() == 7) {
      haveaccount = false;
    }
    return new Administration(adminjson.getNameadministration(),
        adminjson.getMatricule(), adminjson.getEmail(), adminjson.getTelephone(), adminjson.getBirthday(),
        adminjson.getGender(), adminjson.getAddresse(), urlPhoto, getProfil, region, haveaccount);
  }
}
