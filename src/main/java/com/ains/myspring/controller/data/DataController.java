package com.ains.myspring.controller.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.jsontoclass.module.typeproduct.Jsonproduct;
import com.ains.myspring.services.modules.ProductService;
import com.ains.myspring.services.modules.TypeproductService;

@RestController
@RequestMapping("/data_save")
@CrossOrigin("*")
public class DataController {
  @Autowired
  private TypeproductService _servicetypeproduct;

  @Autowired
  private ProductService _serviceProduct;

  @PreAuthorize("hasRole('DSI')")
  @PostMapping("/savetypeproduct")
  public ResponseEntity<?> SaveTYpeproduct(@RequestBody Jsonproduct jsonproduct) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _servicetypeproduct.Save(jsonproduct)).Mapping());
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(400, e.getMessage()).Mapping());
    }
  }

  @PreAuthorize("hasRole('DSI')")
  @PostMapping("/saveproduct")
  public ResponseEntity<?> Saveproduct(@RequestBody Jsonproduct jsonproduct) {
    try {
      return ResponseEntity.ok(new ReturnMap(200, _serviceProduct.Save(jsonproduct)).Mapping());
    } catch (Exception e) {
      return ResponseEntity.ok(new ReturnMap(400, e.getMessage()).Mapping());
    }
  }
}
