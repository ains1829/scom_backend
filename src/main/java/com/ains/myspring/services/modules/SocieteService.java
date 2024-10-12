package com.ains.myspring.services.modules;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ains.myspring.models.jsontoclass.JsonSociete;
import com.ains.myspring.models.jsontoclass.JsonSocieteModify;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.repository.modules.SocieteRepository;
import com.ains.myspring.services.modules.lieu.DistrictService;

@Service
public class SocieteService {
  @Autowired
  private SocieteRepository _contextsociete;
  @Autowired
  private DistrictService _serviceDistrict;

  public boolean NifIsExist(String nif) throws Exception {
    if (_contextsociete.NifIsExist(nif) > 0) {
      throw new Exception("Nif is already exist");
    }
    return false;
  }

  public boolean StatIsExist(String stat) throws Exception {
    if (_contextsociete.StatIsExist(stat) > 0) {
      throw new Exception("Stat is already exist");
    }
    return false;
  }

  public boolean FiscalIsExist(String fiscal) throws Exception {
    if (_contextsociete.FiscalIsExist(fiscal) > 0) {
      throw new Exception("Fiscal is already exist");
    }
    return false;
  }

  public Societe AddNewSociete(MultipartFile logo, JsonSociete societe) throws Exception {
    String url_logo = "";
    if (logo != null) {
      url_logo = logo.getOriginalFilename();
    }
    District district = _serviceDistrict.getById(societe.getIddistrict());
    Societe newSociete = new Societe(societe.getNamesociete(), societe.getDescription(), societe.getNif(),
        societe.getStat(), district.getRegion(), district, societe.getAddresse(),
        societe.getResponsable(), societe.getTelephone(), societe.getNumerofiscal());
    newSociete.setUrl_logo(url_logo);
    FiscalIsExist(newSociete.getNumerofiscal());
    StatIsExist(newSociete.getStat());
    NifIsExist(newSociete.getNif());

    return _contextsociete.save(newSociete);
  }

  public Societe getSocieteById(int idsociete) throws Exception {
    Optional<Societe> getSociete = _contextsociete.findById(idsociete);
    if (getSociete.isEmpty()) {
      throw new Exception("Societe not found");
    }
    return getSociete.get();
  }

  public Societe UpdateSociete(MultipartFile logo, JsonSocieteModify societe) throws Exception {
    District district = _serviceDistrict.getById(societe.getIddistrict());
    String url_logo = "";
    if (logo != null) {
      url_logo = logo.getOriginalFilename();
    }
    Societe societe_modify = getSocieteById(societe.getIdsociete());
    societe_modify.setNamesociete(societe.getNamesociete());
    societe_modify.setDescription(societe.getDescription());
    societe_modify.setNif(societe.getNif());
    societe_modify.setStat(societe.getStat());
    societe_modify.setRegion(district.getRegion());
    societe_modify.setDistrict(district);
    societe_modify.setAddresse(societe.getAddresse());
    societe_modify.setResponsable(societe.getResponsable());
    societe_modify.setTelephone(societe.getTelephone());
    societe_modify.setNumerofiscal(societe.getNumerofiscal());
    societe_modify.setUrl_logo(url_logo);
    if (societe_modify.getNumerofiscal() != societe.getNumerofiscal()) {
      FiscalIsExist(societe.getNumerofiscal());
    }
    if (societe_modify.getStat() != societe.getStat()) {
      StatIsExist(societe.getStat());
    }
    if (societe_modify.getNif() != societe.getNif()) {
      NifIsExist(societe.getNif());
    }
    return _contextsociete.save(societe_modify);
  }

  public Societe DesactivateSociete(int idsociete) throws Exception {
    Societe getSociete;
    try {
      getSociete = getSocieteById(idsociete);
      getSociete.setSocieteactive(false);
      return _contextsociete.save(getSociete);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Societe getSocieteNotFound() throws Exception {
    Optional<Societe> societe = _contextsociete.getSocieteNotFound();
    if (societe.isPresent()) {
      return societe.get();
    }
    throw new Exception("Societe not found");
  }

  public List<Societe> getSocietebyregion(int region) {
    return _contextsociete.getSocieteByRegion(region);
  }

  public Page<Societe> getSocietebyregion(int region, int pagenumber, String text) {
    int size = 15;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextsociete.getSocieteByregion(region, text, page);
  }

  public Page<Societe> getSocieteglobal(int pagenumber, String text) {
    int size = 15;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextsociete.getSocieteglobal(text, page);
  }

  public Page<Societe> getSocieteInMissionBydatebyregion(int region, int pagenumber, String text, Date begin,
      Date end) {
    int size = 15;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextsociete.getSocieteInMissionbydatebyregion(text, region, begin, end, page);
  }

  public Page<Societe> getSocieteInMissionBydateGlobal(int pagenumber, String text, Date begin,
      Date end) {
    int size = 15;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextsociete.getSocieteInMissionbydateGlobal(text, begin, end, page);
  }

  public Page<Societe> getSocieteByFiltre(int pagenumber, int region, String text, boolean searchSocieteOM,
      String begin,
      String end) {
    if (searchSocieteOM) {
      if (region != 0) {
        return getSocieteInMissionBydatebyregion(region, pagenumber, text, Date.valueOf(begin), Date.valueOf(end));
      } else {
        return getSocieteInMissionBydateGlobal(pagenumber, text, Date.valueOf(begin), Date.valueOf(end));
      }
    } else {
      if (region != 0) {
        return getSocietebyregion(region, pagenumber, text);
      }
      return getSocieteglobal(pagenumber, text);
    }
  }
}
