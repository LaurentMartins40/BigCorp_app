package com.training.spring.bigcorp.repository;
import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Measure;
import com.training.spring.bigcorp.model.RealCaptor;
import com.training.spring.bigcorp.model.Site;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class MeasureDaoImplTest {
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private EntityManager entityManager;
    @Test
    public void findById() {
        Optional<Measure> measure = measureDao.findById(-1L);
        Assertions.assertThat(measure)
                .get()
                .extracting(Measure::getValueInWatt)
                .isEqualTo(1000000);
    }
    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Optional<Measure> measure = measureDao.findById(10L);
        Assertions.assertThat(measure).isEmpty();
    }
    @Test
    public void findAll() {
        List<Measure> measure = measureDao.findAll();
        Assertions.assertThat(measure).hasSize(10);
    }
    @Test
    public void create() {
        Captor captor = new RealCaptor("Eolienne", new Site("site"));
        captor.setId("c1");
        Assertions.assertThat(measureDao.findAll()).hasSize(10);
        measureDao.save(new Measure(Instant.now(), 2_333_666, captor));
        Assertions.assertThat(measureDao.findAll())
                .hasSize(11);
    }
    @Test
    public void update() {
        Optional<Measure> measure = measureDao.findById(-1L);
        Assertions.assertThat(measure).get().extracting(Measure::getValueInWatt).isEqualTo(1000000);
            measure.ifPresent(m ->{
                    m.setValueInWatt(300);
                    measureDao.save(m);
                });
        measure = measureDao.findById(-1L);
        Assertions.assertThat(measure).get().extracting(Measure::getValueInWatt).isEqualTo(300);
    }
    @Test
    public void deleteById() {
        Captor captor = new RealCaptor("Eolienne", new Site("site"));
        captor.setId("c1");
        Measure newmeasure = new Measure(Instant.now(), 2_333_666, captor);
        measureDao.save(newmeasure);
        Assertions.assertThat(measureDao.findById(newmeasure.getId())).isNotEmpty();
        measureDao.delete(newmeasure);
        Assertions.assertThat(measureDao.findById(newmeasure.getId())).isEmpty();
    }
    @Test
    public void preventConcurrentWrite() {
        Measure measure = measureDao.getOne(-1L);
        Assertions.assertThat(measure.getVersion()).isEqualTo(0);
        entityManager.detach(measure);
        measure.setValueInWatt(2000000);
        Measure attachedMeasure = measureDao.save(measure);
        entityManager.flush();
        Assertions.assertThat(attachedMeasure.getValueInWatt()).isEqualTo(2000000);
        Assertions.assertThat(attachedMeasure.getVersion()).isEqualTo(1);
        Assertions.assertThatThrownBy(() -> measureDao.save(measure))
                .isExactlyInstanceOf(ObjectOptimisticLockingFailureException.class);
    }
}