package com.example;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.spi.NotImplementedYetException;

public class DTUPayService {

    ResteasyClient client = (ResteasyClient) ResteasyClientBuilder.newClient();
    ResteasyWebTarget target = client.target("http://localhost:8080");
    String status = "";

    public String createAccount(String name, String lastname, String s, String customerBankId) throws NoSuchFieldException {
        Account account = new Account();
        account.setName(name);
        account.setLastname(lastname);
        account.setCpr(s);
        account.setBankId(customerBankId);

        var n = Entity.entity(account, "application/json");
        System.out.println(n.toString());

        Response res = target.path("/account")
                .request()
                .post(Entity.entity(account, "application/json"));
        System.out.println(res.getStatus());

        if (res.getStatus() == 200) {
            status = "Complete";
            var returnValue = res.readEntity(String.class);
            return returnValue;
        } else {
            status = res.readEntity(String.class);
            throw new NoSuchFieldException();
        }
    }

    public Account getAccount(String customerDTUPayId) {
        Response res = target.path("/account/"+customerDTUPayId)
                .request()
                .get();

        if (res.getStatus() == 200) {
            status = "Complete";
            return res.readEntity(Account.class);
        } else {
            status = "error";
            return null;
        }

    }

    public boolean pay(Integer int1, String merchantDTUPayId, String customerDTUPayId) {
        throw new NotImplementedYetException();
    }
}
