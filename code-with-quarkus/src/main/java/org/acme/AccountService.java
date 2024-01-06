package org.acme;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private  static  Map<String, Account> accountMap = new HashMap<>();

    public String createAccount(Account account) throws CustomerAlreadyExists{
        System.out.println(account+ account.getCpr());

        if (!accountMap.containsKey(account.getCpr())){
            accountMap.put(account.getCpr(),account);
            return account.getCpr();
        }else throw new  CustomerAlreadyExists("that account already exist");
    }

    public Account getAccount(String accountId){
        return accountMap.getOrDefault(accountId, null);
    }

    public void deleteAccount(String cpr) {
        System.out.println("Pre delete");
        for(Account account : accountMap.values()){
            System.out.println(account.getCpr());
        }
        accountMap.remove(cpr);
        System.out.println("Post delete");
        for(Account account : accountMap.values()){
            System.out.println(account.getCpr());
        }
    }
}
