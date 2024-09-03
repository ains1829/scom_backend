package com.ains.myspring.services.modules.mission;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.order.CollecteJson;
import com.ains.myspring.models.jsontoclass.order.MissionJson;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.models.modules.lieu.Region;
import com.ains.myspring.models.modules.mission.Autresuivi;
import com.ains.myspring.models.modules.mission.Collecte;
import com.ains.myspring.models.modules.mission.Enquete;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.repository.modules.mission.OrdermissionRepository;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.modules.lieu.DistrictService;
import com.ains.myspring.services.modules.lieu.RegionService;
import com.ains.myspring.services.modules.mission.collecte.DetailcollecteService;
import com.ains.myspring.services.modules.mission.doc.GenerateOM;

@Service
public class OrdermissionService {
  @Autowired
  private OrdermissionRepository _contextOrder;
  @Autowired
  private RegionService _regionService;
  @Autowired
  private EquipeService _serviceEquipe;
  @Autowired
  private EnqueteService _serviceEnquete;
  @Autowired
  private CollecteService _serviceCollecte;
  @Autowired
  private AutresuiviService _serviceAutresuivi;
  @Autowired
  private SocieteService serviceSociete;
  @Autowired
  private GenerateOM serviceGenerateOM;
  @Autowired
  private DistrictService _serviceDistrict;
  @Autowired
  private DetailcollecteService _servicedetailcollecte;

  public Ordermission UpdateOrdermission(Ordermission ordermission) {
    return _contextOrder.save(ordermission);
  }

  public Ordermission getOrdermissionByNumeroSerie(String numero_serie) throws Exception {
    Optional<Ordermission> mission = _contextOrder.getOrdermissionByNumeroSerie(numero_serie);
    if (mission.isPresent()) {
      return mission.get();
    }
    throw new Exception("Ordermission not found");
  }

  public Ordermission getOrderMissionById(int idorderdemission) throws Exception {
    Optional<Ordermission> ordermission = _contextOrder.findById(idorderdemission);
    if (ordermission.isPresent()) {
      return ordermission.get();
    }
    throw new Exception("Ordermission not found");
  }

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Ordermission CollecteFinished(int idorderdemission, List<CollecteJson> collecte_detail) throws Exception {
    Collecte collecte = _serviceCollecte.getCollecteByOrdermission(idorderdemission);
    collecte.setStatu(200);
    Collecte new_collecte = _serviceCollecte.Save(collecte);
    _servicedetailcollecte.SaveDetail(new_collecte, collecte_detail);
    Ordermission ordermission = getOrderMissionById(idorderdemission);
    Equipe equipe = ordermission.getEquipe();
    equipe.setMission_fini(equipe.getMission_fini() + 1);
    equipe.setMission_encours(equipe.getMission_encours() - 1);
    _serviceEquipe.Save(equipe);
    ordermission.setDateorderend(new Date(System.currentTimeMillis()));
    return UpdateOrdermission(ordermission);
  }

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Ordermission MissionFinished(int idorderdemission) throws Exception {
    Enquete enquete = _serviceEnquete.getEnqueteByOrdermission(idorderdemission);
    Ordermission ordermission = getOrderMissionById(enquete.getOrdermission().getIdordermission());
    Equipe equipe = ordermission.getEquipe();
    equipe.setMission_fini(equipe.getMission_fini() + 1);
    equipe.setMission_encours(equipe.getMission_encours() - 1);
    _serviceEquipe.Save(equipe);
    _serviceEnquete.ChangeStatusMissionFinished(enquete);
    ordermission.setDateorderend(new Date(System.currentTimeMillis()));
    return UpdateOrdermission(ordermission);
  }

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Ordermission SaveAll(MissionJson demaJson, int region, Administration sender) throws Exception {
    Ordermission newordermission = Save(demaJson, region, sender);
    if (demaJson.getIdtypeordermission() == 1) {
      _serviceEnquete.CheckSocieteIsPending(demaJson.getSociete());
      _serviceEnquete.Save(new Enquete(newordermission, serviceSociete.getSocieteById(demaJson.getSociete()), 0));
    } else if (demaJson.getIdtypeordermission() == 2) {
      _serviceCollecte.Save(new Collecte(newordermission, demaJson.getDistrict(), 0,
          new Date(System.currentTimeMillis())));
    } else {
      _serviceAutresuivi.Save(new Autresuivi(newordermission.getIdordermission(), "", 0, demaJson.getDistrict()));
    }
    return newordermission;
  }

