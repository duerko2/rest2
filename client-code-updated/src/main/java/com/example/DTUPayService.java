package com.example;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

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

                Response res = target.path("/account")
                .request()
                .post(Entity.entity(account, "application/json"));
        System.out.println(res.getStatus());

        if (res.getStatus() == 200) {
            status = "Complete";
            var returnValue = res.readEntity(String.class);
            return returnValue;
        } else {
            status="that account already exists";
            return res.readEntity(String.class);
        }
    }

    public Account getAccount(String customerDTUPayId) throws NoSuchFieldException {
        Response res = target.path("/account/"+customerDTUPayId)
                .request()
                .get();

        if (res.getStatus() == 200) {
            status = "Complete";
            return res.readEntity(Account.class);
        } else {
            status = "error";
            throw new NoSuchFieldException("Account doesn't exist");
        }

    }

    public boolean pay(int amount, String merchant, String customer) {

        Payment payment = new Payment(amount, merchant, customer);
        Response res = target.path("/payment")
                .request()
                .post(Entity.entity(payment, "application/json"));

        if (res.getStatus() == 200) {
            status = "Complete";
            return true;
        } else {
            status = res.readEntity(String.class);
            return false;
        }
    }
    public void deleteDTUPayAccount(String cpr) {
        Response res = target.path("/account/"+ cpr).request().delete();
    }

    public String getStatus() {
        return status;
    }
}
