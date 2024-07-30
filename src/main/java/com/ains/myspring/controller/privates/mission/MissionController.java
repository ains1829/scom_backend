package com.ains.myspring.controller.privates.mission;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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
import com.ains.myspring.models.modules.mission.enquete.Fichetechnique;
import com.ains.myspring.security.config.JwtService;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.function.upload.FileUpload;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.modules.mission.EnqueteService;
import com.ains.myspring.services.modules.mission.OrdermissionService;
import com.ains.myspring.services.modules.mission.enquete.FichetechniqueService;

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
  private FileUpload uplad;

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/demandeordre")
  public ResponseEntity<?> DemandeOrdreMission(@RequestBody MissionJson demande) {
    try {
      // get user by token
      int region = 0;
      Ordermission mission = _serviceOrdre.Save(demande, region);
      return ResponseEntity.ok(new ReturnMap(200, mission).Mapping());
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('CHEF_EQUIPE')")
  @PostMapping("/fichetechnique")
  public ResponseEntity<?> FicheTechniques(@RequestParam("n_reference") String ref,
      @RequestParam("enquete") int idenquete, @RequestPart("file_fiche") MultipartFile file_fiche) {
    try {
      Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(getEmailUserByToken());
      Equipe equipe = _serviceEquipe.getEquipeByChef(administration.get().getIdadministration());
      _serviceTechnique.IsRefExist(ref); // check if ref is exists
      _serviveEnquete.FindById(idenquete);
      String url_file = uplad.uploadFile(file_fiche);
      Date now = new Date(System.currentTimeMillis());
      return ResponseEntity.ok(new ReturnMap(200,
          _serviceTechnique.Save(new Fichetechnique(idenquete, equipe.getIdequipe(), ref, url_file, now))));
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
