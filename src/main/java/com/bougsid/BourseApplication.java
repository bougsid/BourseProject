package com.bougsid;

import com.bougsid.dao.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class BourseApplication implements CommandLineRunner {

    @Autowired
    private SocieteRepository societeRepository;

    public static void main(String[] args) {
        SpringApplication.run(BourseApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
//        societeRepository.save(BourseExtracrtor.extractData());
//        for (int i = 0; i < 10; i++) {
//            Societe societe = new Societe( String.valueOf(i * 15),"Societe" + i);
//            Ordre ordre1 = new Achat(i * 2 + 1, 122 * i, societe);
//            Ordre ordre2 = new Achat(i * 5 + 1, 154 * i, societe);
//            Ordre ordre3 = new Vente(i * 4 + 1, 122 * i, societe);
//            Ordre ordre4 = new Vente(i * 8 + 1, 122 * i, societe);
//            Ordre ordre5 = new Vente(i * 4 + 1, 45 * i, societe);
//            societe.addOrdre(ordre1);
//            societe.addOrdre(ordre2);
//            societe.addOrdre(ordre3);
//            societe.addOrdre(ordre4);
//            societe.addOrdre(ordre5);
//            this.societeRepository.save(societe);
//        }
    }
}
