package com.ains.myspring.services;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.Coderecuperation;
import com.ains.myspring.repository.CodeRecuperationRepository;

@Service
public class CodeRecuperationService {
  @Autowired
  private CodeRecuperationRepository _contextCode;

  public boolean CodeValidate(String email, int code) throws Exception {
    _contextCode.UpdateCodeExpired();
    Optional<Coderecuperation> coderecuperation = _contextCode.getCodeByEmailandCode(email, code);
    if (coderecuperation.isPresent()) {
      if (coderecuperation.get().isIsexpired() || coderecuperation.get().isIsavailable() == false) {
        throw new Exception("Code expired or Already use");
      } else {
        return true;
      }
    } else {
      throw new Exception("Wrong code");
    }
  }

  public void UpdateCodeToNotAvailable(String email, int code) throws Exception {
    try {
      if (CodeValidate(email, code)) {
        _contextCode.UpdateCodeToNotAvailable(email, code);
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Coderecuperation newCodeOfRecuperation(int code, String email) {
    LocalDateTime begin = LocalDateTime.now();
    LocalDateTime expiration = begin.plusMinutes(10);
    return _contextCode.save(new Coderecuperation(email, code, begin, expiration));
  }
}
