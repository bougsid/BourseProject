package com.bougsid.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ayoub on 11/11/2016.
 */
@Entity
public class Societe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String name;
    @Transient
    private Map<String, Double> infos;

    @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
    private List<Ordre> ordres = new ArrayList<>();
    private static final long serialVersionUID = 6529685098267757690L;

    public Societe(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Societe() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Ordre> getOrdres() {
        return ordres;
    }

    public void setOrdres(List<Ordre> ordres) {
        this.ordres = ordres;
    }

    @Transient
    public void addOrdre(Ordre ordre) {
        this.ordres.add(ordre);
    }

    @Transient
    public void removeOrdre(Ordre ordre) {
        this.ordres.remove(ordre);
    }

    public Map<String, Double> getInfos() {
        return infos;
    }

    public void setInfos(Map<String, Double> infos) {
        this.infos = infos;
    }
}
