package com.bougsid.entities;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Created by ayoub on 11/14/2016.
 */
@Entity
public class Vente extends Ordre {
    public Vente() {
        super();
    }

    public Vente(LocalDate date,int actionsCount, double actionPrice, Societe societe) {
        this();
        this.ordreDate = date;
        this.actionsCount = actionsCount;
        this.actionPrice = actionPrice;
        this.societe = societe;
    }
}
