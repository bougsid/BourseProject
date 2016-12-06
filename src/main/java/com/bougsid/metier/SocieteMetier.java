package com.bougsid.metier;

import com.bougsid.dao.OrdreRepository;
import com.bougsid.dao.SocieteRepository;
import com.bougsid.entities.Ordre;
import com.bougsid.entities.Societe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ayoub on 11/14/2016.
 */
@Service
public class SocieteMetier implements ISocieteMetier {
    @Autowired
    private SocieteRepository repository;

    @Autowired
    private OrdreMetier ordreMetier;

    @Autowired
    private OrdreRepository ordreRepository;

    @Override
    public Page<Societe> findAll(int page, int size) {
        return this.repository.findAll(new PageRequest(page,size));
    }

    @Override
    public Societe findSocieteByCode(String code) {
        return this.repository.findByCode(code);
    }

    @Override
    public Page<Ordre> getOrdresPage(String code, int page, int size) {
        return this.ordreMetier.getOrdresOfSociete(findSocieteByCode(code), page, size);
    }

    @Override
    public Map<String, Double> getAllInfos(String code) {
        Map<String, Double> infos = new HashMap<>();
        infos.put("totalVente", Double.valueOf(this.totalVenteActions(code)));
        infos.put("totalAchat", Double.valueOf(this.totalAchatActions(code)));
        infos.put("AVGVente", this.AVGVenteActions(code));
        infos.put("AVGAchat", this.AVGAchatActions(code));
        infos.put("estimation", this.priceEstimation());
        return infos;
    }

    @Override
    public int totalVenteActions(String code) {
        List<Ordre> ordresAchat = this.ordreRepository.getListOrdreVente(findSocieteByCode(code));
        return this.getTotalAction(ordresAchat);
    }

    @Override
    public int totalAchatActions(String code) {
        List<Ordre> ordresVente = this.ordreRepository.getListOrdreAchat(findSocieteByCode(code));
        return this.getTotalAction(ordresVente);
    }

    @Override
    public double AVGVenteActions(String code) {
        List<Ordre> ordresVente = this.ordreRepository.getListOrdreVente(findSocieteByCode(code));
        return this.getAVGAction(ordresVente);
    }

    @Override
    public double AVGAchatActions(String code) {
        List<Ordre> ordresAchat = this.ordreRepository.getListOrdreAchat(findSocieteByCode(code));
        return this.getAVGAction(ordresAchat);
    }

    @Override
    public double priceEstimation() {
        return 0;
    }

    private int getTotalAction(List<Ordre> ordres) {
        if (ordres == null || ordres.size() == 0) return 0;
        int totalActions = 0;
        for (Ordre ordre : ordres) {
            totalActions += ordre.getActionsCount();
        }
        return totalActions;
    }

    private double getAVGAction(List<Ordre> ordres) {
        if (ordres == null || ordres.size() == 0) return 0;
        double totalPrice = 0;
        for (Ordre ordre : ordres) {
            totalPrice += ordre.getActionPrice();
        }
        return totalPrice / ordres.size();
    }
//
//    @Override
//    public Page<Societe> getSocietePage(int page, int size) {
//        return this.repository.findAll(new PageRequest(page, size));
//    }


}
