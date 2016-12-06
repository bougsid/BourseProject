package com.bougsid.dao;

import com.bougsid.entities.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by ayoub on 11/12/2016.
 */
@RepositoryRestResource
public interface SocieteRepository extends JpaRepository<Societe, Long> {
    Societe findByCode(String code);
}
