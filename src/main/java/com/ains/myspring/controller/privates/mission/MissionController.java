package com.ains.myspring.controller.privates.mission;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.controller.Token;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.order.CollecteJson;
import com.ains.myspring.models.jsontoclass.order.ListCollecteprix;
import com.ains.myspring.models.jsontoclass.order.MissionJson;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.models.modules.mission.Autresuivi;
import com.ains.myspring.models.modules.mission.Collecte;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.modules.mission.AutresuiviService;
import com.ains.myspring.services.modules.mission.CollecteService;
import com.ains.myspring.services.modules.mission.EnqueteService;
import com.ains.myspring.services.modules.mission.OrdermissionService;
import com.ains.myspring.services.modules.mission.collecte.DetailcollecteService;

@RequestMapping("/mission")
@RestController
@CrossOrigin("*")
public class MissionController {
  @Autowired
  private OrdermissionService _serviceOrdre;
  @Autowired
  private EnqueteService _serviveEnquete;
  @Autowired
  private AdministrationService _serviceAdministration;
  @Autowired
  private EquipeService _serviceEquipe;
  @Autowired
  private DetailcollecteService _serviceDetailcollecte;
  @Autowired
  private CollecteService _serviceCollecte;
  @Autowired
  private AutresuiviService _serviceAutresuivi;
  @Autowired
  private Token tokenemail;

