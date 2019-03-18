package com.training.spring.bigcorp.controller;
/*
import com.training.spring.bigcorp.model.FixedCaptor;
import com.training.spring.bigcorp.model.SimulatedCaptor;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.repository.MeasureDao;
import com.training.spring.bigcorp.repository.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

    @Controller
    @Transactional
    @RequestMapping("/sites/{siteId}/captors")
    public class SimulatedCaptorController {
        @Autowired
        SiteDao siteDao;
        @Autowired
        MeasureDao measureDao;
        @Autowired
        CaptorDao captorDao;
        @GetMapping("/SIMULATED/create")
        public ModelAndView handle(Model model, @PathVariable String siteId) {
            Site site = siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new);
            ModelAndView mv = new ModelAndView("captor");
            mv.addObject("simulated", new SimulatedCaptor("",site,null,null));
            return mv;
        }
        @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        public ModelAndView save(@PathVariable String siteId, SimulatedCaptor captor) {
            Site site = siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new);
            SimulatedCaptor captorToPersist;
            if (captor.getId() == null) {
                captorToPersist = new SimulatedCaptor(captor.getName(), site,
                        captor.getMinPowerinWatt(),captor.getMaxPowerinWatt());
            } else {
                captorToPersist = (SimulatedCaptor) captorDao.findById(captor.getId())
                        .orElseThrow(IllegalArgumentException::new);
                captorToPersist.setName(captor.getName());
                captorToPersist.setMinPowerinWatt(captor.getMinPowerinWatt());
                captorToPersist.setMaxPowerinWatt(captor.getMaxPowerinWatt());

            }
            captorDao.save(captorToPersist);
            return new ModelAndView("sites").addObject("sites", siteDao.findAll());
        }
        @PostMapping("/{id}/delete")
        public ModelAndView delete(@PathVariable String id) {
            measureDao.deleteByCaptorId(id);
            captorDao.deleteById(id);
            return new ModelAndView("sites").addObject("sites", siteDao.findAll());
        }
        @GetMapping("/{id}")
        public ModelAndView edit(Model model,@PathVariable String id ,@PathVariable String siteId) {
            return new ModelAndView("captor")
                    .addObject("siteId", siteId)
                    .addObject("simulated",
                            captorDao.findById(id).get());
        }
}
*/