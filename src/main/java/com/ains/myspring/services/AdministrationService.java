package com.ains.myspring.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.Administration;
import com.ains.myspring.repository.AdministrationRepository;

@Service
public class AdministrationService {
  @Autowired
  private AdministrationRepository _contextAdministration;

  public Optional<Administration> getAdminByEmail(String email) {
    return _contextAdministration.getAdministrationByEmail(email);
  }

  public List<Administration> getAll() {
    return _contextAdministration.findAll();
  }

  public Administration Save(Administration administration) {
    return _contextAdministration.save(administration);
  }
}
