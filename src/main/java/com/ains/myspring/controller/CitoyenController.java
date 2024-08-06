package com.ains.myspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.services.modules.signal.SignalService;

@RestController
@RequestMapping("/commerce")
public class CitoyenController {
  @Autowired
  private SignalService serviceSignal;

  @PostMapping("signalement")
  public ResponseEntity<?> Signal(@RequestPart(name = "photo", required = false) List<MultipartFile> photo,
      @RequestParam(name = "cause", required = true) List<Integer> idanomaly, String email, String numberphone,
      int idsociete, String description, @RequestParam("district") int district) {
    try {
      return ResponseEntity.ok(new ReturnMap(200,
          serviceSignal.Save(photo, idanomaly, email, numberphone, district, idsociete, description)));
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(500, e.getMessage()));
    }
  }
}
