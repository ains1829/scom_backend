package com.ains.myspring.services.noentity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.notentity.Enqueregion;
import com.ains.myspring.models.notentity.EnqueteStat;
import com.ains.myspring.models.notentity.MissionStat;
import com.ains.myspring.models.notentity.MissionType;
import com.ains.myspring.models.notentity.Om;
import com.ains.myspring.models.notentity.RegionStatistic;
import com.ains.myspring.models.notentity.SocieteOm;
import com.ains.myspring.repository.notentity.EnqueteStatRepository;
import com.ains.myspring.repository.notentity.MissionStatRepository;
import com.ains.myspring.repository.notentity.MissionTyperepository;

@Service
public class StatMissionservice {
  @Autowired
  private MissionStatRepository _contextmissionStat;
  @Autowired
  private MissionTyperepository _contextmissiontype;
  @Autowired
  private EnqueteStatRepository _contextEnquetestat;

  public List<MissionType> getMissionProgressionByregion(int region, int annee) {
    return _contextmissiontype.getMissionGlobalTypebyregion(region, annee);
  }

  public List<RegionStatistic> getMissionglobalbytypemission(int typemission, int annee) {
    return _contextmissiontype.getMissionGlobalbyTypeMission(typemission, annee);
  }

  public List<SocieteOm> getRefSociete(int societe) {
    return _contextEnquetestat.getRefSociete(societe);
  }

  public Om getOm() {
    return _contextmissionStat.getOM();
  }

  public List<Enqueregion> getEnqueteglobalbyregion(int annee) {
    return _contextEnquetestat.getEnquetebyRegion(annee);
  }

  public EnqueteStat getEnqueteglobal(int annee) {
    return _contextEnquetestat.getEnqueteStatglobal(annee);
  }

  public MissionStat getStatglobalMission(int annee) {
    return _contextmissionStat.getStatGlobalMission(annee);
  }

  public List<MissionType> getMissionglobalTypes(int annee) {
    return _contextmissiontype.getMissionGlobalType(annee);
  }

  public MissionStat getMissionByEquipe(int equipe) {
    return _contextmissionStat.getStatMissionbyEquipe(equipe);
  }

  public List<MissionType> getMissionTypesbyEquipe(int equipe) {
    return _contextmissiontype.getMissionTypebyEquipe(equipe);
  }
}
