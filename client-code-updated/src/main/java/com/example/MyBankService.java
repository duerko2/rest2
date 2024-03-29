package com.example;


import dtu.ws.fastmoney.*;
import dtu.ws.fastmoney.Account;

import java.math.BigDecimal;
import java.util.List;

public class MyBankService {

    BankService bank = new BankServiceService().getBankServicePort();

    public String createAccount(String name, String lastname, String cpr, Integer int1) {
        BankService bank = new BankServiceService().getBankServicePort();

        // SOAP Request to bank
        User user = new User();
        user.setCprNumber(cpr);
        user.setFirstName(name);
        user.setLastName(lastname);

        try {
            // Returns bank account if exists
            List<AccountInfo> accountInfoList = bank.getAccounts();
            for (AccountInfo accountInfo : accountInfoList) {
                if (accountInfo.getUser().getCprNumber().equals(user.getCprNumber())) {
                    return accountInfo.getAccountId();
                }
            }

            return bank.createAccountWithBalance(user, new BigDecimal(int1));
        } catch (BankServiceException_Exception e) {
            return "NO ACCOUNT CREATED" + e.getMessage();
        }
    }

    public Account getAccount(String customerBankId) {
        BankService bank = new BankServiceService().getBankServicePort();

        try {
            return bank.getAccount(customerBankId);
        } catch (BankServiceException_Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(String id) throws BankServiceException_Exception {
        BankService bank = new BankServiceService().getBankServicePort();
        bank.retireAccount(id);
    }
}
