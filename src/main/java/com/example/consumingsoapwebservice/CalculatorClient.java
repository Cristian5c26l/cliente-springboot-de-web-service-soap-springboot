package com.example.consumingsoapwebservice;

import com.example.consumingsoapwebservice.wsdl.PlusRequest;
import com.example.consumingsoapwebservice.wsdl.PlusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CalculatorClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CalculatorClient.class);

    public PlusResponse getPlus(Integer numA, Integer numB) {

        PlusRequest request = new PlusRequest();

        request.setNumberA(numA);
        request.setNumberB(numB);

        log.info("Requesting Plus of {} and {}", request.getNumberA(), request.getNumberB());

        String server = "http://localhost:8080/ws";
        String xsdNameFile = "calculator";
        String calculatorXSDTargetNamespace = "http://example.com/demospringwithsoap";

        return (PlusResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        server + "/" + xsdNameFile,
                        request,
                        new SoapActionCallback(calculatorXSDTargetNamespace + "/PlusRequest")
                );// getWebServiceTemplate comes from WebServiceGatewaySupport
    }
}
