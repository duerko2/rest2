package com.example;


import com.example.dtu.ws.fastmoney.*;
import java.math.BigDecimal;

public class MyBankService {

    BankService bank = new BankServiceService().getBankServicePort();

    public String createAccount(String name, String lastname, String cpr, Integer int1) {

        // SOAP Request to bank
        User user = new User();
        user.setCprNumber(cpr);
        user.setFirstName(name);
        user.setLastName(lastname);

        try {
            return bank.createAccountWithBalance(user,new BigDecimal(int1));
        } catch (BankServiceException_Exception e) {
            return "NO ACCOUNT CREATED";
        }
    }

    public Account getAccount(String customerBankId) {
        try {
            return bank.getAccount(customerBankId);
        } catch (BankServiceException_Exception e) {
            throw new RuntimeException(e);
        }
    }
}
