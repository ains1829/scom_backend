package com.ains.myspring.services.modules.mission.collecte;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.jsontoclass.order.ListCollecteprix;
import com.ains.myspring.models.modules.mission.Collecte;
import com.ains.myspring.models.modules.mission.collecte.Detailcollecte;
import com.ains.myspring.repository.modules.mission.collecte.DetailcollecteRepository;
import com.ains.myspring.services.modules.mission.CollecteService;

@Service
public class DetailcollecteService {
  @Autowired
  private DetailcollecteRepository _contextdetailcollecte;
  @Autowired
  private CollecteService _serviceCollecte;

  public Detailcollecte Save(Detailcollecte d_collecte) {
    return _contextdetailcollecte.save(d_collecte);
  }

  public Detailcollecte getById(int idcollecte) throws Exception {
    Optional<Detailcollecte> detail = _contextdetailcollecte.findById(idcollecte);
    if (detail.isPresent()) {
      return detail.get();
    }
    throw new Exception("Detail not found");
  }

  public void DeleteDetail(int idcollecte) throws Exception {
    Detailcollecte detailcollecte = getById(idcollecte);
    _contextdetailcollecte.delete(detailcollecte);
  }

  public List<Detailcollecte> SaveDetail(ListCollecteprix collecte_data) throws Exception {
    Collecte collecte = _serviceCollecte.getCollecteByid(collecte_data.getIdcollecte());
    collecte.setStatu(100);
    _serviceCollecte.Save(collecte);
    List<Detailcollecte> saveDetail = new ArrayList<>();
    if (hasDuplicates(collecte_data.getIdproduct())) {
      throw new Exception("Duplicate data");
    }
    for (int i = 0; i < collecte_data.getIdproduct().size(); i++) {
      saveDetail.add(_contextdetailcollecte
          .save(new Detailcollecte(collecte_data.getIdcollecte(), collecte_data.getIdproduct().get(i),
              collecte_data.getPrix().get(i))));
    }
    return saveDetail;

  }

  public boolean hasDuplicates(List<Integer> list_product) {
    Set<Integer> set = new HashSet<>();
    for (Integer item : list_product) {
      if (!set.add(item)) {
        return true;
      }
    }
    return false;
  }
}
