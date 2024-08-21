package com.ains.myspring.services.modules.mission;

import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.models.modules.mission.Enquete;
import com.ains.myspring.repository.modules.mission.EnqueteRepository;

@Service
public class EnqueteService {
  @Autowired
  private EnqueteRepository _contextEnquete;

  public Enquete Save(Enquete enquete) {
    return _contextEnquete.save(enquete);
  }

  public boolean CheckSocieteIsPending(int idsociete) throws Exception {
    Optional<Enquete> enquete = _contextEnquete.CheckIfSocieteIsPending(idsociete);
    if (enquete != null) {
      throw new Exception("Enquete for this societe is pending");
    }
    return false;
  }

  public Enquete FindById(int idenquete) throws Exception {
    Optional<Enquete> enquete = _contextEnquete.findById(idenquete);
    if (enquete.isPresent()) {
      return enquete.get();
    }
    throw new Exception("Enquete not found");
  }

  public Enquete ChangeStatus(int idenquete, int status) throws Exception {
    Optional<Enquete> enquete = _contextEnquete.findById(idenquete);
    if (enquete.isPresent()) {
      enquete.get().setStatu(status);
      return _contextEnquete.save(enquete.get());
    }
    throw new Exception("Enquete not found");
  }

  public Enquete ChangeStatusMissionFinished(Enquete enquete) throws Exception {
    if (enquete.getStatu() == 200) {
      enquete.setStatu(210);
    } else {
      enquete.setStatu(515);
    }
    return _contextEnquete.save(enquete);
  }

  public Enquete getEnqueteByOrdermission(int ordermission) throws Exception {
    Optional<Enquete> enquete = _contextEnquete.getEnqueteByOrderMission(ordermission);
    if (enquete.isPresent()) {
      return enquete.get();
    }
    throw new Exception("Enquete not found");
  }

  public Enquete FicheTechnique(int ordermission, MultipartFile file) throws Exception {
    Enquete enquete = getEnqueteByOrdermission(ordermission);
    String file_serveur = file.getOriginalFilename();
    enquete.setUrl_fichetechnique(file_serveur);
    enquete.setDatefichetechnique(new Date(System.currentTimeMillis()));
    enquete.setStatu(20);
    return _contextEnquete.save(enquete);
  }

  public Enquete Convocation(int ordermission, MultipartFile file) throws Exception {
    Enquete enquete = getEnqueteByOrdermission(ordermission);
    String file_serveur = file.getOriginalFilename();
    enquete.setDateconvocation(new Date(System.currentTimeMillis()));
    enquete.setUrl_convocation(file_serveur);
    enquete.setStatu(30);
    return _contextEnquete.save(enquete);
  }

  public Enquete Pvaudition(int ordermission, MultipartFile file) throws Exception {
    Enquete enquete = getEnqueteByOrdermission(ordermission);
    String file_serveur = file.getOriginalFilename();
    enquete.setDatepvaudition(new Date(System.currentTimeMillis()));
    enquete.setUrlpvaudition(file_serveur);
    enquete.setStatu(200);
    return _contextEnquete.save(enquete);
  }

  public Enquete Pvinfraction(int ordermission, MultipartFile file) throws Exception {
    Enquete enquete = getEnqueteByOrdermission(ordermission);
    String file_serveur = file.getOriginalFilename();
    enquete.setDateinfraction(new Date(System.currentTimeMillis()));
    enquete.setUrl_pvinfraction(file_serveur);
    enquete.setStatu(500);
    return _contextEnquete.save(enquete);
  }
}
