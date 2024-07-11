package com.ains.myspring.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.Coderecuperation;
import com.ains.myspring.repository.CoderecuperationRepository;
import com.ains.myspring.services.function.Fonction;

@Service
public class CodeService {
  @Autowired
  private CoderecuperationRepository _contextCode;
  @Autowired
  private AdministrationService _ServiceAdministration;

  public Coderecuperation Code(String email) {
    int code = new Fonction().generateCode();
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime expiration = now.plus(30, ChronoUnit.MINUTES);
    Coderecuperation coderecuperation = new Coderecuperation(email, code, now, expiration);
    return _contextCode.save(coderecuperation);
  }

  public Coderecuperation RenvoyeCode(String email) throws Exception {
    if (_ServiceAdministration.getAdminByEmail(email).isEmpty()) {
      throw new Exception("Wrong email please verify");
    }
    return Code(email);
  }
}
