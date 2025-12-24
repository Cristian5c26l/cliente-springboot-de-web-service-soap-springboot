package com.example.consumingsoapwebservice;

import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CalculatorConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the packageName tag configured in the pom.xml en jaxws-maven-plugin
        marshaller.setContextPath("com.example.consumingsoapwebservice.wsdl");
        return marshaller;
    }

    @Bean
    public CalculatorClient countryClient(WebServiceTemplateBuilder builder, Jaxb2Marshaller marshaller) {
        builder = builder.setMarshaller(marshaller).setUnmarshaller(marshaller);

        CalculatorClient client = new CalculatorClient();
        client.setWebServiceTemplate(builder.build());
        client.setDefaultUri("http://localhost:8080/ws");
        return client;
    }

}
