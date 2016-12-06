package com.bougsid.metier;

import com.bougsid.entities.Ordre;
import com.bougsid.entities.Societe;
import org.springframework.data.domain.Page;

/**
 * Created by ayoub on 11/14/2016.
 */
public interface IOrdreMetier {
    Page<Ordre> getOrdresOfSociete(Societe societe, int page, int size);
}
