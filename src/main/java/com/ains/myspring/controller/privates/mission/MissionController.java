package com.ains.myspring.controller.privates.mission;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.order.MissionJson;
import com.ains.myspring.models.modules.equipe.Equipe;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.models.modules.mission.enquete.Convocation;
import com.ains.myspring.models.modules.mission.enquete.Fichetechnique;
import com.ains.myspring.models.modules.mission.enquete.Pvaudition;
import com.ains.myspring.models.modules.mission.enquete.Pvinfraction;
import com.ains.myspring.security.config.JwtService;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.modules.mission.EnqueteService;
import com.ains.myspring.services.modules.mission.OrdermissionService;
import com.ains.myspring.services.modules.mission.enquete.ConvocationService;
import com.ains.myspring.services.modules.mission.enquete.FichetechniqueService;
import com.ains.myspring.services.modules.mission.enquete.PvauditionService;
import com.ains.myspring.services.modules.mission.enquete.PvinfractionService;

@RequestMapping("/mission")
@RestController
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

  @PreAuthorize("hasRole('DR')")
  @PostMapping("/demandeordre")
  public ResponseEntity<?> DemandeOrdreMission(@RequestBody MissionJson demande) {
    try {
      Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(getEmailUserByToken());
      int region = administration.get().getRegion().getIdregion();
      Ordermission mission = _serviceOrdre.Save(demande, region);
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
      return ResponseEntity
          .ok(new ReturnMap(200,
              _servicePvinfraction.Save(new Pvinfraction(idenquete, equipe.getIdequipe(), url_file, now))));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

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

  private String getEmailUserByToken() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String jwtToken = (String) authentication.getCredentials();
    String email_chef = jwt.getEmailByToken(jwtToken);
    return email_chef;
  }
}
