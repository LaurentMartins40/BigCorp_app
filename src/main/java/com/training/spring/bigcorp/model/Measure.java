package com.training.spring.bigcorp.model;
import java.time.Instant;
import java.util.Objects;

public class Measure {
    private Instant instant;
    private Captor captor;
    private Integer valueInWatt;
    private long id ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
    public Integer getValueInWatt() {
        return valueInWatt;
    }
    public void setValueInWatt(Integer valueInWatt) {
        this.valueInWatt = valueInWatt;
    }
    public Captor getCaptor() {
        return captor;
    }

    public void setCaptor(Captor captor) {
        this.captor = captor;
    }

    public Measure(){}
    public Measure(Instant instant,Integer valueInWatt,Captor captor){
        this.captor = captor;
        this.valueInWatt = valueInWatt ;
        this.instant = instant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measure measure = (Measure) o;
        return instant.equals(measure.instant) &&
                valueInWatt.equals(measure.valueInWatt) &&
                captor.equals(measure.captor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instant, valueInWatt, captor);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
