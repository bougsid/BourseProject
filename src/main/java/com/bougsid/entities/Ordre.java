package com.bougsid.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by ayoub on 11/11/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Ordre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected LocalDate ordreDate;
    protected int actionsCount;
    protected double actionPrice;
    @Column(updatable = false, insertable = false)
    protected String type;


    @ManyToOne
    @JoinColumn(name = "societe_id")
    protected Societe societe;
    private static final long serialVersionUID = 6829685098267757690L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.ordreDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
    }

    @JsonIgnore
    public LocalDate getOrdreDate() {
        return ordreDate;
    }

    public void setOrdreDate(LocalDate date) {
        this.ordreDate = date;
    }

    public int getActionsCount() {
        return actionsCount;
    }

    public void setActionsCount(int actionsCount) {
        this.actionsCount = actionsCount;
    }

    public double getActionPrice() {
        return actionPrice;
    }

    public void setActionPrice(double actionPrice) {
        this.actionPrice = actionPrice;
    }

    @JsonIgnore

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
