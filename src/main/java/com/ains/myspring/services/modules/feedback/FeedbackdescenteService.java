package com.ains.myspring.services.modules.feedback;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ains.myspring.models.feedback.Feedbackcontent;
import com.ains.myspring.models.feedback.Feedbackdescente;
import com.ains.myspring.models.feedback.Feedbackdescentephoto;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.repository.modules.feedback.FeedbackdescenteRepository;

@Service
public class FeedbackdescenteService {
  @Autowired
  private FeedbackdescenteRepository _contextFeedback;
  @Autowired
  private FeedbackdescentephotoService servicefeedbackphoto;

  @Transactional(rollbackFor = { Exception.class, SQLException.class })
  public Feedbackdescente Save(Ordermission mission, String feedback,
      List<MultipartFile> photo, String contact) throws Exception {
    Date date = new Date(System.currentTimeMillis());
    Feedbackdescente descente_feedback = _contextFeedback.save(new Feedbackdescente(mission, feedback, date, contact));
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

  public List<Feedbackcontent> ContentFeedback(int idorderdemission) {
    List<Feedbackdescente> feedbackdescente = _contextFeedback.getFeedbackbyordermission(idorderdemission);
    List<Feedbackcontent> content = new ArrayList<>();
    for (int i = 0; i < feedbackdescente.size(); i++) {
      List<Feedbackdescentephoto> photo = servicefeedbackphoto
          .getPhotobydescente(feedbackdescente.get(i).getIdfeedbackdescente());
      Feedbackcontent content_fedd = new Feedbackcontent();
      content_fedd.setDate_feeback(feedbackdescente.get(i).getDate_feedback());
      content_fedd.setFeedback(feedbackdescente.get(i).getFeedback());
      content_fedd.setTelephone(feedbackdescente.get(i).getContact());
      content_fedd.setUrl_piece(Url(photo));
      content.add(content_fedd);
    }
    return content;
  }

  private List<String> Url(List<Feedbackdescentephoto> photo) {
    List<String> string_url = new ArrayList<>();
    for (int i = 0; i < photo.size(); i++) {
      string_url.add(photo.get(i).getUrl_photo());
    }
    return string_url;
  }
}
