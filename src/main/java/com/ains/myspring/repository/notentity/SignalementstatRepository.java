package com.ains.myspring.repository.notentity;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ains.myspring.models.notentity.Statanomaly;
import com.ains.myspring.models.notentity.Statsignalament;
import com.ains.myspring.models.notentity.Statsignalementbyregion;

@Repository
public class SignalementstatRepository {
  private final JdbcTemplate jdbcTemplate;

  public SignalementstatRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @SuppressWarnings("deprecation")
  public List<Statanomaly> getStatRepartitionAnomalybyregionbyyear(int annee, int region) {
    String sql = "select f.* , anomaly.nameanomaly from getPercentAnomalybyregionbyyearfinal(? , ?) as f join anomaly on f.idanomaly  = anomaly.idanomaly";
    return jdbcTemplate.query(sql, new Object[] { annee, region }, (rs, rowNum) -> {
      Statanomaly stat = new Statanomaly();
      stat.setIdanomaly(rs.getInt("idanomaly"));
      stat.setNameanomaly(rs.getString("nameanomaly"));
      stat.setNb_anomaly(rs.getInt("nb_anomaly"));
      stat.setPercent(rs.getDouble("percent"));
      return stat;
    });
  }

  @SuppressWarnings("deprecation")
  public List<Statsignalament> getStatSignalementybyregionyear(int annee, int region) {
    String sql = "select mois , max(nombre_signal) as nombre_signal from (select * from getNbsignalementbyregionbyyear(? , ?) union select v_init_nb_mois.nombre_signal, v_init_nb_mois.idmois as mois  from v_init_nb_mois) \n"
        + //
        "group by mois order by mois";
    return jdbcTemplate.query(sql, new Object[] { annee, region }, (rs, rowNum) -> {
      Statsignalament statsignalament = new Statsignalament();
      statsignalament.setMois(rs.getInt("mois"));
      statsignalament.setNombre_signal(rs.getInt("nombre_signal"));
      return statsignalament;
    });
  }

  @SuppressWarnings("deprecation")
  public List<Statanomaly> getStatRepartitionAnomalybyyear(int annee) {
    String sql = " select f.* , anomaly.nameanomaly from getPercentAnomalybyyearfinal(?) as f join anomaly on f.idanomaly  = anomaly.idanomaly ";
    return jdbcTemplate.query(sql, new Object[] { annee }, (rs, rowNum) -> {
      Statanomaly stat = new Statanomaly();
      stat.setIdanomaly(rs.getInt("idanomaly"));
      stat.setNameanomaly(rs.getString("nameanomaly"));
      stat.setNb_anomaly(rs.getInt("nb_anomaly"));
      stat.setPercent(rs.getDouble("percent"));
      return stat;
    });
  }

  @SuppressWarnings("deprecation")
  public List<Statsignalament> getStatSignalementybyyear(int annee) {
    String sql = "select mois , max(nombre_signal) as nombre_signal from (select * from getNbsignalementbyyear(?) union select v_init_nb_mois.nombre_signal, v_init_nb_mois.idmois as mois  from v_init_nb_mois) \n"
        + //
        "group by mois order by mois";
    return jdbcTemplate.query(sql, new Object[] { annee }, (rs, rowNum) -> {
      Statsignalament statsignalament = new Statsignalament();
      statsignalament.setMois(rs.getInt("mois"));
      statsignalament.setNombre_signal(rs.getInt("nombre_signal"));
      return statsignalament;
    });
  }

  @SuppressWarnings("deprecation")
  public List<Statsignalementbyregion> getStatSignalementybyregionbyyear(int annee) {
    String sql = " select f.* , region.nameregion from region join getNbSignalregionByyearfinal(?) f on f.idregion = region.idregion order by f.nb_signal desc";
    return jdbcTemplate.query(sql, new Object[] { annee }, (rs, rowNum) -> {
      Statsignalementbyregion statsignalament = new Statsignalementbyregion();
      statsignalament.setIdregion(rs.getInt("idregion"));
      statsignalament.setNb_signal(rs.getInt("nb_signal"));
      statsignalament.setNameregion(rs.getString("nameregion"));
      return statsignalament;
    });
  }
}
