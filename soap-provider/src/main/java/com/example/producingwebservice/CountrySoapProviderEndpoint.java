package com.example.producingwebservice;

import com.example.producingwebservice.mockdata.CountryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ch.domain.ws.country_namespace.GetCountryRequest;
import ch.domain.ws.country_namespace.GetCountryResponse;

/**
 * This SOAP Endpoint handles all incoming requests.
 */
@Endpoint // registers the class with Spring WS as a Web Service Endpoint
public class CountrySoapProviderEndpoint {
    private static final String NAMESPACE_URI = "http://domain.ch/ws/country-namespace";
    private final CountryDataRepository countryDataRepository;

    /*
    Der Konstruktor im Beispiel muss nicht näher markiert werden. Gibt es mehr als einen Konstruktor, so muss derjenige,
    der genutzt werden soll, mit der Spring-Annotation @Autowired oder dem JSR-330-Pendant @Inject markiert werden.
     */
    @Autowired
    public CountrySoapProviderEndpoint(CountryDataRepository countryDataRepository) {
        this.countryDataRepository = countryDataRepository;
    }

    // defines the handler method according to the namespace and localPart attributes
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    // indicates that this method returns a value to be mapped to the response payload
    @ResponsePayload
    public GetCountryResponse getCountry(
            //  indicates that this method accepts a parameter to be mapped from the incoming request
            @RequestPayload GetCountryRequest getCountryRequest
    ) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryDataRepository.findCountry(getCountryRequest.getName()));

        return response;
    }

}
