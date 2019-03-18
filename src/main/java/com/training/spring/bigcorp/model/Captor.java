package com.training.spring.bigcorp.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Captor {
    /**
     * Captor id
     */
    @Id
    private String id;
    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
    /**
     * Captor name
     */
    @NotNull
    @Size(min = 3,max = 100)
    @Column(nullable = false)
    private String name;
    @ManyToOne(optional = false)
    private Site site;
    @Version
    private int version;
    @Enumerated(EnumType.STRING)
    @NotNull
    private PowerSource powerSource;

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerSource powerSource) {
        this.powerSource = powerSource;
    }
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
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

    public Captor() {
        // Use for serializer or deserializer
    }
    /**
     * Constructor to use with required property
     * @param name
     */
    public Captor(String name ,Site site,PowerSource powerSource) {
        this.name = name;
        this.site = site;
        this.powerSource = powerSource;
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
