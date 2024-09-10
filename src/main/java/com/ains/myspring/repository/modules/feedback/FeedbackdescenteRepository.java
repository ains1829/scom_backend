package com.ains.myspring.repository.modules.feedback;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ains.myspring.models.feedback.Feedbackdescente;

public interface FeedbackdescenteRepository extends JpaRepository<Feedbackdescente, Integer> {
  @Query(value = "select * from feedbackdescente where idordermission = :id", nativeQuery = true)
  List<Feedbackdescente> getFeedbackbyordermission(int id);
}
