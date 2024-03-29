package com.example.cucumber;

import com.example.AccountAlreadyExistsException;
import com.example.MyBankService;
import com.example.DTUPayService;
import com.example.NoSuchAccountException;
import dtu.ws.fastmoney.BankServiceException_Exception;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DTUPaySoapSteps {

    String customerName = "name";
    String customerLastName = "lastname";
    String customerCPR = "customerCPR";
    String merchantName = "merchantName";
    String merchantLastName = "merchantlastname";
    String merchantCPR = "merchantCPR";

    String customerBankId;
    String customerDTUPayId;
    String merchantBankId;
    String merchantDTUPayId;
    boolean successful;
    String errorMessage;

    MyBankService myBankService = new MyBankService();
    DTUPayService dtuPayService = new DTUPayService();

    @Given("a customer with a bank account with balance {int}")
    public void a_customer_with_a_bank_account_with_balance(int int1) throws BankServiceException_Exception {
        // Write code here that turns the phrase above into concrete actions
        customerBankId = myBankService.createAccount(customerName, customerLastName, customerCPR, int1);
        assertTrue(myBankService.getAccount(customerBankId).getBalance().equals(new BigDecimal(int1)));
    }

    @Given("that the customer is registered with DTU Pay")
    public void that_the_customer_is_registered_with_dtu_pay() throws NoSuchAccountException, AccountAlreadyExistsException {
        // Write code here that turns the phrase above into concrete actions
        customerDTUPayId = dtuPayService.createAccount(customerName, customerLastName, customerCPR, customerBankId);
        assertEquals(customerDTUPayId,dtuPayService.getAccount(customerDTUPayId).getAccountId());
    }

    @Given("a merchant with a bank account with balance {int}")
    public void a_merchant_with_a_bank_account_with_balance(int int1) {
        // Write code here that turns the phrase above into concrete actions
        merchantBankId = myBankService.createAccount(merchantName, merchantLastName, merchantCPR, int1);
        assertTrue(myBankService.getAccount(merchantBankId).getBalance().equals(new BigDecimal(int1)));
    }

    @Given("that the merchant is registered with DTU Pay")
    public void that_the_merchant_is_registered_with_dtu_pay() throws NoSuchAccountException, AccountAlreadyExistsException {
        // Write code here that turns the phrase above into concrete actions
        merchantDTUPayId = dtuPayService.createAccount(merchantName, merchantLastName, merchantCPR, merchantBankId);
        assertEquals(merchantDTUPayId,dtuPayService.getAccount(merchantDTUPayId).getAccountId());
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void the_merchant_initiates_a_payment_for_kr_by_the_customer(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        successful = dtuPayService.pay(int1, merchantDTUPayId, customerDTUPayId);
    }

    @Then("the payment is successful")
    public void the_payment_is_successful() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(successful);
    }

    @Then("the balance of the customer at the bank is {int} kr")
    public void the_balance_of_the_customer_at_the_bank_is_kr(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(BigDecimal.valueOf(int1), myBankService.getAccount(customerBankId).getBalance());
    }

    @Then("the balance of the merchant at the bank is {int} kr")
    public void the_balance_of_the_merchant_at_the_bank_is_kr(Integer int1) {
        // Write code here that turns the phrase above into concrete action
        assertEquals(BigDecimal.valueOf(int1), myBankService.getAccount(merchantBankId).getBalance());

    }

    @Given("a customer without a bank account")
    public void a_customer_without_a_bank_account() {
        // Write code here that turns the phrase above into concrete actions
        customerBankId = "Quark" ;
    }

    @Then("the payment is not successful")
    public void the_payment_is_not_successful() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(successful);
    }
    @Given("that the customer is not registered with DTU Pay")
    public void that_the_customer_is_not_registered_with_dtu_pay() {
        // Write code here that turns the phrase above into concrete actions
        assertThrows(NoSuchAccountException.class,() ->{
            dtuPayService.getAccount(customerDTUPayId);
        });
    }
    @When("a customer tries to create a new DTU Pay account")
    public void a_customer_tries_to_create_a_new_dtu_pay_account() {
        // Write code here that turns the phrase above into concrete actions
        try{
            customerDTUPayId = dtuPayService.createAccount(customerName, customerLastName, customerCPR, customerBankId);
        } catch (AccountAlreadyExistsException e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("it returns an error saying {string}")
    public void it_returns_an_error_saying(String string) {
        // Write code here that turns the phrase above into concrete actions
            assertEquals(string, errorMessage);
    }


    @After
    public void cleanUp() {
        try {
            myBankService.deleteAccount(customerBankId);
        } catch (Exception e) {}
        try{
            myBankService.deleteAccount(merchantBankId);
        } catch (Exception e){}
        dtuPayService.deleteDTUPayAccount(customerDTUPayId);
        dtuPayService.deleteDTUPayAccount(merchantDTUPayId);
    }
}
