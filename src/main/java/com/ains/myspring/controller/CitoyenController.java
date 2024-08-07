package com.ains.myspring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.modules.mission.Enquete;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.services.modules.feedback.FeedbackdescenteService;
import com.ains.myspring.services.modules.mission.EnqueteService;
import com.ains.myspring.services.modules.mission.OrdermissionService;
import com.ains.myspring.services.modules.signal.SignalService;

@RestController
@RequestMapping("/commerce")
public class CitoyenController {
  @Autowired
  private SignalService serviceSignal;
  @Autowired
  private OrdermissionService serviceOrdermission;
  @Autowired
  private EnqueteService serviceEnquete;
  @Autowired
  private FeedbackdescenteService serviceFeedback;

  @PostMapping("signalement")
  public ResponseEntity<?> Signal(@RequestPart(name = "photo", required = false) List<MultipartFile> photo,
      @RequestParam(name = "cause", required = true) List<Integer> idanomaly, String email, String numberphone,
      @RequestParam(name = "idsociete", defaultValue = "0") int idsociete, String description,
      @RequestParam("district") int district) {
    try {
      return ResponseEntity.ok(new ReturnMap(200,
          serviceSignal.Save(photo, idanomaly, email, numberphone, district, idsociete, description)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }

  @GetMapping("feedback")
  public ResponseEntity<?> Feedback(@RequestParam("numero_serie") String n_serie, String feedback,
      @RequestPart(name = "photo", required = false) List<MultipartFile> photo) {
    try {
      Ordermission mission = serviceOrdermission.getOrdermissionByNumeroSerie(n_serie);
      Enquete enquete = serviceEnquete.getEnqueteByOrdermission(mission.getIdordermission());
      return ResponseEntity
          .ok(new ReturnMap(200, serviceFeedback.Save(mission, enquete.getSociete().getIdsociete(), feedback, photo)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }
}
