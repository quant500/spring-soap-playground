package com.example.producingwebservice;

import com.example.producingwebservice.mockdata.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

/**
 * This SOAP Endpoint handles all incoming requests.
 */
@Endpoint // registers the class with Spring WS as a Web Service Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private final CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // defines the handler method according to the namespace and localPart attributes
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    // indicates that this method returns a value to be mapped to the response payload
    @ResponsePayload
    public GetCountryResponse getCountry(
            //  indicates that this method accepts a parameter to be mapped from the incoming request
            @RequestPayload GetCountryRequest request
    ) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
