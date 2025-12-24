package com.example.consumingsoapwebservice;

import com.example.consumingsoapwebservice.wsdl.PlusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ConsumingsoapwebserviceApplication {

    Logger logger = LoggerFactory.getLogger(ConsumingsoapwebserviceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumingsoapwebserviceApplication.class, args);
    }

    @Bean
    ApplicationRunner lookup(CalculatorClient client) {
        return args -> {

            List<String> numberA = args.getOptionValues("numberA");
            List<String> numberB = args.getOptionValues("numberB");

            Integer numA = numberA == null || numberA.isEmpty() ? 3 : Integer.parseInt( numberA.getFirst() );
            Integer numB = numberB == null || numberB.isEmpty() ? 10 : Integer.parseInt( numberB.getFirst() );

            PlusResponse response = client.getPlus(numA, numB);
            logger.info("Response (plus result): " + response.getResult());
        };
    }
}
