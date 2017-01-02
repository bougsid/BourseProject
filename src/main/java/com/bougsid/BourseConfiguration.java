package com.bougsid;

import com.bougsid.jwt.JwtFilter;
import com.bougsid.metier.ISocieteMetier;
import com.bougsid.metier.SocieteMetier;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Created by ayoub on 11/12/2016.
 */
@Configuration
public class BourseConfiguration {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

    @Bean
    public SimpleJaxWsServiceExporter getJWS() {
        SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
        exporter.setBaseAddress("http://localhost:8081/");
        return exporter;
    }

    @Bean
    public RmiServiceExporter getRmiServiceExporter(ApplicationContext context) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(context.getBean(SocieteMetier.class));
        exporter.setRegistryPort(1999);
        exporter.setServiceName("societeRmiService");
        exporter.setServiceInterface(ISocieteMetier.class);
        return exporter;
    }

    @Configuration
    public class RabbitConfiguration {

        @Bean
        public ConnectionFactory connectionFactory() {
            return new CachingConnectionFactory("localhost");
        }

        @Bean
        public AmqpAdmin amqpAdmin() {
            return new RabbitAdmin(connectionFactory());
        }

        @Bean
        public RabbitTemplate rabbitTemplate() {
            return new RabbitTemplate(connectionFactory());
        }

        @Bean
        public Queue myQueue() {
            return new Queue("myqueue");
        }
    }


}
