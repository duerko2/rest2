package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Singleton
public class AccountService {

    private Map<String, Account> accountMap = new HashMap<>();

    public String createAccount(Account account) throws CustomerAlreadyExists {
        String accountId = UUID.randomUUID().toString();
        if (!accountExists(account.getCpr())) {
            account.setAccountId(accountId);
            accountMap.put(accountId, account);
            return account.getAccountId();
        } else throw new CustomerAlreadyExists("that account already exist");
    }
    private boolean accountExists(String cpr){
        return accountMap.values().stream().anyMatch(a -> a.getCpr().equals(cpr));
    }

    public Account getAccount(String accountId) {
        return accountMap.getOrDefault(accountId, null);
    }

    public void deleteAccount(String accountId) {
        if(accountMap.containsKey(accountId)){
        accountMap.remove(accountId);
        }
    }
}
