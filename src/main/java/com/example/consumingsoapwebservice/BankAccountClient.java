package com.example.consumingsoapwebservice;

import com.example.consumingsoapwebservice.wsdl.GetBankAccountRequest;
import com.example.consumingsoapwebservice.wsdl.GetBankAccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class BankAccountClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(BankAccountClient.class);

    public GetBankAccountResponse getBankAccount(String accountNumber) {

        GetBankAccountRequest request = new GetBankAccountRequest();

        request.setAccountNumber(accountNumber);

        logger.info("Requesting accountNumber: {}", request.getAccountNumber());

        String server = "http://localhost:8080/services";
        String wsdlNameFile = "bankAccounts";
        String bankAccountXSDTargetNamespace = "http://example.com/demospringwithsoap";

        return (GetBankAccountResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        server + "/" + wsdlNameFile,
                        request,
                        new SoapActionCallback(bankAccountXSDTargetNamespace + "/GetBankAccountRequest")
                );// getWebServiceTemplate comes from WebServiceGatewaySupport
    }
}
