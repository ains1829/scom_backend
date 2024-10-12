package com.ains.myspring.controller.privates.stat;

import java.time.Year;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ains.myspring.controller.Token;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.modules.equipe.DetailequipeService;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.noentity.StatMissionservice;
import com.ains.myspring.services.noentity.StatSignalementService;

@RestController
@RequestMapping("/statistique")
@CrossOrigin("*")
public class StatController {
  @Autowired
  private StatMissionservice _serviceStat;
  @Autowired
  private Token tokenemail;
  @Autowired
  private AdministrationService _serviceAdministration;
  @Autowired
  private EquipeService _serviceEquipe;
  @Autowired
  private StatSignalementService _serviceSignalement;
  @Autowired
  private DetailequipeService _serviceDetailequipe;

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/repartitionanomalybyregion")
  public ResponseEntity<?> getRepartitionAnomalybyyear(@RequestParam("annee") int annee) {
    Optional<Administration> administration = _serviceAdministration
        .getAdministrationByEmail(tokenemail.getEmailUserByToken());
    return ResponseEntity.ok(new ReturnMap(200,
        _serviceSignalement.getRepartitionanomalybyregion(annee, administration.get().getRegion().getIdregion())));
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/ombyregion")
  public ResponseEntity<?> geOmByregion() {
    Optional<Administration> administration = _serviceAdministration
        .getAdministrationByEmail(tokenemail.getEmailUserByToken());
    return ResponseEntity
        .ok(new ReturnMap(200, _serviceStat.getOmByregion(administration.get().getRegion().getIdregion())));
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/signalementbymonthbyregion")
  public ResponseEntity<?> getStatsignalementbyregionbymonth(@RequestParam("annee") int annee) {
    Optional<Administration> administration = _serviceAdministration
        .getAdministrationByEmail(tokenemail.getEmailUserByToken());
    return ResponseEntity.ok(new ReturnMap(200, _serviceSignalement.getStatSignalementbyregionbymonth(annee,
        administration.get().getRegion().getIdregion())));
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/repartitionanomalyglobal")
  public ResponseEntity<?> getRepartitionAnomaly(@RequestParam("annee") int annee) {
    return ResponseEntity.ok(new ReturnMap(200, _serviceSignalement.getRepartitionanomaly(annee)));
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/signalementbymonthglobal")
  public ResponseEntity<?> getStatsignalementbymonth(@RequestParam("annee") int annee) {
    return ResponseEntity.ok(new ReturnMap(200, _serviceSignalement.getStatSignalementbymonth(annee)));
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/signalementbyregion")
  public ResponseEntity<?> getStatsignalementbyregion(@RequestParam("annee") int annee) {
    return ResponseEntity.ok(new ReturnMap(200, _serviceSignalement.getStatsignalementbyregion(annee)));
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/statmission_month_type")
  public ResponseEntity<?> getMissionStatMonthType(@RequestParam("annee") int annee) {
    HashMap<String, Object> data = new HashMap<>();
    data.put("enquete", _serviceStat.getMissionStatBymonth_Type(annee, 1));
    data.put("collecte", _serviceStat.getMissionStatBymonth_Type(annee, 2));
    data.put("autre_suivi", _serviceStat.getMissionStatBymonth_Type(annee, 3));
    return ResponseEntity.ok(new ReturnMap(annee, data));
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/statmission_month")
  public ResponseEntity<?> getMissionStatMonth(@RequestParam("annee") int annee) {
    return ResponseEntity.ok(new ReturnMap(annee, _serviceStat.getMissionStatBymonth(annee)));
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG')")
  @GetMapping("/om_stat")
  public ResponseEntity<?> getOmstat() {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviceStat.getOm()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/missionprogressingbyregion")
  public ResponseEntity<?> getMissionprogressinganeebyregion(@RequestParam(name = "annee") int annee) {
    try {
      Optional<Administration> administration = _serviceAdministration
          .getAdministrationByEmail(tokenemail.getEmailUserByToken());
      return ResponseEntity
          .ok(new ReturnMap(200,
              _serviceStat.getMissionProgressionByregion(administration.get().getRegion().getIdregion(), annee)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/missionbyregionbytypemission")
  public ResponseEntity<?> getMissionbytypebyaneebyregion(@RequestParam(name = "typemission") int idtypeordermission,
      @RequestParam(name = "annee") int annee) {
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, _serviceStat.getMissionglobalbytypemission(idtypeordermission, annee)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/enquetestatglobalbyregion")
  public ResponseEntity<?> getEnqueteglobalbyregion(@RequestParam(name = "date") int annee) {
    try {
      if (annee == 0) {
        annee = Year.now().getValue();
      }
      return ResponseEntity.ok(new ReturnMap(200, _serviceStat.getEnqueteglobalbyregion(annee)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/enquetestatglobal")
  public ResponseEntity<?> getEnqueteglobalReponse(@RequestParam(name = "date") int annee) {
    try {
      if (annee == 0) {
        annee = Year.now().getValue();
      }
      return ResponseEntity.ok(new ReturnMap(200, _serviceStat.getEnqueteglobal(annee)));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/missionstatglobal")
  public ResponseEntity<?> getStatGlobalReponse(@RequestParam(name = "date") int annee) {
    try {
      if (annee == 0) {
        annee = Year.now().getValue();
      }
      return ResponseEntity.ok(new ReturnMap(200, _serviceStat.getStatglobalMission(annee)));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI')")
  @GetMapping("/missiontypeglobal")
  public ResponseEntity<?> getMissionTypeGlobalReponse(@RequestParam(name = "date") int annee) {
    try {
      if (annee == 0) {
        annee = Year.now().getValue();
      }
      return ResponseEntity.ok(new ReturnMap(200, _serviceStat.getMissionglobalTypes(annee)));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @GetMapping("/missionstatbyequipe")
  public ResponseEntity<?> getStatReponsebyEquipe() {
    Optional<Administration> administration = _serviceAdministration
        .getAdministrationByEmail(tokenemail.getEmailUserByToken());
    try {
      Equipe equipe = _serviceEquipe.getEquipeByChef(administration.get().getIdadministration());
      return ResponseEntity.ok(new ReturnMap(200, _serviceStat.getMissionByEquipe(equipe.getIdequipe())));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @GetMapping("/stat_typemissionbyequipe")
  public ResponseEntity<?> getTypemissionbyEquipe() {
    Optional<Administration> administration = _serviceAdministration
        .getAdministrationByEmail(tokenemail.getEmailUserByToken());
    try {
      Equipe equipe = _serviceEquipe.getEquipeByChef(administration.get().getIdadministration());
      return ResponseEntity.ok(new ReturnMap(200, _serviceStat.getMissionTypesbyEquipe(equipe.getIdequipe())));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @GetMapping("/detailequipe")
  public ResponseEntity<?> getDetailEquipe() {
    Optional<Administration> administration = _serviceAdministration
        .getAdministrationByEmail(tokenemail.getEmailUserByToken());
    try {
      Equipe equipe = _serviceEquipe.getEquipeByChef(administration.get().getIdadministration());
      return ResponseEntity.ok(new ReturnMap(200, _serviceDetailequipe.getDetailEquipe(equipe.getIdequipe())));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }
}
