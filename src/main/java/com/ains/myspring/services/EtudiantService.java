package com.ains.myspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.Etudiant;
import com.ains.myspring.repository.EtudiantRepository;

@Service
public class EtudiantService {
  @Autowired
  private EtudiantRepository _context_etudiant;

  public List<Etudiant> getAllEtudiant() {
    return _context_etudiant.findAll();
  }

  public Etudiant UpdateEtudiant(Etudiant etudiant_new) throws Exception {
    int idetudiant = etudiant_new.getId_etudiant();
    Optional<Etudiant> etudiant = _context_etudiant.findById(idetudiant);
    if (etudiant.isEmpty()) {
      throw new Exception("Etudiant doesn't exist");
    }
    return _context_etudiant.save(etudiant_new);
  }

  public Etudiant CreateEtudiant(Etudiant etudiant) throws Exception {
    return _context_etudiant.save(etudiant);
  }

  public void DeleteEtudiant(int idEtudiant) {
    _context_etudiant.deleteById(idEtudiant);
  }
}
