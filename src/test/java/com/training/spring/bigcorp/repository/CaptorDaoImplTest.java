package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Measure;
import com.training.spring.bigcorp.model.Site;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class CaptorDaoImplTest {
    @Autowired
    private CaptorDao captorDao;
    @Test
    public void findById() {
        Captor captor = captorDao.findById("c1");
        Assertions.assertThat(captor.getId()).isEqualTo("c1");
        Assertions.assertThat(captor.getName()).isEqualTo("Eolienne");
        Assertions.assertThat(captor.getSite().getName()).isEqualTo("Bigcorp Lyon");
    }
    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Captor captor = captorDao.findById("francis");
        Assertions.assertThat(captor).isNull();
    }
    @Test
    public void findAll() {
        List<Captor> captors = captorDao.findAll();
        Assertions.assertThat(captors).hasSize(2);
    }
    @Test
    public void create() {
        Captor captor = new Captor("Eolienne", new Site("site"));
        captor.setId("c3");
        Assertions.assertThat(captorDao.findAll()).hasSize(2);
        captorDao.persist(captor);
        Assertions.assertThat(captorDao.findAll()).hasSize(3);
    }
    @Test
    public void update() {
        Captor captor = captorDao.findById("c1");
        Assertions.assertThat(captor.getName()).isEqualTo("Eolienne");
        captor.setName("new name");captorDao.persist(captor);
        captor = captorDao.findById("c1");
        Assertions.assertThat(captor.getName()).isEqualTo("new name");
    }
    @Test
    public void deleteById() {
        Assertions.assertThat(captorDao.findAll()).hasSize(2);
        captorDao.delete(captorDao.findById("c1"));
        Assertions.assertThat(captorDao.findAll()).hasSize(1);
    }
}