package com.ains.myspring.services.modules;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.Unite;
import com.ains.myspring.repository.modules.UniteRepository;

@Service
public class UniteService {
  @Autowired
  private UniteRepository _context;

  public List<Unite> getAllUnite() {
    return _context.findAll();
  }

  public Unite getUniteById(int id) throws Exception{
    Optional<Unite> unite = _context.findById(id);
    if (unite.isPresent()) {
      return unite.get();
    }
    throw new Exception("Unite not found");
  }
}
