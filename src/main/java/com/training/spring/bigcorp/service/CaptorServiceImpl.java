package com.training.spring.bigcorp.service;


import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.service.measure.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service("captorServiceImpl")
public class CaptorServiceImpl implements CaptorService{
    private CaptorDao captorDao;

    private MeasureService realMeasureService;
    private MeasureService fixedMeasureService;
    private MeasureService simulatedMeasureService;

    public CaptorServiceImpl(MeasureService realMeasureService, MeasureService fixedMeasureService, MeasureService simulatedMeasureService, CaptorDao captorDao) {
        this.realMeasureService = realMeasureService;
        this.fixedMeasureService = fixedMeasureService;
        this.simulatedMeasureService = simulatedMeasureService;
        this.captorDao = captorDao;
    }

    @Override
    public Set<Captor> findBySite(String siteId) {
        return captorDao.findBySiteId(siteId).stream().collect(Collectors.toSet());
    }
}
