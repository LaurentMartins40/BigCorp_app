package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SiteDaoImpl implements SiteDao {
    @Autowired
    private CaptorDao captorDao;
    @PersistenceContext
    private EntityManager em;
    @Override
    public void persist(Site site) {
        em.persist(site);
    }
    @Override
    public Site findById(String id) {
        return em.find(Site.class, id);
    }
    @Override
    public List<Site> findAll() {
        return em.createQuery("select s from Site s ",
                Site.class)
                .getResultList();
    }
    @Override
    public void delete(Site site) {
        captorDao.findAll().forEach(c->{
            if(site.equals(c.getSite()))
            captorDao.delete(c);
        });
        em.remove(site);
    }
}