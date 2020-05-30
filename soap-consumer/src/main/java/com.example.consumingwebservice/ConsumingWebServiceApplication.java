package com.example.consumingwebservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.GetCountryResponse;

@SpringBootApplication
public class ConsumingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
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
