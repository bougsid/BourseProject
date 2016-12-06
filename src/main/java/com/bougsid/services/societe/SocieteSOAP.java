package com.bougsid.services.societe;

import com.bougsid.dao.SocieteRepository;
import com.bougsid.entities.Societe;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by ayoub on 11/12/2016.
 */
@Service
@WebService(serviceName = "societes")
public class SocieteSOAP {

    private SocieteRepository societeRepository;

    @WebMethod
    public List<Societe> getSocietesByPage(@WebParam(name = "page") int page, @WebParam(name = "size") int size) {
        return this.societeRepository.findAll(new PageRequest(page, size)).getContent();
    }
}
