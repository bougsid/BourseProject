package com.bougsid.dao;

import com.bougsid.entities.Ordre;
import com.bougsid.entities.Societe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by ayoub on 11/12/2016.
 */
@RepositoryRestResource
public interface OrdreRepository extends JpaRepository<Ordre, Long> {

    Page<Ordre> findBySociete(Societe societe, Pageable pageable);

    @Query("select o from Achat o where o.societe = ?1")
    List<Ordre> getListOrdreAchat(Societe societe);

    @Query("select o from Vente o where o.societe = ?1")
    List<Ordre> getListOrdreVente(Societe societe);
}
