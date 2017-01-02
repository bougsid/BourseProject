package com.bougsid.services.bourse;

import com.bougsid.entities.Societe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ayoub on 11/28/2016.
 */

@Service
public class NotificationsService {
    @Autowired
    private ApplicationContext context;

    public void sendNotification(List<Societe> societes) throws JsonProcessingException {
        System.out.println("context" + context);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("bourse-queue", new ObjectMapper().writeValueAsString(societes));
    }
}
