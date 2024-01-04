package com.example.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.Payment;
import com.example.SimpleDTUPay;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

public class SimpleDTUPaySteps {
    String cid, mid;
    SimpleDTUPay dtuPay = new SimpleDTUPay();
    boolean successful;
    List<Payment> payments;

    @Given("a customer with id {string}")
    public void aCustomerWithId(String cid) {
        this.cid = cid;
    }

    @Given("a merchant with id {string}")
    public void aMerchantWithId(String mid) {
        this.mid = mid;
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(int amount) {
        successful = dtuPay.pay(amount, mid, cid);
    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertTrue(successful);
    }

    @Given("a successful payment of {int} kr from customer {string} to merchant {string}")
    public void a_successful_payment_of_kr_from_customer_to_merchant(Integer int1,
            String string,
            String string2) {
        // Write code here that turns the phrase above into concrete actions
        successful = dtuPay.pay(int1, string2, string);
        assertTrue(successful);
    }

    @When("the manager asks for a list of payments")
    public void the_manager_asks_for_a_list_of_payments() {
        // Write code here that turns the phrase above into concrete actions
        payments = dtuPay.getPaymentList();
    }

    @Then("the list contains a payments where customer {string} paid {int} kr to merchant {string}")
    public void the_list_contains_a_payments_where_customer_paid_kr_to_merchant(
            String string, Integer int1, String string2) {
        // Write code here that turns the phrase above into concrete actions
        Payment p = null;

        for (Payment payment : payments) {
            if (payment.getCustomer().equals(string) && payment.getAmount() == int1 &&
                    payment.getMerchant().equals(string2)) {
                p = payment;
            }
        }
        assertNotNull(p);
    }

    @Then("the payment is not succesful")
    public void the_payment_is_not_succesful() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(successful);
    }
}
