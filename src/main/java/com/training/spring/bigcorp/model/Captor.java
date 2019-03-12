package com.training.spring.bigcorp.model;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Objects;
import java.util.UUID;

public class Captor {
    /**
     * Captor id
     */
    private String id = UUID.randomUUID().toString();

    /**
     * Captor name
     */
    private String name;

    private PowerSource powerSource;

    private Site site;

    @Deprecated
    public Captor() {
        // Use for serializer or deserializer
    }

    /**
     * Constructor to use with required property
     * @param name
     */
    public Captor(String name ,Site site) {

        this.name = name;
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Captor site = (Captor) o;
        return Objects.equals(name, site.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Captor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
