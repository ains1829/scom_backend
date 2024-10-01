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

  public Administration getSg() {
    return _contextAdminitration.getSg();
  }

  public Administration getDg() {
    return _contextAdminitration.getDg();
  }

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
    Profil getProfil = _serviceProfil.getProfilByid(adminjson.getIdprofil());
    Region region = _serviceRegion.getRegionById(adminjson.getIdregion());
    CheckInformation(photo, adminjson);
    CheckProfil(getProfil.getIdprofil(), region.getIdregion());
    String urlPhoto = photo.getOriginalFilename(); // upload.uploadFile(photo)
    boolean haveaccount = true;
    if (adminjson.getIdprofil() == 6 || adminjson.getIdprofil() == 7) {
      haveaccount = false;
    }
    return _contextAdminitration.save(new Administration(adminjson.getNameadministration(), adminjson.getMatricule(),
        adminjson.getEmail(), adminjson.getTelephone(),
        adminjson.getGender(), adminjson.getAddresse(), urlPhoto, getProfil, region, haveaccount));
  }

  private void CheckInformation(MultipartFile photo, JsonAdministration adminjson) throws Exception {
    if (photo == null) {
      throw new Exception("Photo required");
    }
    if (_contextAdminitration.CheckAdministrationByEmail(adminjson.getEmail()) == 1) {
      throw new Exception("this email is already exist");
    }
    if (_contextAdminitration.CheckAdministrationByTelephone(adminjson.getTelephone()) == 1) {
      throw new Exception("this telephone is already exist");
    }
    if (_contextAdminitration.CheckAdministrationByMatricule(adminjson.getMatricule()) == 1) {
      throw new Exception("this matricule is already exist");
    }
  }

  private void CheckProfil(int profil, int region) throws Exception {
    if (profil == 1) { // sg
      if (getSg() != null) {
        throw new Exception("SG is exist");
      }
    }
    if (profil == 2) { // dg
      if (getDg() != null) {
        throw new Exception("DG is exist");
      }
    }
    if (profil == 4) { // dr
      if (_contextAdminitration.CountDr(region) == 1) {
        throw new Exception("DR is exist");
      }
    }
    if (profil == 8) { // dt
      if (_contextAdminitration.CountDT(region) == 1) {
        throw new Exception("DT is exist");
      }
    }
    if (profil == 3) { // dsi
      if (_contextAdminitration.CountDsi() == 2) {
        throw new Exception("DSI is exist");
      }
    }
  }

  public Administration UpdateAdministration(MultipartFile photo, JsonAdministration adminjson, int idadministration)
      throws Exception {
    Optional<Administration> administration = _contextAdminitration.findById(idadministration);
    if (administration.isEmpty()) {
      throw new Exception("Administration not found");
    }
    Administration admin = administration.get();
    if (photo != null) {
      // upload.uploadFile(photo)
      String urlPhoto = photo.getOriginalFilename();
      admin.setPhoto(urlPhoto);
    }
    admin.setAddresse(adminjson.getAddresse());
    admin.setEmail(adminjson.getEmail());
    admin.setGender(adminjson.getGender());
    admin.setMatricule(adminjson.getMatricule());
    admin.setNameadministration(adminjson.getNameadministration());
    admin.setTelephone(adminjson.getTelephone());
    Profil getProfil = _serviceProfil.getProfilByid(adminjson.getIdprofil());
    Region region = _serviceRegion.getRegionById(adminjson.getIdregion());
    admin.setProfil(getProfil);
    admin.setRegion(region);

    return _contextAdminitration.save(admin);
  }

  public List<Administration> getListAdministrator() {
    return _contextAdminitration.getAdministration();
  }

  public Page<Administration> getListDirecteurRT(String text, int region, int page) {
    int size = 12;
    Pageable pageable = PageRequest.of(page, size);
    return _contextAdminitration.getDirecteurRT(text, region, pageable);
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
    List<Administration> administrations_byprofil = _contextAdminitration
        .getAdministrationsByprofil(administration.getProfil().getIdprofil());
    if (administrations_byprofil.size() > 1) {
      administration.setIsactive(false);
    } else {
      throw new Exception("tsy ampy oul");
    }
    // appel account_service desactivated account if have;
    return _contextAdminitration.save(administration);
  }

  public Administration getdrbyregion(int region) {
    return _contextAdminitration.Drbyregion(region);
  }

  public List<Administration> getAdministrationNoEquipe(int region) {
    return _contextAdminitration.getAdministrationNoEquipe(region);
  }
}
