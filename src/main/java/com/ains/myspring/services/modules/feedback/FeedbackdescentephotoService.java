package com.ains.myspring.services.modules.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ains.myspring.models.feedback.Feedbackdescentephoto;
import com.ains.myspring.repository.modules.feedback.FeedbackdescentephotoRepository;

@Service
public class FeedbackdescentephotoService {
  @Autowired
  private FeedbackdescentephotoRepository _contextFeedbackphoto;

  public Feedbackdescentephoto Save(Feedbackdescentephoto feedback_descente) {
    return _contextFeedbackphoto.save(feedback_descente);
  }
}
