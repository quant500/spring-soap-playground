package com.example.consumingwebservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountryConfiguration {
    public static final String CONTEXT_PATH = "com.example.consumingwebservice.wsdl";
    public static final String DEFAULT_URI = "http://localhost:8080/ws";

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage>
        // specified in pom.xml
        marshaller.setContextPath(CONTEXT_PATH);
        return marshaller;
    }

    @Bean
    public CountrySoapConsumer countryClient(Jaxb2Marshaller marshaller) {
        CountrySoapConsumer client = new CountrySoapConsumer();
        client.setDefaultUri(DEFAULT_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
