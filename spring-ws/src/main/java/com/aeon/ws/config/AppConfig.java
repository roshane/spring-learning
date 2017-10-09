package com.aeon.ws.config;

import com.aeon.ws.client.IpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by roshane on 10/5/2017.
 */
@Configuration
public class AppConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.aeon.ws.integration");
        return marshaller;
    }

    @Bean
    public IpClient ipClient() {
        IpClient ipClient = new IpClient();
        ipClient.setMarshaller(marshaller());
        ipClient.setUnmarshaller(marshaller());
        return ipClient;
    }
}
