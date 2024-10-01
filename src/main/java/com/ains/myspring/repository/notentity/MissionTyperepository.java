package com.ains.myspring.repository.notentity;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ains.myspring.models.notentity.MissionType;
import com.ains.myspring.models.notentity.RegionStatistic;

@Repository
public class MissionTyperepository {
  private final JdbcTemplate jdbcTemplate;

  public MissionTyperepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @SuppressWarnings("deprecation")
  public List<MissionType> getMissionGlobalType(int annee) {
    String sql = "select idtypeordermission , max(nombre_mission) as nombre_mission  , max(mission_finished) as mission_finished , max(mission_pending) as mission_pending , max(progressing) as progressing from ( select * from  v_typemission_init union select * from get_missionglobaltype_and_year(?)) as s  group by idtypeordermission order by idtypeordermission";
    return jdbcTemplate.query(sql, new Object[] { annee }, (rs, rowNum) -> {
      MissionType mission = new MissionType(rs.getInt("idtypeordermission"), rs.getInt("nombre_mission"));
      mission.setMission_finished(rs.getInt("mission_finished"));
      mission.setMission_pending(rs.getInt("mission_pending"));
      mission.setProgressing(rs.getDouble("progressing"));
      return mission;
    });
  }

  @SuppressWarnings("deprecation")
  public List<MissionType> getMissionGlobalTypebyregion(int region, int annee) {
    String sql = "select idtypeordermission , max(nombre_mission) as nombre_mission  , max(mission_finished) as mission_finished , max(mission_pending) as mission_pending , max(progressing) \n"
        + //
        "    as progressing from ( select * from  v_typemission_init union select idtypeordermission , nombre_mission , mission_finished,mission_pending,progressing from get_missionglobaltypebyregion_and_year(?,?)) as s  group by idtypeordermission order by idtypeordermission";
    return jdbcTemplate.query(sql, new Object[] { region, annee }, (rs, rowNum) -> {
      MissionType mission = new MissionType(rs.getInt("idtypeordermission"), rs.getInt("nombre_mission"));
      mission.setMission_finished(rs.getInt("mission_finished"));
      mission.setMission_pending(rs.getInt("mission_pending"));
      mission.setProgressing(rs.getDouble("progressing"));
      return mission;
    });
  }

  @SuppressWarnings("deprecation")
  public List<MissionType> getMissionGlobalbyEquipe(int equipe) {
    String sql = "select * from f_stat_equipe_mission(?)";
    return jdbcTemplate.query(sql, new Object[] { equipe }, (rs, rowNum) -> {
      MissionType mission = new MissionType(rs.getInt("idtypeordermission"), rs.getInt("nombre_mission"));
      mission.setMission_finished(rs.getInt("mission_finished"));
      mission.setMission_pending(rs.getInt("mission_pending"));
      mission.setProgressing(rs.getDouble("progressing"));
      return mission;
    });
  }

  @SuppressWarnings("deprecation")
  public List<RegionStatistic> getMissionGlobalbyTypeMission(int typemission, int annee) {
    String sql = "select region.nameregion ,province.nameprovince,stat_type.* from region join province on (province.idprovince = region.idprovince) join get_combined_bytypemission_stats_by_region(?,?) as stat_type on (stat_type.region = region.idregion) order by region.idregion";
    return jdbcTemplate.query(sql, new Object[] { typemission, annee }, (rs, rowNum) -> {
      RegionStatistic statisc = new RegionStatistic();
      statisc.setNamregion(rs.getString("nameregion"));
      statisc.setNameprovince(rs.getString("nameprovince"));
      statisc.setRegion(rs.getInt("region"));
      statisc.setMissionfini(rs.getInt("missionfini"));
      statisc.setNissionencours(rs.getInt("nissionencours"));
      statisc.setT_mission(rs.getInt("t_mission"));
      return statisc;
    });
  }
}
