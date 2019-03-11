package com.training.spring.bigcorp.service;


import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.service.measure.MeasureService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("captorServiceImpl")
public class CaptorServiceImpl implements CaptorService{

    private MeasureService realMeasureService;
    private MeasureService fixedMeasureService;
    private MeasureService simulatedMeasureService;

    public CaptorServiceImpl(MeasureService realMeasureService, MeasureService fixedMeasureService, MeasureService simulatedMeasureService) {
        this.realMeasureService = realMeasureService;
        this.fixedMeasureService = fixedMeasureService;
        this.simulatedMeasureService = simulatedMeasureService;
    }

    @Override
    public Set<Captor> findBySite(String siteId) {
        Set<Captor> captors = new HashSet<>();
        if (siteId == null) {
            return captors;
        }
        captors.add(new Captor("Capteur A"));
        return captors;
    }
}
