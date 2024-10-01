package com.ains.myspring.repository.modules.signal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.signal.Signal_photo;

public interface Signal_photoRepository extends JpaRepository<Signal_photo, Integer> {
  @Query(value = "select * from signal_photo where idsignal = :signal", nativeQuery = true)
  List<Signal_photo> getPhotoByIdSignal(int signal);
}
