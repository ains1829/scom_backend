package com.ains.myspring.services.modules.mission;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.modules.mission.Typeordermission;
import com.ains.myspring.repository.modules.mission.TypeordermissionRepository;

@Service
public class TypeordermissionService {
  @Autowired
  private TypeordermissionRepository _contextType;

  public List<Typeordermission> getTypeordermission() {
    return _contextType.findAll();
  }
}
