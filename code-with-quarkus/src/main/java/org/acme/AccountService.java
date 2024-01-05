package org.acme;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private  static  Map<String, Account> accountMap = new HashMap<>();

    public String createAccount(Account account){
        accountMap.put(account.cpr,account);
        return account.cpr;
    }

    public Account getAccount(String accountId){
        return accountMap.getOrDefault(accountId, null);
    }
}
