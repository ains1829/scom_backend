package com.ains.myspring.models.modules.signal;

import com.ains.myspring.models.modules.Anomaly;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Signal_cause {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int idsignalcause;
  int idsignal;
  @ManyToOne
  @JoinColumn(name = "idanomaly")
  Anomaly anomaly;

  public Signal_cause() {
  }

  public Signal_cause(int idsignal, Anomaly anomaly) {
    this.idsignal = idsignal;
    this.anomaly = anomaly;
  }

  public int getIdsignalcause() {
    return idsignalcause;
  }

  public void setIdsignalcause(int idsignalcause) {
    this.idsignalcause = idsignalcause;
  }

  public int getIdsignal() {
    return idsignal;
  }

  public void setIdsignal(int idsignal) {
    this.idsignal = idsignal;
  }

  public Anomaly getAnomaly() {
    return anomaly;
  }

  public void setAnomaly(Anomaly anomaly) {
    this.anomaly = anomaly;
  }
}
