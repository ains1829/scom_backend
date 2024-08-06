package com.ains.myspring.services.modules.feedback;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.ains.myspring.models.feedback.Feedbackdescente;
import com.ains.myspring.models.feedback.Feedbackdescentephoto;
import com.ains.myspring.models.modules.Societe;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.repository.modules.feedback.FeedbackdescenteRepository;
import com.ains.myspring.services.modules.SocieteService;

@Service
public class FeedbackdescenteService {
  @Autowired
  private FeedbackdescenteRepository _contextFeedback;
  @Autowired
  private SocieteService serviceSociete;
  @Autowired
  private FeedbackdescentephotoService servicefeedbackphoto;

  @Transactional
  public Feedbackdescente Save(Ordermission mission, int idsociete, String feedback,
      List<MultipartFile> photo) throws Exception {
    Societe societe = serviceSociete.getSocieteById(idsociete);
    Date date = new Date(System.currentTimeMillis());
    Feedbackdescente descente_feedback = _contextFeedback.save(new Feedbackdescente(mission, societe, feedback, date));
    if (photo != null) {
      SavePhoto(descente_feedback.getIdfeedbackdescente(), photo);
    }
    return descente_feedback;
  }

  public List<Feedbackdescentephoto> SavePhoto(int feedback_descente, List<MultipartFile> photo) {
    List<Feedbackdescentephoto> photos_feedback = new ArrayList<>();
    for (int i = 0; i < photo.size(); i++) {
      String url_photo = photo.get(i).getOriginalFilename();
      photos_feedback.add(servicefeedbackphoto.Save(new Feedbackdescentephoto(feedback_descente, url_photo)));
    }
    return photos_feedback;
  }
}
