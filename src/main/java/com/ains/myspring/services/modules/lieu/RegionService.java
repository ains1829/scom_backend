package com.ains.myspring.services.modules.lieu;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.modules.lieu.Region;
import com.ains.myspring.repository.modules.lieu.RegionRepository;

@Service
public class RegionService {
  @Autowired
  private RegionRepository _context;

  public Region getRegionById(int idregion) throws Exception {
    Optional<Region> region = _context.findById(idregion);
    if (region.isPresent()) {
      return region.get();
    }
    throw new Exception("Region not found");
  }
}
