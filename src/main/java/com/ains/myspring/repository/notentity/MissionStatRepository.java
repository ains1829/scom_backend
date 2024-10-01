package com.ains.myspring.repository.notentity;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ains.myspring.models.notentity.MissionStat;
import com.ains.myspring.models.notentity.Om;

@Repository
public class MissionStatRepository {

  private final JdbcTemplate jdbcTemplate;

  public MissionStatRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @SuppressWarnings("deprecation")
  public MissionStat getStatMissionbyEquipe(int idequipe) {
    String sql = "Select * from get_mission_counts(?)";
    return jdbcTemplate.queryForObject(sql, new Object[] { idequipe }, (rs, rowNum) -> {
      MissionStat stat = new MissionStat(rs.getInt("total_missions"), rs.getInt("missions_en_cours"),
          rs.getInt("missions_fini"));
      return stat;
    });
  }

  @SuppressWarnings("deprecation")
  public MissionStat getStatGlobalMission(int annee) {
    String sql = "Select * from get_global_missions_by_year(?)";
    return jdbcTemplate.queryForObject(sql, new Object[] { annee }, (rs, rowNum) -> {
      MissionStat stat = new MissionStat(rs.getInt("total_missions"), rs.getInt("missions_en_cours"),
          rs.getInt("missions_fini"));
      return stat;
    });
  }

  public Om getOM() {
    String sql = "select * from om_stat";
    return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
      Om stat = new Om(rs.getInt("om"), rs.getInt("om_valider"),
          rs.getInt("om_nonvalider"), rs.getInt("attente_dg"), rs.getInt("supprimer"));
      return stat;
    });
  }

  @SuppressWarnings("deprecation")
  public Om getObByregion(int idregion) {
    String sql = "SELECT  COUNT(*) AS om, COUNT(CASE WHEN status_validation = 100 THEN 1 END) AS om_valider,COUNT(CASE WHEN status_validation = 0 THEN 1 END) AS om_nonvalider, COUNT(CASE WHEN status_validation = 10 THEN 1 END) as attente_dg , COUNT(CASE WHEN status_validation = 500 THEN 1 END) as supprimer FROM ordermission where idregion = ?";
    return jdbcTemplate.queryForObject(sql, new Object[] { idregion }, (rs, rowNum) -> {
      Om stat = new Om(rs.getInt("om"), rs.getInt("om_valider"),
          rs.getInt("om_nonvalider"), rs.getInt("attente_dg"), rs.getInt("supprimer"));
      return stat;
    });
  }
}
