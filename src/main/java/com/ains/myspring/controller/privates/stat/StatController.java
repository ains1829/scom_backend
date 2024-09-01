package com.ains.myspring.controller.privates.stat;

import java.time.Year;
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
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.noentity.StatMissionservice;

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

  @PreAuthorize("hasRole('SG') or hasRole('DG')")
  @GetMapping("/enquetestatglobalbyregion")
  public ResponseEntity<?> getEnqueteglobalbyregion(@RequestParam(name = "date") int annee) {
    try {
      if (annee == 0) {
        annee = Year.now().getValue();
      }
      return ResponseEntity.ok(new ReturnMap(200, _serviceStat.getEnqueteglobalbyregion(annee)));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG')")
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

  @PreAuthorize("hasRole('SG') or hasRole('DG')")
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

  @PreAuthorize("hasRole('SG') or hasRole('DG')")
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
}
