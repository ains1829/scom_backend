package com.ains.myspring.services.admin;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.admin.Administration;
import com.ains.myspring.repository.AdministrationRepository;

@Service
public class AdministrationService {
  @Autowired
  private AdministrationRepository _contextAdminitration;

  public Optional<Administration> getAdministrationByEmail(String email) {
    return _contextAdminitration.getAdministrationByEmail(email);
  }

  public Administration CreateNewAdministration(Administration administration) {
    return _contextAdminitration.save(administration);
  }
}
