package com.bougsid.metier;

import com.bougsid.dao.OrdreRepository;
import com.bougsid.entities.Ordre;
import com.bougsid.entities.Societe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by ayoub on 11/14/2016.
 */

@Service
public class OrdreMetier implements IOrdreMetier {
    @Autowired
    private OrdreRepository repository;

    @Override
    public Page<Ordre> getOrdresOfSociete(Societe societe, int page, int size) {
        return this.repository.findBySociete(societe, new PageRequest(page, size));
    }
}
