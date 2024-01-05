package org.acme;


import dtu.ws.fastmoney.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

  List<Payment> payments = new ArrayList<>();
  BankService bank = new BankServiceService().getBankServicePort();
  AccountService accountService = new AccountService();


  public void addPayment(Payment p) {
    payments.add(p);
  }



  public List<Payment> getPayments() { return payments; }


  public void handlePayment(Payment payment) throws BankServiceException_Exception {
    Account customer = accountService.getAccount(payment.getCustomer());
    Account merchant = accountService.getAccount(payment.getMerchant());
    if(customer == null || merchant == null){
      throw new BankServiceException_Exception("Error no such account exists",null);
    }

    bank.transferMoneyFromTo(customer.getBankId(),merchant.getBankId(), BigDecimal.valueOf(payment.getAmount()),"Money!");

  }
}