  @PreAuthorize("hasRole('SG')")
  @PostMapping("/basculed_ordre_mission")
  public ResponseEntity<?> BasculedOrdremission(@RequestParam("idorderdemission") int id) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviceOrdre.Basculed(id)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('SG')")
  @GetMapping("/validation_ordre_mission")
  public ResponseEntity<?> ModerationOrdremission(@RequestParam("idorderdemission") int id,
      @RequestParam("confirmation") int confirmation) {
    try {
      if (confirmation == 0) {
        _serviceOrdre.Moderation(id, false);
        return ResponseEntity.ok(new ReturnMap(200, "Refuser"));
      } else {
        _serviceOrdre.Moderation(id, true);
        return ResponseEntity.ok(new ReturnMap(200, "Valider"));
      }
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('SG')")
  @GetMapping("/OrderMissionFinish")
  public ResponseEntity<?> OrdermissionFinish(@RequestParam(name = "pagenumber", defaultValue = "0") int page) {
    HashMap<String, Object> mapping = new HashMap<>();
    Page<Ordermission> ordermission = _serviceOrdre.getOrdermissionMissionFinish(page);
    mapping.put("hasnext", ordermission.hasNext());
    mapping.put("hasprevious", ordermission.hasPrevious());
    mapping.put("data", ordermission.getContent());
    mapping.put("nombrepage", ordermission.getTotalPages());
    mapping.put("page", page);
    return ResponseEntity.ok(new ReturnMap(200, mapping));
  }

  @PreAuthorize("hasRole('SG')")
  @GetMapping("/OrderMissionValider")
  public ResponseEntity<?> OrdermissionValider(@RequestParam(name = "pagenumber", defaultValue = "0") int page) {
    HashMap<String, Object> mapping = new HashMap<>();
    Page<Ordermission> ordermission = _serviceOrdre.getOrderMissionFilterStatus(100, page);
    mapping.put("hasnext", ordermission.hasNext());
    mapping.put("hasprevious", ordermission.hasPrevious());
    mapping.put("data", ordermission.getContent());
    mapping.put("nombrepage", ordermission.getTotalPages());
    mapping.put("page", page);
    return ResponseEntity.ok(new ReturnMap(200, mapping));
  }

  @PreAuthorize("hasRole('SG')")
  @GetMapping("/OrderMissionNonValider")
  public ResponseEntity<?> OrdermissionNonValider(@RequestParam(name = "pagenumber", defaultValue = "0") int page) {
    HashMap<String, Object> mapping = new HashMap<>();
    Page<Ordermission> ordermission = _serviceOrdre.getOrderMissionFilterStatus(0, page);
    mapping.put("hasnext", ordermission.hasNext());
    mapping.put("hasprevious", ordermission.hasPrevious());
    mapping.put("data", ordermission.getContent());
    mapping.put("nombrepage", ordermission.getTotalPages());
    mapping.put("page", page);
    return ResponseEntity.ok(new ReturnMap(200, mapping));
  }

  @PreAuthorize("hasRole('SG')")
  @GetMapping("/OrderMissionenAttente")
  public ResponseEntity<?> OrdermissionenAttente(@RequestParam(name = "pagenumber", defaultValue = "0") int page) {
    HashMap<String, Object> mapping = new HashMap<>();
    Page<Ordermission> ordermission = _serviceOrdre.getOrderMissionFilterStatus(500, page);
    mapping.put("hasnext", ordermission.hasNext());
    mapping.put("hasprevious", ordermission.hasPrevious());
    mapping.put("data", ordermission.getContent());
    mapping.put("nombrepage", ordermission.getTotalPages());
    mapping.put("page", page);
    return ResponseEntity.ok(new ReturnMap(200, mapping));
  }

  @PreAuthorize("hasRole('SG')")
  @GetMapping("/OrderMissionAll")
  public ResponseEntity<?> OrdermissionAll(@RequestParam(name = "pagenumber", defaultValue = "0") int page) {
    HashMap<String, Object> mapping = new HashMap<>();
    Page<Ordermission> ordermission = _serviceOrdre.getOrdermissionAll(page);
    mapping.put("hasnext", ordermission.hasNext());
    mapping.put("hasprevious", ordermission.hasPrevious());
    mapping.put("data", ordermission.getContent());
    mapping.put("nombrepage", ordermission.getTotalPages());
    mapping.put("page", page);
    return ResponseEntity.ok(new ReturnMap(200, mapping));
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/OrderMissionAllbydrdt")
  public ResponseEntity<?> OrdermissionAllByDrDt(@RequestParam(name = "pagenumber", defaultValue = "0") int page) {
    try {
      String email = tokenemail.getEmailUserByToken();
      Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
      HashMap<String, Object> mapping = new HashMap<>();
      Page<Ordermission> ordermission = _serviceOrdre
          .getOrdermissionAllByDrDt(administration.get().getIdadministration(), page);
      mapping.put("hasnext", ordermission.hasNext());
      mapping.put("hasprevious", ordermission.hasPrevious());
      mapping.put("data", ordermission.getContent());
      mapping.put("nombrepage", ordermission.getTotalPages());
      mapping.put("page", page);
      return ResponseEntity.ok(new ReturnMap(200, mapping));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @PostMapping("/demandeordre")
  public ResponseEntity<?> DemandeOrdreMission(@RequestBody MissionJson demande) {
    try {
      Optional<Administration> administration = _serviceAdministration
          .getAdministrationByEmail(tokenemail.getEmailUserByToken());
      int region = administration.get().getRegion().getIdregion();
      Ordermission mission = _serviceOrdre.SaveAll(demande, region, administration.get());
      return ResponseEntity.ok(new ReturnMap(200, mission).Mapping());
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @GetMapping("/collectebyordermission")
  public ResponseEntity<?> getOrdermission(@RequestParam("idordermission") int idordermission) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviceCollecte.getCollecteByOrdermission(idordermission)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(200, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @GetMapping("/enqeuetebyordermission")
  public ResponseEntity<?> getEnquete(@RequestParam("idordermission") int idorderdemission) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviveEnquete.getEnqueteByOrdermission(idorderdemission)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @GetMapping("/suivi_mission")
  public ResponseEntity<?> Suivi_mission(@RequestParam(name = "page", defaultValue = "0") int pagenumber) {
    Optional<Administration> administration = _serviceAdministration
        .getAdministrationByEmail(tokenemail.getEmailUserByToken());
    try {
      Equipe equipe = _serviceEquipe.getEquipeByChef(administration.get().getIdadministration());
      HashMap<String, Object> mapping = new HashMap<>();
      Page<Ordermission> ordermission = _serviceOrdre.getOrdermissionByEquipe(pagenumber, equipe.getIdequipe());
      mapping.put("hasnext", ordermission.hasNext());
      mapping.put("hasprevious", ordermission.hasPrevious());
      mapping.put("data", ordermission.getContent());
      mapping.put("nombrepage", ordermission.getTotalPages());
      mapping.put("page", pagenumber);
      return ResponseEntity.ok(new ReturnMap(200, mapping));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/fichetechnique")
  public ResponseEntity<?> FicheTechniques(@RequestParam("idordermission") int idordermission,
      @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviveEnquete.FicheTechnique(idordermission, file_fiche)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/convocation")
  public ResponseEntity<?> ConvocationController(@RequestParam("idordermission") int idordermission,
      @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviveEnquete.Convocation(idordermission, file_fiche)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/pvaudition")
  public ResponseEntity<?> PvauditionController(@RequestParam("idordermission") int idordermission,
      @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviveEnquete.Pvaudition(idordermission, file_fiche)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/pvinfraction")
  public ResponseEntity<?> PvInfractionController(@RequestParam("idordermission") int idordermission,
      @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviveEnquete.Pvinfraction(idordermission, file_fiche)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/enquete_missionFinished")
  public ResponseEntity<?> EnqueteMissionFinished(@RequestParam("idordermission") int idorderdemission) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviceOrdre.MissionFinished(idorderdemission)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/collecte_missionFinished")
  public ResponseEntity<?> CollecteMissionFinished(@RequestParam("id") int idorderdemission,
      @RequestBody List<CollecteJson> liste) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviceOrdre.CollecteFinished(idorderdemission, liste)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @GetMapping("/validate_suivieconomique")
  public ResponseEntity<?> ValidateCollecteEconomique(@RequestParam("idcollecte") int idcollecte) {
    try {
      Collecte collecte = _serviceCollecte.getCollecteByid(idcollecte);
      collecte.setStatu(200);
      _serviceCollecte.Save(collecte);
      return ResponseEntity.ok(new ReturnMap(200, "Suivi economique valider par dsi"));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/autresuivi_finished")
  public ResponseEntity<?> AutreMissionFinished(@RequestParam("idautresuivi") int id,
      @RequestPart("rapport") MultipartFile file) {
    try {
      String url_rapport = "";
      Autresuivi suivi = _serviceAutresuivi.getById(id);
      suivi.setUrlrapport(url_rapport);
      _serviceAutresuivi.Save(suivi);
      Ordermission ordermission = _serviceOrdre.getOrderMissionById(suivi.getIdordermission());
      ordermission.setDateorderend(new Date(System.currentTimeMillis()));
      _serviceOrdre.UpdateOrdermission(ordermission);
      return ResponseEntity.ok(new ReturnMap(200, "Autre suivi terminer"));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }
}
