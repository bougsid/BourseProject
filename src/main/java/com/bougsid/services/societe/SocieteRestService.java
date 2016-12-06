package com.bougsid.services.societe;

import com.bougsid.entities.Ordre;
import com.bougsid.entities.Societe;
import com.bougsid.metier.SocieteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by ayoub on 11/14/2016.
 */
@RestController
@RequestMapping("/societe/{code}")
public class SocieteRestService {
    @Autowired
    private SocieteMetier metier;

    @GetMapping()
    public Societe findSocieteByCode(@PathVariable(name = "code") String code) {
        return this.metier.findSocieteByCode(code);
    }

    @GetMapping("/ordres/{page}/{size}")
    public Page<Ordre> getOrdresPage(@PathVariable(name = "code") String code, @PathVariable(name = "page") int page, @PathVariable(name = "size") int size) {
        return this.metier.getOrdresPage(code, page, size);
    }

    @GetMapping("/infos")
    Map<String, Double> getAllInfos(@PathVariable(name = "code") String code) {
        return this.metier.getAllInfos(code);
    }

    @GetMapping("/totalVenteActions")
    Map<String, Integer> totalVenteActions(@PathVariable(name = "code") String code) {
        return Collections.singletonMap("totalVente", this.metier.totalVenteActions(code));
    }

    @GetMapping("/totalAchatActions")
    Map<String, Integer> totalAchatActions(@PathVariable(name = "code") String code) {
        return Collections.singletonMap("totalAchat", this.metier.totalAchatActions(code));
    }

    @GetMapping("/AVGVenteActions")
    Map<String, Double> AVGVenteActions(@PathVariable(name = "code") String code) {
        return Collections.singletonMap("AVGVente", this.metier.AVGVenteActions(code));
    }

    @GetMapping(value = "/AVGAchatActions", produces = "application/json")
    Map<String, Double> AVGAchatActions(@PathVariable(name = "code") String code) {
        return Collections.singletonMap("AVGAchat", this.metier.AVGAchatActions(code));
    }

    @GetMapping("/priceEstimation")
    double priceEstimation() {
        return 0;
    }
}
