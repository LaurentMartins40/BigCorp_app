package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class CaptorDaoImplTest {
    private Site site;
    @Autowired
    private CaptorDao captorDao;
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private SiteDao siteDao;
    @Autowired
    private EntityManager entityManager;
    @Test
    public void findById() {
        Optional<Captor> captor = captorDao.findById("c1");
        Assertions.assertThat(captor)
                .get()
                .extracting("name")
                .containsExactly("Eolienne");
    }
    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Optional<Captor> captor = captorDao.findById("unknown");
        Assertions.assertThat(captor).isEmpty();
    }
    @Test
    public void findAll() {
        List<Captor> captor = captorDao.findAll();
        Assertions.assertThat(captor).hasSize(2)
                .extracting("id", "name")
                .contains(Tuple.tuple("c1", "Eolienne"))
                .contains(Tuple.tuple("c2", "Laminoire à chaud"));
    }
    @Test
    public void create() {
        site = new Site("site");
        entityManager.persist(site);
        Assertions.assertThat(captorDao.findAll()).hasSize(2);
        Captor captor = new RealCaptor("New Captor",site);
        captorDao.save(captor);
        Assertions.assertThat(captorDao.findAll())
                .hasSize(3)
                .extracting(Captor::getName)
                .contains("Eolienne", "Laminoire à chaud","New Captor");
    }
    @Test
    public void update() {
        Optional<Captor> captor = captorDao.findById("c1");
        Assertions.assertThat(captor).get().extracting("name").containsExactly("Eolienne");
        captor.ifPresent(s -> {
                    s.setName("Captor updated");
                    captorDao.save(s);
                });

        captor = captorDao.findById("c1");
        Assertions.assertThat(captor).get().extracting("name").containsExactly("Captor updated");
    }
    @Test
    public void deleteById() {
        Captor newcaptor = new RealCaptor("New Captor",site);
        captorDao.save(newcaptor);
        Assertions.assertThat(captorDao.findById(newcaptor.getId())).isNotEmpty();
        captorDao.delete(newcaptor);
        Assertions.assertThat(captorDao.findById(newcaptor.getId())).isEmpty();
    }
    @Test
    public void deleteByIdShouldThrowExceptionWhenIdIsUsedAsForeignKey() {
        Captor captor = captorDao.getOne("c1");
        Assertions
                .assertThatThrownBy(() -> {
                    captorDao.delete(captor);
                    entityManager.flush();})
                .isExactlyInstanceOf(PersistenceException.class)
                .hasCauseExactlyInstanceOf(ConstraintViolationException.class);
    }
    @Test
    public void findByExample() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.ignoreCase().contains())
                .withIgnorePaths("id")
                .withIgnorePaths("defaultPowerInWatt")
                .withIgnorePaths("site")
                .withIgnoreNullValues();
        site = new Site("Site2");
        entityManager.persist(site);
        Captor captor = new FixedCaptor("lienn", site,1);
        List<Captor> captors = captorDao.findAll(Example.of(captor, matcher));
        Assertions.assertThat(captors)
                .hasSize(1)
                .extracting("id", "name")
                .containsExactly(Tuple.tuple("c1", "Eolienne"));
    }
    @Test
    public void preventConcurrentWrite() {
        Captor captor = captorDao.getOne("c1");
        Assertions.assertThat(captor.getVersion()).isEqualTo(0);
        entityManager.detach(captor);
        captor.setName("Captor updated");
        Captor attachedCaptor = captorDao.save(captor);
        entityManager.flush();
        Assertions.assertThat(attachedCaptor.getName()).isEqualTo("Captor updated");
        Assertions.assertThat(attachedCaptor.getVersion()).isEqualTo(1);
        Assertions.assertThatThrownBy(() -> captorDao.save(captor))
                .isExactlyInstanceOf(ObjectOptimisticLockingFailureException.class);
    }
    @Test
    public void createShouldThrowExceptionWhenNameIsNull() {
        Assertions
                .assertThatThrownBy(() -> {
                    captorDao.save(new RealCaptor(null,site));
                    entityManager.flush();
                })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne peut pas être nul");
    }
    @Test
    public void createShouldThrowExceptionWhenNameSizeIsInvalid() {
        Assertions
                .assertThatThrownBy(() -> {
                    captorDao.save(new RealCaptor("ee",site));
                    entityManager.flush();
                })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("la taille doit être comprise entre 3 et 100");
    }
    @Test
    public void createSimulatedCaptorShouldThrowExceptionWhenMinMaxAreInvalid() {
        Assertions
                .assertThatThrownBy(() -> {
                    captorDao.save(new SimulatedCaptor("Mon site", site, 10, 5));
                    entityManager.flush();
                })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("minPowerInWatt should be less than maxPowerInWatt");
    }
    @Test
    public void deleteBySiteId() {
        site= siteDao.findById("site1").get();
        Assertions.assertThat(captorDao.findBySiteId("site1")).hasSize(2);
        measureDao.deleteAll();
        captorDao.deleteBySiteId("site1");
        siteDao.delete(site);
        Assertions.assertThat(captorDao.findBySiteId("site1")).isEmpty();
    }
}