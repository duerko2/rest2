package org.acme;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/payments")

public class PaymentResource {

    @Inject
  PaymentService pService ;
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPayments() {

    return Response.ok(pService.getPayments()).build();
  }



    @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response handlePayment(Payment payment){

      try {
          pService.handlePayment(payment);
      } catch (BankServiceException_Exception e) {
          return Response.status(Response.Status.NOT_FOUND).build();
      }

      return Response.ok().build();

  }
}
