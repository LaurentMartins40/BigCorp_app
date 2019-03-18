package com.training.spring.bigcorp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("SIMULATED")
public class SimulatedCaptor extends Captor{
    @NotNull
    private Integer minPowerInWatt;
    @NotNull
    private Integer maxPowerInWatt;
    @Deprecated
    public SimulatedCaptor(){
        super();
    }
    public SimulatedCaptor(String name, Site site,Integer minPowerInWatt,Integer maxPowerInWatt) {
        super(name, site,PowerSource.SIMULATED);
        this.maxPowerInWatt = maxPowerInWatt;
        this.minPowerInWatt = minPowerInWatt;
    }
    public Integer getMinPowerInWatt() {
        return minPowerInWatt;
    }
    public void setMinPowerInWatt(Integer minPowerinWatt) {
        this.minPowerInWatt = minPowerinWatt;
    }
    public Integer getMaxPowerInWatt() {
        return maxPowerInWatt;
    }
    public void setMaxPowerInWatt(Integer maxPowerinWatt) {
        this.maxPowerInWatt = maxPowerinWatt;
    }
    @AssertTrue(message = "minPowerInWatt should be less than maxPowerInWatt")
    public boolean isValid(){
        return this.minPowerInWatt <= this.maxPowerInWatt;
    }
}
