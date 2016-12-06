package com.bougsid.services.ordre;

import com.bougsid.dao.OrdreRepository;
import com.bougsid.entities.Ordre;
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
@WebService(serviceName = "ordres")
public class OrdreSOAP {

    private OrdreRepository ordreRepository;

    @WebMethod
    public List<Ordre> getOrdresByPage(@WebParam(name = "page") int page, @WebParam(name = "size") int size) {
        return this.ordreRepository.findAll(new PageRequest(page, size)).getContent();
    }
}
