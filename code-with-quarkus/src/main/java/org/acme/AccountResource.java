package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/account")

public class AccountResource {
    AccountService accountService = new AccountService();

    @GET
    @Path("{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("accountId") String accountId) {
        Account account = accountService.getAccount(accountId);
        if (account != null) {
            return Response.ok(account).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAccount(Account account) {
        try {
            String accountId = accountService.createAccount(account);
            return Response.ok(accountId).build();
        } catch (CustomerAlreadyExists e) {
            return Response.status(400).entity(account.getCpr()).build();
        }

    }
    @DELETE
    @Path("/{cpr}")
    public Response deleteAccount(@PathParam("cpr") String cpr) {
        accountService.deleteAccount(cpr);
        return Response.ok().build();
    }

}
