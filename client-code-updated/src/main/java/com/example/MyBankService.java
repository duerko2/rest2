package com.example;





import dtu.ws.fastmoney.*;
import dtu.ws.fastmoney.Account;

import java.math.BigDecimal;
import java.util.List;

public class MyBankService {

    BankService bank = new BankServiceService().getBankServicePort();

    public String createAccount(String name, String lastname, String cpr, Integer int1) {

        // SOAP Request to bank
        User user = new User();
        user.setCprNumber(cpr);
        user.setFirstName(name);
        user.setLastName(lastname);

        try {
            List<AccountInfo> accountInfoList = bank.getAccounts();
            for (AccountInfo accountInfo : accountInfoList){
                if(accountInfo.getUser().getCprNumber() == user.getCprNumber()){
                    return accountInfo.getAccountId();
                }
            }
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
