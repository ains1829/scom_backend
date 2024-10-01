package com.ains.myspring.repository.modules.signal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ains.myspring.models.modules.signal.Signal;

public interface SignalRepository extends JpaRepository<Signal, Integer> {
  @Query(value = "select * from signal where extract(year from datesignal) = :annee and idregion = :region order by datesignal desc", nativeQuery = true)
  Page<Signal> getSignal(int annee, int region, Pageable page);
}
