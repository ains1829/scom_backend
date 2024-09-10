package com.ains.myspring.models.feedback;

import java.sql.Date;
import java.util.List;

public class Feedbackcontent {
  String feedback;
  String telephone;
  Date date_feeback;
  List<String> url_piece;

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Date getDate_feeback() {
    return date_feeback;
  }

  public void setDate_feeback(Date date_feeback) {
    this.date_feeback = date_feeback;
  }

  public List<String> getUrl_piece() {
    return url_piece;
  }

  public void setUrl_piece(List<String> url_piece) {
    this.url_piece = url_piece;
  }
}
