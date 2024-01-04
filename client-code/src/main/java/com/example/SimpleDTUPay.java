package com.example;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class SimpleDTUPay {

    ResteasyClient client = (ResteasyClient) ResteasyClientBuilder.newClient();
    ResteasyWebTarget target = client.target("http://localhost:8080");

    public boolean pay(int amount, String merchant, String customer) {

        Payment payment = new Payment(amount, merchant, customer);

        Response res = target.path("/payment")
                .request()
                .post(Entity.entity(payment, "application/json"));

        if (res.getStatus() == 200) {
            System.out.println(res.readEntity(String.class));
            return true;
        } else {
            return false;
        }
    }

    public List<Payment> getPaymentList() {

        Response res = target.path("/payment")
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        List<Payment> payments = res.readEntity(new GenericType<List<Payment>>() {
        });

        return payments;
    }
}
