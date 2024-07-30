package com.ains.myspring.services.modules.mission;

import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.jsontoclass.order.MissionJson;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.models.modules.lieu.Region;
import com.ains.myspring.models.modules.mission.Autresuivi;
import com.ains.myspring.models.modules.mission.Collecte;
import com.ains.myspring.models.modules.mission.Enquete;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.repository.modules.mission.OrdermissionRepository;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.modules.lieu.RegionService;

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
  private SocieteService _serviceSociete;

  public Ordermission Save(MissionJson demande, int region) throws Exception {
    Equipe equipe = _serviceEquipe.getById(demande.getIdequipe(), region);
    Region _Objectregion = _regionService.getRegionById(region);
    String numero_serie = generateNumeroSerie();
    Date date_now = new Date(System.currentTimeMillis());
    Ordermission ordre = new Ordermission(demande.getIdtypeordermission(), equipe, _Objectregion, demande.getMotifs(),
        numero_serie, date_now, demande.getDatedescente(), _serviceSociete.getSocieteById(demande.getSociete()).get());
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

  public Ordermission Moderation(int idordermission, boolean confirmed) throws Exception {
    Optional<Ordermission> ordermission = _contextOrder.findById(idordermission);
    if (ordermission.isEmpty()) {
      throw new Exception("Order mission not found");
    }
    if (confirmed) {
      ordermission.get().setStatus_validation(100);
      // generate documennt
      String urlfile = getUrlFileOrderOfmission();
      ordermission.get().setFileordermission(urlfile);
      IfModerationValidate(ordermission.get());
    } else {
      ordermission.get().setStatus_validation(500);
    }
    return _contextOrder.save(ordermission.get());
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

  public Page<Ordermission> getOrdermissionSearchbyMotifs(String searchmotif, int pagenumber) {
    int size = 20;
    Pageable page = PageRequest.of(pagenumber, size);
    return _contextOrder.getOrdermissionSearchbyMotifs(searchmotif, page);
  }

  private String generateNumeroSerie() {
    return "";
  }

  private String getUrlFileOrderOfmission() {
    return "";
  }

  private void IfModerationValidate(Ordermission ordermission) {
    if (ordermission.getIdtypeordermission() == 1) {
      _serviceEnquete.Save(new Enquete(ordermission.getIdordermission(), ordermission.getSociete().getIdsociete(), 0));
    } else if (ordermission.getIdordermission() == 2) {
      _serviceCollecte.Save(new Collecte());
    } else {
      _serviceAutresuivi.Save(new Autresuivi());
    }
  }
}
