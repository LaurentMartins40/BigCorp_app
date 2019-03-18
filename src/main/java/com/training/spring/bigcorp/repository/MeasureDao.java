package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.Measure;
import com.training.spring.bigcorp.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureDao extends JpaRepository<Measure, Long> {
    void deleteByCaptorId(String captorId);
}
