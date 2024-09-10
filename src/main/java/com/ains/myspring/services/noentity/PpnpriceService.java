package com.ains.myspring.services.noentity;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.notentity.PpnRegion;
import com.ains.myspring.models.notentity.Ppnprice;
import com.ains.myspring.repository.notentity.PpnRepository;

@Service
public class PpnpriceService {
  @Autowired
  private PpnRepository _context_ppn;

  public List<PpnRegion> getDetailProvince(int idprovince, int idproduct, int mois, int annee) {
    return _context_ppn.getDetailByRegion(idprovince, idproduct, mois, annee);
  }

  public HashMap<String, Object> getPricePpn3An(int idprovince, int idproduct, int annee) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("isthreeyears", true);
    map.put("date1", annee);
    map.put("data1", _context_ppn.getStatglobalpricebyprovinceAnnee(idprovince, idproduct, annee));
    map.put("date2", annee - 1);
    map.put("data2", _context_ppn.getStatglobalpricebyprovinceAnnee(idprovince, idproduct, annee - 1));
    map.put("date3", annee - 2);
    map.put("data3", _context_ppn.getStatglobalpricebyprovinceAnnee(idprovince, idproduct, annee - 2));
    return map;
  }

  public HashMap<String, Object> getPricePpn2compare(int idprovince, int idproduct, int annee, int anne2) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("isthreeyears", false);
    map.put("date1", annee);
    map.put("data1", _context_ppn.getStatglobalpricebyprovinceAnnee(idprovince, idproduct, annee));
    map.put("date2", anne2);
    map.put("data2", _context_ppn.getStatglobalpricebyprovinceAnnee(idprovince, idproduct, anne2));
    return map;
  }

  public HashMap<String, Object> getPriceglobalbyprovince(int idprovince, int idproduct, int mois, int annee) {
    HashMap<String, Object> data = new HashMap<>();
    Ppnprice actuelle = _context_ppn.getStatglobalpricebyprovince(idprovince, idproduct, mois, annee);
    Ppnprice lastmonth = null;
    int mois_dernier = 0;
    int annee_dernier = annee;
    if (mois == 1) {
      mois_dernier = 12;
      annee_dernier = annee - 1;
    } else {
      mois_dernier = mois - 1;
    }
    lastmonth = _context_ppn.getStatglobalpricebyprovince(idprovince, idproduct, mois_dernier, annee_dernier);
    if (actuelle.getP_moyenne() == 0) {
      data.put("haverapport", false);
      data.put("rapport", "Pas de donnée collectée ce mois-ci");
      data.put("price_actu", actuelle);
    } else if (lastmonth.getP_moyenne() == 0) {
      data.put("haverapport", false);
      data.put("rapport", "Aucune collecte n'a été effectuée le mois dernier");
      data.put("price_actu", actuelle);
    } else {
      data.put("haverapport", true);
      data.put("moyenne_rapport", calculePercent(actuelle.getP_moyenne(), lastmonth.getP_moyenne()));
      data.put("max_rapport", calculePercent(actuelle.getP_max(), lastmonth.getP_max()));
      data.put("min_rapport", calculePercent(actuelle.getP_min(), lastmonth.getP_min()));
      data.put("price_actu", actuelle);
    }
    return data;
  }

  private double calculePercent(double price_actuele, double price_lastmonth) {
    double percent = ((price_actuele - price_lastmonth) / price_lastmonth) * 100;
    return percent;
  }
}
