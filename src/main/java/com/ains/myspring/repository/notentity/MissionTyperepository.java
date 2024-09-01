package com.ains.myspring.repository.notentity;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ains.myspring.models.notentity.MissionType;

@Repository
public class MissionTyperepository {
  private final JdbcTemplate jdbcTemplate;

  public MissionTyperepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @SuppressWarnings("deprecation")
  public List<MissionType> getMissionTypebyEquipe(int idequipe) {
    String sql = "select idtypeordermission , max(nombre_mission) as nombre_mission from (select * from  v_typeordermission_mission  union select * from get_mission_count_byequipe(?) ) as s  group by idtypeordermission order by idtypeordermission";
    return jdbcTemplate.query(sql, new Object[] { idequipe }, (rs, rowNum) -> {
      MissionType mission = new MissionType(rs.getInt("idtypeordermission"), rs.getInt("nombre_mission"));
      return mission;
    });
  }

  @SuppressWarnings("deprecation")
  public List<MissionType> getMissionGlobalType(int annee) {
    String sql = "select idtypeordermission , max(nombre_mission) as nombre_mission from ( select * from  v_typeordermission_mission union select * from get_missionglobaltype_and_year(?)) as s  group by idtypeordermission order by idtypeordermission";
    return jdbcTemplate.query(sql, new Object[] { annee }, (rs, rowNum) -> {
      MissionType mission = new MissionType(rs.getInt("idtypeordermission"), rs.getInt("nombre_mission"));
      return mission;
    });
  }
}
