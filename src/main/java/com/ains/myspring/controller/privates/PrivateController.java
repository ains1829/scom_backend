package com.ains.myspring.controller.privates;

import java.sql.Date;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import com.ains.myspring.models.admin.Account;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.jsontoclass.JsonAdministration;
import com.ains.myspring.models.jsontoclass.JsonSociete;
import com.ains.myspring.models.jsontoclass.equipe.Jsonequipe;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.signal.Signal;
import com.ains.myspring.services.admin.AccountService;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.equipe.EquipeService;
import com.ains.myspring.services.modules.lieu.DistrictService;
import com.ains.myspring.services.modules.signal.SignalService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/scomadminstration")
@CrossOrigin("*")
public class PrivateController {
  @Autowired
  private AccountService _serviceAccount;
  @Autowired
  private SocieteService _serviceSociete;
  @Autowired
  private EquipeService _serviceEquipe;
  @Autowired
  private AdministrationService _serviceAdministration;
  @Autowired
  private DistrictService _serviceDistrict;
  @Autowired
  private Token token_email;
  @Autowired
  private SignalService _serviceSignal;

  @PreAuthorize("hasRole('DR') or hasRole('DT') or hasRole('CHEF_EQUIPE') or hasRole('SG') or hasRole('DSI') or hasRole('DG')")
  @GetMapping("/getrole")
  public ResponseEntity<?> getRole() {
    try {
      return ResponseEntity.ok(new ReturnMap(200, token_email.getRole()));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @GetMapping("/account_validate")
  public ResponseEntity<?> getAccountValidate(@RequestParam("page") int page) {
    try {
      HashMap<String, Object> mapping = new HashMap<>();
      Page<Account> account = _serviceAccount.getListAccountValidate(page);
      mapping.put("hasnext", account.hasNext());
      mapping.put("hasprevious", account.hasPrevious());
      mapping.put("data", account.getContent());
      mapping.put("nombrepage", account.getTotalPages());
      mapping.put("page", page);
      return ResponseEntity.ok(new ReturnMap(200, mapping));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @GetMapping("/account_Novalidate")
  public ResponseEntity<?> getAccountNoValidate(@RequestParam("page") int page) {
    try {
      HashMap<String, Object> mapping = new HashMap<>();
      Page<Account> account = _serviceAccount.getListAccountNoValidate(page);
      mapping.put("hasnext", account.hasNext());
      mapping.put("hasprevious", account.hasPrevious());
      mapping.put("data", account.getContent());
      mapping.put("nombrepage", account.getTotalPages());
      mapping.put("page", page);
      return ResponseEntity.ok(new ReturnMap(200, mapping));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @GetMapping("/validateaccount_dsi")
  public ResponseEntity<?> ValidateAccountDsi(@RequestParam("idaccount") int idaccoount) {
    try {
      _serviceAccount.ValidateAccount(idaccoount);
      return ResponseEntity.ok(new ReturnMap(200, "account valide"));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @PostMapping("/updateperson")
  public ResponseEntity<?> UpdateAdministration(@RequestPart(name = "photo", required = false) MultipartFile photo,
      HttpServletRequest request) {
    try {
      int idAdministration = Integer.valueOf(request.getParameter("idadministration"));
      JsonAdministration admin = new JsonAdministration(request);
      _serviceAdministration.UpdateAdministration(photo, admin, idAdministration);
      return ResponseEntity.ok(new ReturnMap(200, "Modifier"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @PostMapping("/newperson")
  public ResponseEntity<?> NewAdministration(@RequestPart(name = "photo") MultipartFile photo,
      HttpServletRequest request) {
    try {
      JsonAdministration admin = new JsonAdministration(request);
      _serviceAdministration.CreateNewAdministration(photo, admin);
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ReturnMap(200, "Nouveaux"));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @PostMapping("/newSociete")
  public ResponseEntity<?> NewSociete(@RequestPart(name = "photo", required = false) MultipartFile photo,
      @RequestPart("data") JsonSociete societe) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviceSociete.AddNewSociete(photo, societe)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @PostMapping("/createEquipe")
  public ResponseEntity<?> NewEquipe(@RequestBody Jsonequipe newequipe) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    try {
      return ResponseEntity.ok(new ReturnMap(200,
          _serviceEquipe.CreateEquipe(administration.get().getRegion().getIdregion(), newequipe)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/supprime_equipe")
  public ResponseEntity<?> DesactivateEquipe(@RequestParam("idequipe") int idequipe) {
    try {
      _serviceEquipe.DesactivateEquipe(idequipe);
      return ResponseEntity.ok(new ReturnMap(200, "OK"));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/administration_no_H_equipe")
  public ResponseEntity<?> AdministrationNoEquipe() {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200,
              _serviceAdministration.getAdministrationNoEquipe(administration.get().getRegion().getIdregion())));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/equipebyregion")
  public ResponseEntity<?> Equipebyregion() {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, _serviceEquipe.getEquipeByRegion(administration.get().getRegion().getIdregion())));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getdistrictbyregion")
  public ResponseEntity<?> getDistrictbyregion() {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, _serviceDistrict.getDistrictByregion(administration.get().getRegion().getIdregion())));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getSocietebyregion")
  public ResponseEntity<?> getSocietebyregion() {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, _serviceSociete.getSocietebyregion(administration.get().getRegion().getIdregion())));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DG') or hasRole('SG') or hasRole('DSI')")
  @GetMapping("/getSocieteglobalpagination")
  public ResponseEntity<?> getSocieteglobalpagination(
      @RequestParam(name = "page", defaultValue = "0") int pagenumber, @RequestParam(name = "search") String text,
      @RequestParam("idregion") int region, @RequestParam("filter") boolean filtresocieteom,
      @RequestParam(name = "date_begin", required = false) String date_begin,
      @RequestParam(name = "date_end", required = false) String date_end) {
    Page<Societe> societe = _serviceSociete.getSocieteByFiltre(pagenumber, region, text, filtresocieteom, date_begin,
        date_end);
    HashMap<String, Object> mapping = new HashMap<>();
    mapping.put("hasnext", societe.hasNext());
    mapping.put("hasprevious", societe.hasPrevious());
    mapping.put("data", societe.getContent());
    mapping.put("nombrepage", societe.getTotalPages());
    mapping.put("page", pagenumber);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, mapping));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getSocietebyregionpagination")
  public ResponseEntity<?> getSocietebyregionpagination(
      @RequestParam(name = "page", defaultValue = "0") int pagenumber, @RequestParam(name = "search") String text) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    Page<Societe> societe = _serviceSociete.getSocietebyregion(administration.get().getRegion().getIdregion(),
        pagenumber, text);
    HashMap<String, Object> mapping = new HashMap<>();
    mapping.put("hasnext", societe.hasNext());
    mapping.put("hasprevious", societe.hasPrevious());
    mapping.put("data", societe.getContent());
    mapping.put("nombrepage", societe.getTotalPages());
    mapping.put("page", pagenumber);
    try {
      return ResponseEntity
          .ok(new ReturnMap(200, mapping));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getmissionnairebyregion")
  public ResponseEntity<?> getMissionnaireByregion(@RequestParam(name = "page", defaultValue = "0") int pagenumber,
      @RequestParam(name = "text") String text) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    HashMap<String, Object> mapping = new HashMap<>();
    Page<Administration> missionnaire = _serviceAdministration.getMissionnaireByregion(pagenumber,
        administration.get().getRegion().getIdregion(), text);
    mapping.put("hasnext", missionnaire.hasNext());
    mapping.put("hasprevious", missionnaire.hasPrevious());
    mapping.put("data", missionnaire.getContent());
    mapping.put("nombrepage", missionnaire.getTotalPages());
    mapping.put("page", pagenumber);
    return ResponseEntity.ok(new ReturnMap(200, mapping));
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/getSignalbyregion")
  public ResponseEntity<?> getSignalbyregion(@RequestParam(name = "page", defaultValue = "0") int pagenumber,
      @RequestParam(name = "annee") int annee) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    HashMap<String, Object> mapping = new HashMap<>();
    Page<Signal> signal = _serviceSignal.getSignalbyregion(pagenumber,
        annee,
        administration.get().getRegion().getIdregion());
    mapping.put("hasnext", signal.hasNext());
    mapping.put("hasprevious", signal.hasPrevious());
    mapping.put("data", signal.getContent());
    mapping.put("nombrepage", signal.getTotalPages());
    mapping.put("page", pagenumber);
    return ResponseEntity.ok(new ReturnMap(200, mapping));
  }

}
