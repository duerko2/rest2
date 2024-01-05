package org.acme;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/payment")
public class GreetingResource {

  PaymentService pService = new PaymentService();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPayments() {

    return Response.ok(pService.getPayments()).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response receivePayment(Payment payment) {
    if (payment.customer.equals("cid1")) {
      pService.addPayment(payment);
      return Response.ok(payment.customer).build();
    } else {
      return Response.status(403)
          .entity("customer with id " + payment.customer + " is unknown")
          .build();
    }
  }
}
