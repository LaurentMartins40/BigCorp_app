package com.training.spring.bigcorp.service;



import com.training.spring.bigcorp.utils.OutputCapture;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SiteServiceImplTest.SiteServiceTestConfiguration.class})
public class SiteServiceImplTest {
    @Configuration
    @ComponentScan("com.training.spring.bigcorp.service")
    static class SiteServiceTestConfiguration{ }
    @Autowired
    private SiteService siteService;
    @Rule
    public OutputCapture output = new OutputCapture();
    @Test
    public void readFileFromUrl(){
        siteService.readFile("url:https://dev-mind.fr/lorem.txt");
        assertThat(output.toString()).contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
    }
    @Test
    public void readFileFromClasspath(){
        siteService.readFile("classpath:example.txt");
        assertThat(output.toString()).contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
    }
    @Test
    public void readFileFromFileSystem(){
        siteService.readFile("file:/Users/Diginamic/workspace/Spring/formation-spring-tp/src/main/resources/example.txt");
        assertThat(output.toString()).contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
    }
/*
    @Mock
    private CaptorService captorService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdShouldReturnNullWhenIdIsNull(){
        // Initialisation
        String siteId = null;

        // Appel du SUT
        Site site = siteService.findById(siteId);

        // Vérification
        Assertions.assertThat(site).isNull();
    }

    @Test
    public void findById(){
        // Initialisation
        String siteId = "siteId";
        Set<Captor> expectedCpators = Collections.singleton(new Captor("Capteur A"));
        Mockito.when(captorService.findBySite(siteId)).thenReturn(expectedCpators);

        // Appel du SUT
        Site site = siteService.findById(siteId);

        // Vérification
        Assertions.assertThat(site.getId()).isEqualTo(siteId);
        Assertions.assertThat(site.getName()).isEqualTo("Florange");
        Assertions.assertThat(site.getCaptors()).isEqualTo(expectedCpators);
    }*/
}