package com.ains.myspring.controller.privates.stat;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ains.myspring.controller.Token;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.models.notentity.PpnDistrict;
import com.ains.myspring.models.notentity.PpnRegion;
import com.ains.myspring.services.admin.AdministrationService;
import com.ains.myspring.services.noentity.PpnpriceService;

@RestController
@RequestMapping("/statistique_ppn")
@CrossOrigin("*")
public class PpnController {
  @Autowired
  private PpnpriceService _servicePpn;
  @Autowired
  private Token token_email;
  @Autowired
  private AdministrationService _serviceAdministration;

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI') ")
  @GetMapping("/priceglobalbyprovincebyproduct")
  public HashMap<String, Object> getPriceGlobal(@RequestParam("province") int province,
      @RequestParam("product") int product,
      @RequestParam("mois") int mois, @RequestParam("annee") int annee) {
    return _servicePpn.getPriceglobalbyprovince(province, product, mois, annee);
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI') ")
  @GetMapping("/Yearsglobalbyprovincebyproduct")
  public HashMap<String, Object> getPriceGlobalAn(@RequestParam("province") int province,
      @RequestParam("product") int product, @RequestParam("annee") int annee, @RequestParam("anne2") int annee2,
      @RequestParam("choix") boolean three_last_years) {
    if (three_last_years == true) {
      return _servicePpn.getPricePpn3An(province, product, annee);
    } else {
      return _servicePpn.getPricePpn2compare(province, product, annee, annee2);
    }
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI') ")
  @GetMapping("/detailregionbyprovince")
  public List<PpnRegion> getDetailRegionbyProvince(@RequestParam("province") int province,
      @RequestParam("product") int product,
      @RequestParam("mois") int mois, @RequestParam("annee") int annee) {
    return _servicePpn.getDetailProvince(province, product, mois, annee);
  }

  @PreAuthorize("hasRole('SG') or hasRole('DG') or hasRole('DSI') or hasRole('DR') or hasRole('DT') ")
  @GetMapping("/districtdetailbyregion")
  public List<PpnDistrict> getDistrictbyDetailregion(@RequestParam("idregion") int idregion,
      @RequestParam("product") int product,
      @RequestParam("mois") int mois, @RequestParam("annee") int annee) {
    return _servicePpn.getDistrictbyDetailregion(idregion, product, mois, annee);
  }
  // -------------------- * ////////////////////--------------------

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/pricebyregionbyprovincebyproduct")
  public HashMap<String, Object> getPriceGlobalByregion(@RequestParam("product") int product,
      @RequestParam("mois") int mois, @RequestParam("annee") int annee) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    return _servicePpn.getPriceglobalbyprovince(administration.get().getRegion().getProvince().getIdprovince(), product,
        mois, annee);
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/Yearsglobalbyprovincebydirecteur")
  public HashMap<String, Object> getPriceGlobalbyregion(@RequestParam("product") int product,
      @RequestParam("annee") int annee, @RequestParam("anne2") int annee2,
      @RequestParam("choix") boolean three_last_years) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    if (three_last_years == true) {
      return _servicePpn.getPricePpn3An(administration.get().getRegion().getProvince().getIdprovince(), product, annee);
    } else {
      return _servicePpn.getPricePpn2compare(administration.get().getRegion().getProvince().getIdprovince(), product,
          annee, annee2);
    }
  }

  @PreAuthorize("hasRole('DR') or hasRole('DT')")
  @GetMapping("/detailregionbydirecteur")
  public List<PpnRegion> getDetailRegionbyProvinceByDirecteur(
      @RequestParam("product") int product,
      @RequestParam("mois") int mois, @RequestParam("annee") int annee) {
    String email = token_email.getEmailUserByToken();
    Optional<Administration> administration = _serviceAdministration.getAdministrationByEmail(email);
    return _servicePpn.getDetailProvince(administration.get().getRegion().getProvince().getIdprovince(), product, mois,
        annee);
  }
}
