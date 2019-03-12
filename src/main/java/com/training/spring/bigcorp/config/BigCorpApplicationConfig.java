package com.training.spring.bigcorp.config;

import com.training.spring.bigcorp.model.ApplicationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Set;


@Configuration
public class BigCorpApplicationConfig {
    @Autowired
    Environment environment;
    @Bean
    public ApplicationInfo applicationInfo(){
        String name = environment.getProperty("bigcorp.name");
        Integer version = environment.getProperty("bigcorp.version",Integer.class);
        Set<String> emails = environment.getProperty("bigcorp.emails",Set.class);
        String webSiteUrl = environment.getProperty("bigcorp.webSiteUrl");
        return new ApplicationInfo(name,version,emails,webSiteUrl);
    }
}