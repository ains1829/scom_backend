package com.ains.myspring.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.services.modules.SocieteService;
import com.ains.myspring.services.modules.feedback.FeedbackdescenteService;
import com.ains.myspring.services.modules.mission.OrdermissionService;
import com.ains.myspring.services.modules.signal.SignalService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mic")
public class CitoyenController {
  @Autowired
  private SocieteService _serviceSociete;
  @Autowired
  private SignalService serviceSignal;
  @Autowired
  private OrdermissionService serviceOrdermission;
  @Autowired
  private FeedbackdescenteService serviceFeedback;

  @PostMapping("signalement")
  public ResponseEntity<?> Signal(@RequestPart(name = "photo", required = false) List<MultipartFile> photo,
      @RequestParam(name = "cause", required = true) int idanomaly, String email, String numberphone,
      @RequestParam(name = "idsociete", defaultValue = "0") int idsociete, String description, int region,
      String addresse, String namesociete) {
    try {
      return ResponseEntity.ok(new ReturnMap(200,
          serviceSignal.Save(photo, idanomaly, email, numberphone, idsociete, description, region, addresse,
              namesociete)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @PostMapping("feedback")
  public ResponseEntity<?> Feedback(@RequestParam("numero_serie") String n_serie, String feedback,
      @RequestPart(name = "photo", required = false) List<MultipartFile> photo,
      @RequestParam("contact") String contact, String email) {
    try {
      Ordermission mission = serviceOrdermission.getOrdermissionByNumeroSerie(n_serie);
      return ResponseEntity
          .ok(new ReturnMap(200, serviceFeedback.Save(mission, feedback, photo, contact, email)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @GetMapping("/getSocieteglobalpaginationcitoyen")
  public ResponseEntity<?> getSocieteglobalpagination(
      @RequestParam(name = "page", defaultValue = "0") int pagenumber, @RequestParam(name = "search") String text,
      @RequestParam("idregion") int region) {
    Page<Societe> societe = null;
    if (region == 0) {
      societe = _serviceSociete.getSocieteglobal(pagenumber, text);
    } else {
      societe = _serviceSociete.getSocietebyregion(region, pagenumber, text);
    }
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
}
