package com.ains.myspring.services.modules;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.jsontoclass.module.typeproduct.Jsonproduct;
import com.ains.myspring.models.modules.Typeproduct;
import com.ains.myspring.models.modules.Unite;
import com.ains.myspring.repository.modules.TypeproductRepository;

@Service
public class TypeproductService {
  @Autowired
  private TypeproductRepository _context;
  @Autowired
  private UniteService serviceUnite;

  public List<Typeproduct> getAllTypeproduct() {
    return _context.findAll();
  }

  public Optional<Typeproduct> getTypeProductById(int idtypeproduct) throws Exception {
    if (_context.findById(idtypeproduct).isPresent()) {
      return _context.findById(idtypeproduct);
    } else {
      throw new Exception("typeproduct not found");
    }
  }

  public Typeproduct Save(Jsonproduct productjson) throws Exception {
    Unite unite = serviceUnite.getUniteById(productjson.getId());
    Typeproduct typeproduct = new Typeproduct();
    typeproduct.setNametypeproduct(productjson.getName());
    typeproduct.setUnite(unite);
    return CreateNewType(typeproduct);
  }

  public Typeproduct CreateNewType(Typeproduct typeproduct) throws Exception {
    if (_context.TypeproductIsExist(typeproduct.getNametypeproduct()) > 0) {
      throw new Exception("typeproduct is already exist");
    }
    return _context.save(typeproduct);
  }

  public Typeproduct UpdateTypeproduct(Typeproduct typeproduct) throws Exception {
    Optional<Typeproduct> getLatestProduct = _context.findById(typeproduct.getIdtypeproduct());
    if (getLatestProduct.isPresent()) {
      getLatestProduct.get().setNametypeproduct(typeproduct.getNametypeproduct());
      return _context.save(getLatestProduct.get());
    } else {
      throw new Exception("typeproduct not found");
    }
  }

  public void DeleteTypeproduct(int idtypeproduct) throws Exception {
    try {
      _context.deleteById(idtypeproduct);
    } catch (Exception e) {
      throw new Exception("Type product in conflict");
    }
  }
}
