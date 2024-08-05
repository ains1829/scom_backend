package com.ains.myspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/commerce")
public class CitoyenController {
  @PostMapping("signalement")
  public String Signal(@RequestPart(name = "photo", required = false) List<MultipartFile> photo,
      @RequestParam(name = "cause", required = false) List<Integer> idanomaly, String email, String numberphone,
      int idsociete, String description, @RequestParam("district") int district) {
    if (photo == null) {
      System.out.println("pas de photo");
    } else {
      for (int i = 0; i < photo.size(); i++) {
        System.out.println(photo.get(i).getOriginalFilename());
      }
    }
    return "salut mon ami";
  }
}
