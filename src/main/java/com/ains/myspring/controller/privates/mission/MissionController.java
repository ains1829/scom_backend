package com.ains.myspring.controller.privates.mission;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.jsontoclass.order.MissionJson;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.services.modules.mission.OrdermissionService;

@RequestMapping("/mission")
@RestController
public class MissionController {
  @Autowired
  private OrdermissionService _serviceOrdre;

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
}
