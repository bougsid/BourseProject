package com.bougsid.metier;

import com.bougsid.entities.Ordre;
import com.bougsid.entities.Societe;
import org.springframework.data.domain.Page;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by ayoub on 11/14/2016.
 */
public interface ISocieteMetier extends Remote {

    Page<Societe> findAll(int page, int size) throws RemoteException;
    Societe findSocieteByCode(String code) throws RemoteException;

    Page<Ordre> getOrdresPage(String code, int page, int size) throws RemoteException;

    Map<String, Double> getAllInfos(String code) throws RemoteException;

    int totalVenteActions(String code) throws RemoteException;

    int totalAchatActions(String code) throws RemoteException;

    double AVGVenteActions(String code) throws RemoteException;

    double AVGAchatActions(String code) throws RemoteException;

    double priceEstimation() throws RemoteException;
}
