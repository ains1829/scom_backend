package com.ains.myspring.repository.modules.feedback;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ains.myspring.models.feedback.Feedbackdescentephoto;

public interface FeedbackdescentephotoRepository extends JpaRepository<Feedbackdescentephoto, Integer> {
  @Query(value = "Select * from feedbackdescentephoto where idfeedbackdescente = :iddescente", nativeQuery = true)
  List<Feedbackdescentephoto> getFeedbackdescentebyiddescente(int iddescente);
}
