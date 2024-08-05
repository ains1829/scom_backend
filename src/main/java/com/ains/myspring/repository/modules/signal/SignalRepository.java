package com.ains.myspring.repository.modules.signal;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ains.myspring.models.modules.signal.Signal;

public interface SignalRepository extends JpaRepository<Signal, Integer> {

}
