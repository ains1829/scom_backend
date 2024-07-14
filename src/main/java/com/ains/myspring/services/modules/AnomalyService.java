package com.ains.myspring.services.modules;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.modules.Anomaly;
import com.ains.myspring.repository.modules.AnomalyRepository;

@Service
public class AnomalyService {
  @Autowired
  private AnomalyRepository _contextanomaly;

  public List<Anomaly> getAllAnomaly() {
    return _contextanomaly.findAll();
  }

  public Anomaly CreateNewAnomaly(Anomaly anomaly) throws Exception {
    if (_contextanomaly.AnomlyIsExist(anomaly.getNameanomaly()) > 0) {
      throw new Exception("Anomaly is already exist");
    }
    return _contextanomaly.save(anomaly);
  }

  public Anomaly UpdateAnomaly(Anomaly anomaly) throws Exception {
    Optional<Anomaly> latestanomaly = _contextanomaly.findById(anomaly.getIdanomaly());
    if (latestanomaly.isPresent()) {
      latestanomaly.get().setNameanomaly(anomaly.getNameanomaly());
      return _contextanomaly.save(latestanomaly.get());
    } else {
      throw new Exception("Anomaly not found");
    }
  }

  public void DeleteAnomaly(int idanomaly) throws Exception {
    try {
      _contextanomaly.deleteById(idanomaly);
    } catch (Exception e) {
      throw new Exception("Anomaly in conflict");
    }
  }
}
