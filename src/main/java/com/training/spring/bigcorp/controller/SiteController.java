package com.training.spring.bigcorp.controller;

import com.training.spring.bigcorp.exception.NotFoundException;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.repository.MeasureDao;
import com.training.spring.bigcorp.repository.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Controller
@Transactional
@RequestMapping(path = "/sites")
public class SiteController {
    @Autowired
    private SiteDao siteDao;
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private CaptorDao captorDao;
    @GetMapping
    public ModelAndView handle(Model model) {
        ModelAndView mv = new ModelAndView("sites");
        mv.addObject("sites", siteDao.findAll());
        return mv;
    }
    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable String id) {
        return new ModelAndView("sites-create")
                .addObject("site",
                        siteDao.findById(id).orElseThrow(NotFoundException::new));
    }
    @GetMapping("/create")
    public ModelAndView create(Model model){
        return new ModelAndView("sites-create").addObject("sites", new Site());
    }
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView save(Site site) {
        if (site.getId() == null) {
            siteDao.save(site);
            return new ModelAndView("sites").addObject("sites",siteDao.findAll());

        } else {
            Site siteToPersist =
                    siteDao.findById(site.getId()).orElseThrow(NotFoundException::new);
            siteToPersist.setName(site.getName());
            return new ModelAndView("sites").addObject("sites", siteDao.findAll());
        }
    }
    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable String id) {
        Site site = siteDao.findById(id).orElseThrow(NotFoundException::new);
        site.getCaptors().forEach(c -> measureDao.deleteByCaptorId(c.getId()));
        captorDao.deleteBySiteId(id);
        siteDao.deleteById(id);
        return new ModelAndView("sites").addObject("sites", siteDao.findAll());
    }
    @GetMapping("/{id}/measures")
    public ModelAndView findMeasuresById(@PathVariable String id) {
        Site site = siteDao.findById(id).orElseThrow(NotFoundException::new);
        String captors = site.getCaptors()
                .stream()
                .map(c -> "{ id: '" + c.getId() + "', name: '" + c.getName()
                        + "'}")
                .collect(Collectors.joining(","));
        return new ModelAndView("site-measures")
                .addObject("site", site)
                .addObject("captors", captors);
    }

}