  public Ordermission Save(MissionJson demande, int region, Administration sender) throws Exception {
    Equipe equipe = _serviceEquipe.getById(demande.getIdequipe(), region);
    Region _Objectregion = _regionService.getRegionById(region);
    String numero_serie = generateNumeroSerie(_Objectregion.getNumero());
    Date date_now = new Date(System.currentTimeMillis());
    Ordermission ordre = null;
    int status_ordermission = 0;
    if (sender.getProfil().getIdprofil() == 8) {
      status_ordermission = 10;
    }
    if (demande.getIdtypeordermission() == 1) {
      Societe societe = serviceSociete.getSocieteById(demande.getSociete());
      ordre = new Ordermission(demande.getIdtypeordermission(), equipe, _Objectregion, demande.getMotifs(),
          numero_serie, date_now, demande.getDatedescente(), societe.getIdsociete(), societe.getNamesociete(),
          societe.getAddresse(), societe.getDistrict().getIddistrict(), societe.getDistrict().getNameville());
    } else {
      District district = _serviceDistrict.getById(demande.getDistrict());
      ordre = new Ordermission(demande.getIdtypeordermission(), equipe, _Objectregion, demande.getMotifs(),
          numero_serie, date_now, demande.getDatedescente(), null, null,
          null, district.getIddistrict(), district.getNameville());
    }
    ordre.setStatus_validation(status_ordermission);
    ordre.setSender(sender);
    return _contextOrder.save(ordre);
  }

  public Ordermission Basculed(int idordermission) throws Exception {
    Optional<Ordermission> ordermission = _contextOrder.findById(idordermission);
    if (ordermission.isEmpty()) {
      throw new Exception("Order mission not found");
    }
    ordermission.get().setIsbasculed(true);
    return _contextOrder.save(ordermission.get());
  }

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Ordermission Moderation(int idordermission, boolean confirmed) throws Exception {
    Optional<Ordermission> ordermission = _contextOrder.findById(idordermission);
    if (ordermission.isEmpty()) {
      throw new Exception("Order mission not found");
    }
    if (confirmed) {
      ordermission.get().setStatus_validation(100);
      String urlfile = serviceGenerateOM.Ordermission(ordermission.get());
      ordermission.get().setFileordermission(urlfile);
      IfModerationValidate(ordermission.get());
    } else {
      ordermission.get().setStatus_validation(500);
    }
    return _contextOrder.save(ordermission.get());
  }

  public Ordermission ValidationDgdmDt(int idordermission) throws Exception {
    Optional<Ordermission> ordermission = _contextOrder.findById(idordermission);
    if (ordermission.isEmpty()) {
      throw new Exception("Order mission not found");
    }
    ordermission.get().setStatus_validation(0);
    return _contextOrder.save(ordermission.get());
  }

  private void IfModerationValidate(Ordermission ordermission) throws Exception {
    if (ordermission.getIdtypeordermission() == 1) {
      Enquete enquete = _serviceEnquete.getEnqueteByOrdermission(ordermission.getIdordermission());
      enquete.setStatu(10);
      _serviceEnquete.Save(enquete);
    } else if (ordermission.getIdtypeordermission() == 2) {
      Collecte collecte = _serviceCollecte.getCollecteByOrdermission(ordermission.getIdordermission());
      collecte.setStatu(10);
      _serviceCollecte.Save(collecte);
    } else {
      Autresuivi autresuivi = _serviceAutresuivi.getAutresuiviByIdodremission(ordermission.getIdordermission());
      autresuivi.setStatu(10);
      _serviceAutresuivi.Save(autresuivi);
    }
    Equipe equipe = ordermission.getEquipe();
    equipe.setNombre_mission(equipe.getNombre_mission() + 1);
    equipe.setMission_encours(equipe.getMission_encours() + 1);
    _serviceEquipe.Save(equipe);
  }

  public Page<Ordermission> getOrderMissionFilterStatus(int status, int pagenumber) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getOrdermissionFilterstatus(status, page);
  }

  public Page<Ordermission> getOrdermissionMissionFinish(int pagenumber) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getOrdermissionMissionFinish(page);
  }

  public Page<Ordermission> getOrdermissionMissionNotFinish(int pagenumber) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getOrdermissionMissionNotFinish(page);
  }

  public Page<Ordermission> getOrdermissionAll(int pagenumber) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getOrdermissionAll(page);
  }

  public Page<Ordermission> getOrdermissionAllByDrDt(int idregion, int pagenumber) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getOrdermissionAllByDrDt(idregion, page);
  }

  public Page<Ordermission> getMissionAllByDrDt(int idregion, int pagenumber) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getMissionAllByDrDt(idregion, page);
  }

  public Page<Ordermission> getOrdermissionSearchbyMotifs(String searchmotif, int pagenumber) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getOrdermissionSearchbyMotifs(searchmotif, page);
  }

  public Page<Ordermission> getOrdermissionByEquipe(int pagenumber, int equipe) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getOrdermissionByEquipe(equipe, page);
  }

  public String generateNumeroSerie(int regionCode) {
    Date now = new Date(System.currentTimeMillis());
    SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
    SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
    SimpleDateFormat secondFormat = new SimpleDateFormat("ss");
    String yearMonth = yearMonthFormat.format(now);
    String hour = hourFormat.format(now);
    String minute = minuteFormat.format(now);
    String second = secondFormat.format(now);
    String formattedRegionCode = String.format("REG%02d", regionCode);
    String missionOrderNumber = String.format("%s%s%s%s%s", formattedRegionCode, yearMonth, hour, minute,
        second);
    return missionOrderNumber;
  }
}
