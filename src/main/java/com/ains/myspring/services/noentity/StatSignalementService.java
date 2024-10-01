package com.ains.myspring.services.noentity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.notentity.Statanomaly;
import com.ains.myspring.models.notentity.Statsignalament;
import com.ains.myspring.models.notentity.Statsignalementbyregion;
import com.ains.myspring.repository.notentity.SignalementstatRepository;

@Service
public class StatSignalementService {
  @Autowired
  private SignalementstatRepository signalementStat;

  public List<Statanomaly> getRepartitionanomalybyregion(int annee, int region) {
    return signalementStat.getStatRepartitionAnomalybyregionbyyear(annee, region);
  }

  public List<Statsignalament> getStatSignalementbyregionbymonth(int annee, int region) {
    return signalementStat.getStatSignalementybyregionyear(annee, region);
  }

  public List<Statanomaly> getRepartitionanomaly(int annee) {
    return signalementStat.getStatRepartitionAnomalybyyear(annee);
  }

  public List<Statsignalament> getStatSignalementbymonth(int annee) {
    return signalementStat.getStatSignalementybyyear(annee);
  }

  public List<Statsignalementbyregion> getStatsignalementbyregion(int annee) {
    return signalementStat.getStatSignalementybyregionbyyear(annee);
  }
}
