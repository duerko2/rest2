package org.acme;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

  List<Payment> payments = new ArrayList<>();

  public void addPayment(Payment p) { payments.add(p); }

  public List<Payment> getPayments() { return payments; }
}
