package com.ains.myspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ains.myspring.models.Etudiant;
import com.ains.myspring.services.EtudiantService;
import java.util.List;

@RestController

@CrossOrigin(origins = "*")
@RequestMapping("/main")
public class MainController {
  @Autowired
  private EtudiantService _serviceEtudiant;

  @GetMapping("/list_etudiant")
  public List<Etudiant> getAllEtudiant() {
    return _serviceEtudiant.getAllEtudiant();
  }

  @PutMapping("/update_etudiant")
  public ResponseEntity<?> UpdateEtudiant(@RequestBody Etudiant update_etudiant) {
    try {
      return new ResponseEntity<>(_serviceEtudiant.UpdateEtudiant(update_etudiant), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/create_etudiant")
  public ResponseEntity<?> CreateEtuant(@RequestBody Etudiant newEtudiant) {
    try {
      return new ResponseEntity<>(_serviceEtudiant.CreateEtudiant(newEtudiant), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  @DeleteMapping("/delete_etudiant")
  public ResponseEntity<?> DeleteEtudiant(@RequestParam("idetudiant") int IdEtudiant) {
    try {
      _serviceEtudiant.DeleteEtudiant(IdEtudiant);
      return new ResponseEntity<>("Etudiant is delete", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }
}
