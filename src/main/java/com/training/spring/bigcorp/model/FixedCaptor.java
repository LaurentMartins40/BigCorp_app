package com.training.spring.bigcorp.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("FIXED")
public class FixedCaptor extends Captor {
    @NotNull
    private Integer defaultPowerInWatt;
    @Deprecated
    public FixedCaptor(){
        super();
    }
    public FixedCaptor(String name, Site site, Integer defaultPowerInWatt) {
        super(name, site, PowerSource.FIXED);
        this.defaultPowerInWatt = defaultPowerInWatt;
    }

    public Integer getDefaultPowerInWatt() {
        return defaultPowerInWatt;
    }

    public void setDefaultPowerInWatt(Integer defaultPowerInWatt) {
        this.defaultPowerInWatt = defaultPowerInWatt;
    }
}
