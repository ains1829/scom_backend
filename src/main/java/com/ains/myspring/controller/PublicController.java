package com.ains.myspring.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ains.myspring.controller.other.ReturnMap;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.admin.Profil;
import com.ains.myspring.models.modules.Anomaly;
import com.ains.myspring.models.modules.Product;
import com.ains.myspring.models.modules.Typeproduct;
import com.ains.myspring.models.modules.Unite;
import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.models.modules.lieu.Region;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.admin.ProfilService;
import com.ains.myspring.services.modules.AnomalyService;
import com.ains.myspring.services.modules.ProductService;
import com.ains.myspring.services.modules.TypeproductService;
import com.ains.myspring.services.modules.UniteService;
import com.ains.myspring.services.modules.lieu.DistrictService;
import com.ains.myspring.services.modules.lieu.RegionService;
import com.ains.myspring.services.noentity.StatMissionservice;
import jakarta.servlet.http.HttpServletRequest;

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
  @Autowired
  private AdministrationService _serviceAdministration;
  @Autowired
  private ProfilService _serviceProfil;
  @Autowired
  private AnomalyService _serviceAnomaly;
  @Autowired
  private StatMissionservice _statmissionservice;
  @Autowired
  private RegionService _serviceRegion;
  @Autowired
  private DistrictService _serviceDistrict;

  @GetMapping("/regions")
  public List<Region> getRegions() {
    return _serviceRegion.getAll();
  }

  @GetMapping("/district")
  public List<District> getDistrictbyregion(@RequestParam("idregion") int region) {
    return _serviceDistrict.getDistrictByregion(region);
  }

  @GetMapping("/list-type-product")
  public List<Typeproduct> getListTypeProduct() {
    return _sreviceType.getAllTypeproduct();
  }

  @GetMapping("/anomaly")
  public List<Anomaly> getAnomaly() {
    return _serviceAnomaly.getAllAnomaly();
  }

  @GetMapping("/profil")
  public List<Profil> getListProfil() {
    return _serviceProfil.getProfils();
  }

  @GetMapping("/product")
  public List<Product> getAllProduct() {
    return _serviceproduct.getAllProduct();
  }

  @GetMapping("/unite")
  public List<Unite> getAllUnite() {
    return _serviceUnite.getAllUnite();
  }

  @GetMapping("/list-administrator")
  public List<Administration> getAdministration() {
    return _serviceAdministration.getListAdministrator();
  }

  @GetMapping("/ref_societe")
  public ResponseEntity<?> getSocieteReponse(@RequestParam("idsociete") int societe) {
    return ResponseEntity.ok(new ReturnMap(200, _statmissionservice.getRefSociete(societe)));
  }
}
