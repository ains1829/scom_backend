package com.ains.myspring.controller.privates.mission;

import java.sql.Date;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.order.ListCollecteprix;
import com.ains.myspring.models.jsontoclass.order.MissionJson;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.models.modules.mission.Autresuivi;
import com.ains.myspring.models.modules.mission.Collecte;
import com.ains.myspring.models.modules.mission.Enquete;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.models.modules.mission.enquete.Convocation;
import com.ains.myspring.models.modules.mission.enquete.Fichetechnique;
import com.ains.myspring.models.modules.mission.enquete.Pvaudition;
import com.ains.myspring.models.modules.mission.enquete.Pvinfraction;
import com.ains.myspring.security.config.JwtService;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.modules.mission.AutresuiviService;
import com.ains.myspring.services.modules.mission.CollecteService;
import com.ains.myspring.services.modules.mission.EnqueteService;
import com.ains.myspring.services.modules.mission.OrdermissionService;
import com.ains.myspring.services.modules.mission.collecte.DetailcollecteService;
import com.ains.myspring.services.modules.mission.enquete.ConvocationService;
import com.ains.myspring.services.modules.mission.enquete.FichetechniqueService;
import com.ains.myspring.services.modules.mission.enquete.PvauditionService;
import com.ains.myspring.services.modules.mission.enquete.PvinfractionService;

@RequestMapping("/mission")
@RestController
@CrossOrigin("*")
public class MissionController {
  @Autowired
  private OrdermissionService _serviceOrdre;
  @Autowired
  private FichetechniqueService _serviceTechnique;
  @Autowired
  private EnqueteService _serviveEnquete;
  @Autowired
  private JwtService jwt;
  @Autowired
  private AdministrationService _serviceAdministration;
  @Autowired
  private EquipeService _serviceEquipe;
  @Autowired
  private ConvocationService _serviceConvocation;
  @Autowired
  private PvauditionService _servicePvaudition;
  @Autowired
  private PvinfractionService _servicePvinfraction;
  @Autowired
  private DetailcollecteService _serviceDetailcollecte;
  @Autowired
  private CollecteService _serviceCollecte;
  @Autowired
  private AutresuiviService _serviceAutresuivi;

  @PreAuthorize("hasRole('SG')")
  @PostMapping("/validation_ordre_mission")
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
      String email = getEmailUserByToken();
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

  @PreAuthorize("hasRole('DR')")
  @PostMapping("/demandeordre")
  public ResponseEntity<?> DemandeOrdreMission(@RequestBody MissionJson demande) {
    try {
      Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(getEmailUserByToken());
      int region = administration.get().getRegion().getIdregion();
      Ordermission mission = _serviceOrdre.SaveAll(demande, region, administration.get());
      return ResponseEntity.ok(new ReturnMap(200, mission).Mapping());
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/fichetechnique")
  public ResponseEntity<?> FicheTechniques(@RequestParam("enquete") int idenquete,
      @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(getEmailUserByToken());
      Equipe equipe = _serviceEquipe.getEquipeByChef(administration.get().getIdadministration());
      _serviveEnquete.FindById(idenquete);
      String url_file = "Wait serveur file"; // wait server file
      Date now = new Date(System.currentTimeMillis());
      return ResponseEntity.ok(new ReturnMap(200,
          _serviceTechnique.Save(new Fichetechnique(idenquete, equipe.getIdequipe(), url_file, now))));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/convocation")
  public ResponseEntity<?> ConvocationController(@RequestParam("enquete") int idenquete,
      @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      _serviveEnquete.FindById(idenquete);
      String url_file = "Wait serveur file"; // wait server file
      Date now = new Date(System.currentTimeMillis());
      return ResponseEntity
          .ok(new ReturnMap(200, _serviceConvocation.Save(new Convocation(idenquete, url_file, now))));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/pvaudition")
  public ResponseEntity<?> PvauditionController(@RequestParam("n_reference") String ref,
      @RequestParam("enquete") int idenquete, @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(getEmailUserByToken());
      Equipe equipe = _serviceEquipe.getEquipeByChef(administration.get().getIdadministration());
      _serviceConvocation.RefConvocationExist(ref);
      _serviveEnquete.FindById(idenquete);
      String url_file = "Wait serveur file"; // wait server file
      Date now = new Date(System.currentTimeMillis());
      _serviveEnquete.ChangeStatus(idenquete, 100);
      return ResponseEntity
          .ok(new ReturnMap(200,
              _servicePvaudition.Save(new Pvaudition(idenquete, equipe.getIdequipe(), url_file, now))));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/pvinfraction")
  public ResponseEntity<?> PvInfractionController(@RequestParam("enquete") int idenquete,
      @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(getEmailUserByToken());
      Equipe equipe = _serviceEquipe.getEquipeByChef(administration.get().getIdadministration());
      _serviveEnquete.FindById(idenquete);
      String url_file = "Wait serveur file"; // wait server file
      Date now = new Date(System.currentTimeMillis());
      _serviveEnquete.ChangeStatus(idenquete, 500);
      return ResponseEntity
          .ok(new ReturnMap(200,
              _servicePvinfraction.Save(new Pvinfraction(idenquete, equipe.getIdequipe(), url_file, now))));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/enquete_missionFinished")
  public ResponseEntity<?> EnqueteMissionFinished(@RequestParam("enquete") int idenquete) {
    try {
      Enquete enquete = _serviveEnquete.ChangeStatusMissionFinished(idenquete);
      Ordermission ordermission = _serviceOrdre.getOrderMissionById(enquete.getIdordermission());
      ordermission.setDateorderend(new Date(System.currentTimeMillis()));
      _serviceOrdre.UpdateOrdermission(ordermission);
      return ResponseEntity.ok(new ReturnMap(200, "Mission terminer"));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/collecte_missionFinished")
  public ResponseEntity<?> CollecteMissionFinished(@RequestBody ListCollecteprix liste) {
    try {
      _serviceDetailcollecte.SaveDetail(liste);
      Collecte collecte = _serviceCollecte.getCollecteByid(liste.getIdcollecte());
      Ordermission ordermission = _serviceOrdre.getOrderMissionById(collecte.getIdordermission());
      ordermission.setDateorderend(new Date(System.currentTimeMillis()));
      _serviceOrdre.UpdateOrdermission(ordermission);
      return ResponseEntity.ok(new ReturnMap(200, "Suivi economique terminer"));
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

  private String getEmailUserByToken() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String jwtToken = (String) authentication.getCredentials();
    System.out.println("token  = " + jwtToken);
    String email_chef = jwt.getEmailByToken(jwtToken);
    return email_chef;
  }
}
