package com.example.consumingwebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.wsdl.GetCountryRequest;
import com.example.consumingwebservice.wsdl.GetCountryResponse;

public class CountrySoapConsumer extends WebServiceGatewaySupport {
    private static final String SOAP_ENDPOINT = "http://localhost:8080/ws/countries";
    private static final String SOAP_ACTION = "https://javaee.github.io/metro-saaj/";
    private static final Logger log = LoggerFactory.getLogger(CountrySoapConsumer.class);

    public GetCountryResponse getCountry(String country) {
        log.info("Requesting data for country: " + country);
        return (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(
                SOAP_ENDPOINT,
                createCountryRequest(country),
                new SoapActionCallback(SOAP_ACTION));
    }

    private GetCountryRequest createCountryRequest(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);
        return request;
    }

}
