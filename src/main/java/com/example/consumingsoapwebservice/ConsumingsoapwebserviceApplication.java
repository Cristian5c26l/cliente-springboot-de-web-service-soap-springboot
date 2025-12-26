package com.example.consumingsoapwebservice;

import com.example.consumingsoapwebservice.wsdl.GetBankAccountResponse;
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
    ApplicationRunner lookup(BankAccountClient client) {
        return args -> {

            List<String> accountNumberOption = args.getOptionValues("accountNumber");

            String accountNumber = accountNumberOption == null || accountNumberOption.isEmpty() ? "MX-001" : accountNumberOption.getFirst();

            GetBankAccountResponse response = client.getBankAccount(accountNumber);
            logger.info("[+] Response (BankAccount)\n\n [*] Account Holder: {}\n[*] Account Balance: {}\n\n", response.getBankAccount().getAccountHolder(), response.getBankAccount().getBalance());
        };
    }
}
