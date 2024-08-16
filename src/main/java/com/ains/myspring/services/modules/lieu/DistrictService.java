package com.ains.myspring.services.modules.lieu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.repository.modules.lieu.DistrictRepository;

@Service
public class DistrictService {
  @Autowired
  private DistrictRepository _contextDistrict;

  public District getById(int iddistrict) throws Exception {
    Optional<District> district = _contextDistrict.findById(iddistrict);
    if (district.isPresent()) {
      return district.get();
    }
    throw new Exception("District not found");
  }

  public List<District> getDistrictByregion(int region) {
    return _contextDistrict.getDistrictByregion(region);
  }
}
