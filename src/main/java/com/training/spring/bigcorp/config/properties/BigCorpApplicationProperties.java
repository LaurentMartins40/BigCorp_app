package com.training.spring.bigcorp.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.Set;
@ConfigurationProperties(prefix = "bigcorp")
@Component
public class BigCorpApplicationProperties {

    private String name;
    private Integer version ;
    private Set<String> emails;
    private String webSiteUrl ;
    @NestedConfigurationProperty
    private BigCorpMeasureProperties measure ;

    public BigCorpMeasureProperties getMeasure() {
        return measure;
    }

    public void setMeasure(BigCorpMeasureProperties measure) {
        this.measure = measure;
    }

    public BigCorpApplicationProperties() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }
}
