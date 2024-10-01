package com.ains.myspring.repository.notentity;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ains.myspring.models.notentity.PpnAnnee;
import com.ains.myspring.models.notentity.PpnDistrict;
import com.ains.myspring.models.notentity.PpnRegion;
import com.ains.myspring.models.notentity.Ppnprice;

@Repository
public class PpnRepository {
  private final JdbcTemplate jdbcTemplate;

  public PpnRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @SuppressWarnings("deprecation")
  public Ppnprice getStatglobalpricebyprovince(int idprovince, int idproduct, int mois, int annee) {
    String sql = "select province , product, round(p_moyenne , 2) as p_moyenne , p_max , p_min , mois,annee from getStatglobalProvincebyproduct(?,?,?,?) ";
    return jdbcTemplate.queryForObject(sql, new Object[] { idprovince, idproduct, mois, annee }, (rs, rowNum) -> {
      Ppnprice price = new Ppnprice();
      price.setProvince(rs.getInt("province"));
      price.setProduct(rs.getInt("product"));
      price.setP_moyenne(rs.getDouble("p_moyenne"));
      price.setP_max(rs.getDouble("p_max"));
      price.setP_min(rs.getDouble("p_min"));
      price.setMois(rs.getInt("mois"));
      price.setAnnee(rs.getInt("annee"));
      return price;
    });
  }

  @SuppressWarnings("deprecation")
  public List<PpnAnnee> getStatglobalpricebyprovinceAnnee(int idprovince, int idproduct, int annee) {
    String sql = "select * from getStatGlobalProductbyannee(?,?,?)";
    return jdbcTemplate.query(sql, new Object[] { idprovince, idproduct, annee }, (rs, rowNum) -> {
      PpnAnnee price = new PpnAnnee();
      price.setProvince(rs.getInt("province"));
      price.setProduct(rs.getInt("product"));
      price.setP_moyenne(rs.getDouble("p_moyenne"));
      price.setMois(rs.getInt("mois"));
      price.setAnnee(rs.getInt("annee"));
      return price;
    });
  }

  @SuppressWarnings("deprecation")
  public List<PpnRegion> getDetailByRegion(int idprovince, int idproduct, int mois, int annee) {
    String sql = "select region.nameregion, st_region.* from region join getStatbyprovincebyproduct(?,?,?,?) as st_region on (st_region.region = region.idregion)";
    return jdbcTemplate.query(sql, new Object[] { idproduct, mois, annee, idprovince }, (rs, rowNum) -> {
      PpnRegion ppregion = new PpnRegion();
      ppregion.setNameregion(rs.getString("nameregion"));
      ppregion.setRegion(rs.getInt("region"));
      ppregion.setProduct(rs.getInt("product"));
      ppregion.setP_moyenne(rs.getDouble("p_moyenne"));
      ppregion.setP_max(rs.getDouble("p_max"));
      ppregion.setP_min(rs.getDouble("p_min"));
      ppregion.setMois(rs.getInt("mois"));
      ppregion.setAnnee(rs.getInt("annee"));
      return ppregion;
    });
  }

  @SuppressWarnings("deprecation")
  public List<PpnDistrict> getDistrictbyDetailregion(int idregion, int idproduct, int mois, int annee) {
    String sql = "select district.nameville , f.* from getCollecteOnDistrict(?,?,?,?) as f join district on (f.iddistrict = district.iddistrict)";
    return jdbcTemplate.query(sql, new Object[] { annee, mois, idregion, idproduct }, (rs, rowNum) -> {
      PpnDistrict ppregion = new PpnDistrict();
      ppregion.setNamedistrict(rs.getString("nameville"));
      ppregion.setIddistrict(rs.getInt("iddistrict"));
      ppregion.setP_moyenne(rs.getDouble("prix_moyenne"));
      ppregion.setP_max(rs.getDouble("prix_max"));
      ppregion.setP_min(rs.getDouble("prix_min"));
      return ppregion;
    });
  }
}
