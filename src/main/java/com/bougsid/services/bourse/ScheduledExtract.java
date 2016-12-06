package com.bougsid.services.bourse;

import com.bougsid.dao.SocieteRepository;
import com.bougsid.entities.Societe;
import com.bougsid.metier.ISocieteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by ayoub on 11/30/2016.
 */
@Component
public class ScheduledExtract {

    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private ISocieteMetier societeMetier;

    @Scheduled(fixedRate = 5000)
    public void checkForUpdate() throws IOException {
        System.out.println("Run Extractor Service And Send Notification");
//        notificationsService.sendNotification(BourseExtracrtor.extractData());
        List<Societe> societes = societeRepository.findAll();
        societes.forEach(s -> {
            try {
                s.setInfos(societeMetier.getAllInfos(s.getCode()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        notificationsService.sendNotification(societes);
    }
}
