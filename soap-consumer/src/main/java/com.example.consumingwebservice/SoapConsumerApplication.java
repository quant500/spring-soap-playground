package com.example.consumingwebservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.GetCountryResponse;

@SpringBootApplication
public class SoapConsumerApplication {

    public static void main(String[] args) {
        // In einer Spring-Boot-Anwendung wird der Spring-Kontext über die Klasse SpringApplication erzeugt
        ConfigurableApplicationContext ctx = SpringApplication.run(SoapConsumerApplication.class, args);
        System.out.println("---> ctx = " + ctx);
    }

    @Bean
    CommandLineRunner lookup(CountrySoapConsumer countrySoapConsumer) {
        return args -> {
            String country = "United Kingdom";
            if (args.length > 0) {
                country = args[0];
            }

            GetCountryResponse response = countrySoapConsumer.getCountry(country);

            System.err.println(response.getCountry().getName());
            System.err.println(response.getCountry().getCapital());
            System.err.println(response.getCountry().getCurrency());
            System.err.println(response.getCountry().getPopulation());
        };
    }

}
