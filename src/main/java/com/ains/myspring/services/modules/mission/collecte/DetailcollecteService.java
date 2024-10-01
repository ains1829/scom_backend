package com.ains.myspring.services.modules.mission.collecte;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ains.myspring.models.jsontoclass.order.CollecteJson;
import com.ains.myspring.models.modules.mission.Collecte;
import com.ains.myspring.models.modules.mission.collecte.Detailcollecte;
import com.ains.myspring.repository.modules.mission.collecte.DetailcollecteRepository;
import com.ains.myspring.services.modules.ProductService;

@Service
public class DetailcollecteService {
  @Autowired
  private DetailcollecteRepository _contextdetailcollecte;
  @Autowired
  private ProductService _serviceProduct;

  public List<Detailcollecte> getDetailcollectesbyIdCollecte(int idcollecte) {
    return _contextdetailcollecte.getDetailcollectesBycollecte(idcollecte);
  }

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

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public List<Detailcollecte> SaveDetail(Collecte collecte, List<CollecteJson> collecte_data) throws Exception {
    List<Detailcollecte> saveDetail = new ArrayList<>();
    for (int i = 0; i < collecte_data.size(); i++) {
      CollecteJson item = (CollecteJson) collecte_data.get(i);
      for (int j = 0; j < item.getPrix().size(); j++) {
        double price = Double.valueOf(item.getPrix().get(j));
        saveDetail.add(
            _contextdetailcollecte.save(new Detailcollecte(collecte.getIdcollecte(), _serviceProduct.getProductbyId(
                item.getId()), price, item.getObservations().get(j))));
      }
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
