package com.example;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class DTUPayService {

    ResteasyClient client = (ResteasyClient) ResteasyClientBuilder.newClient();
    ResteasyWebTarget target = client.target("http://localhost:8080");

    public String createAccount(String name, String lastname, String s, String customerBankId) throws AccountAlreadyExistsException {
        Account account = new Account();
        account.setName(name);
        account.setLastname(lastname);
        account.setCpr(s);
        account.setBankId(customerBankId);

        Response res = target.path("/account")
                .request()
                .post(Entity.entity(account, "application/json"));

        switch (res.getStatus()) {
            case 200:
                return res.readEntity(String.class);
            case 409:
                throw new AccountAlreadyExistsException("that account already exists");
            default:
                return null;
        }
    }

    public Account getAccount(String customerDTUPayId) throws NoSuchAccountException {
        Response res = target.path("/account/" + customerDTUPayId)
                .request()
                .get();

        if (res.getStatus() == 200) {
            return res.readEntity(Account.class);
        } else {
            throw new NoSuchAccountException("Account doesn't exist");
        }

    }

    public boolean pay(int amount, String merchant, String customer) {

        Payment payment = new Payment(amount, merchant, customer);
        Response res = target.path("/payment")
                .request()
                .post(Entity.entity(payment, "application/json"));

        if (res.getStatus() == 200) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteDTUPayAccount(String cpr) {
        Response res = target.path("/account/" + cpr).request().delete();
    }

}
