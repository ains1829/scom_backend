package com.ains.myspring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ains.myspring.models.modules.Product;
import com.ains.myspring.models.modules.Typeproduct;
import com.ains.myspring.models.modules.Unite;
import com.ains.myspring.services.modules.ProductService;
import com.ains.myspring.services.modules.TypeproductService;
import com.ains.myspring.services.modules.UniteService;

@RestController
@RequestMapping("/data")
@CrossOrigin("*")
public class PublicController {
  @Autowired
  private TypeproductService _sreviceType;
  @Autowired
  private ProductService _serviceproduct;
  @Autowired
  private UniteService _serviceUnite;

  @GetMapping("/list-type-product")
  public List<Typeproduct> getListTypeProduct() {
    return _sreviceType.getAllTypeproduct();
  }

  @GetMapping("/product")
  public List<Product> getAllProduct() {
    return _serviceproduct.getAllProduct();
  }

  @GetMapping("/unite")
  public List<Unite> getAllUnite() {
    return _serviceUnite.getAllUnite();
  }
}
