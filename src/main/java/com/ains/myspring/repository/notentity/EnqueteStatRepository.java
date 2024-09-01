package com.ains.myspring.repository.notentity;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ains.myspring.models.notentity.Enqueregion;
import com.ains.myspring.models.notentity.EnqueteStat;
import com.ains.myspring.models.notentity.SocieteOm;

@Repository
public class EnqueteStatRepository {
  private final JdbcTemplate jdbcTemplate;

  public EnqueteStatRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @SuppressWarnings("deprecation")
  public EnqueteStat getEnqueteStatglobal(int annee) {
    String sql = "select * from get_enquete_stats_by_year(?)";
    return jdbcTemplate.queryForObject(sql, new Object[] { annee }, (rs, rowNum) -> {
      EnqueteStat stat = new EnqueteStat(rs.getInt("enquete_encours"), rs.getInt("enquete_fini"),
          rs.getInt("enquete_clean"), rs.getInt("enquete_infraction"));
      return stat;
    });
  }

  @SuppressWarnings("deprecation")
  public List<Enqueregion> getEnquetebyRegion(int annee) {
    String sql = "select region.nameregion ,province.nameprovince,stat_enquete.* from region join province on (province.idprovince = region.idprovince) join get_combined_enquete_stats_by_region(?) as stat_enquete on (stat_enquete.region = region.idregion) order by region.idregion";
    return jdbcTemplate.query(sql, new Object[] { annee }, (rs, rowNum) -> {
      Enqueregion stat = new Enqueregion(rs.getString("nameregion"), rs.getString("nameprovince"),
          rs.getInt("region"), rs.getInt("t_enquete"), rs.getInt("encours"), rs.getInt("fini"), rs.getInt("clean"),
          rs.getInt("infraction"));
      return stat;
    });
  }

  @SuppressWarnings("deprecation")
  public List<SocieteOm> getRefSociete(int societe) {
    String sql = "select ordermission.idordermission , ordermission.numeroserie from enquete join ordermission on (enquete.idordermission = ordermission.idordermission)  where enquete.idsociete = ?";
    return jdbcTemplate.query(sql, new Object[] { societe }, (rs, rowNum) -> {
      SocieteOm ref_om = new SocieteOm();
      ref_om.setIdorderdemission(rs.getInt("idordermission"));
      ref_om.setNumeroserie(rs.getString("numeroserie"));
      return ref_om;
    });
  }
}
