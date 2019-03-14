package com.training.spring.bigcorp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SIMULATED")
public class SimulatedCaptor extends Captor{
    private Integer minPowerInWatt;

    private Integer maxPowerInWatt;
    @Deprecated
    public SimulatedCaptor(){
        super();
    }
    public SimulatedCaptor(String name, Site site) {
        super(name, site);
    }
    public Integer getMinPowerinWatt() {
        return minPowerInWatt;
    }

    public void setMinPowerinWatt(Integer minPowerinWatt) {
        this.minPowerInWatt = minPowerinWatt;
    }

    public Integer getMaxPowerinWatt() {
        return maxPowerInWatt;
    }

    public void setMaxPowerinWatt(Integer maxPowerinWatt) {
        this.maxPowerInWatt = maxPowerinWatt;
    }


}
