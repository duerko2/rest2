package com.example;

public class Payment {
  String customer;
  String merchant;

  int amount;

  String status = "";

  public Payment() {}

  public Payment(int amount, String merchant, String customer) {
    this.amount = amount;
    this.merchant = merchant;
    this.customer = customer;
  }

  public String getStatus() { return status; }

  public int getAmount() { return amount; }

  public String getCustomer() { return customer; }

  public String getMerchant() { return merchant; }

  public void setStatus(String status) { this.status = status; }

  public void setAmount(int amount) { this.amount = amount; }

  public void setCustomer(String customer) { this.customer = customer; }

  public void setMerchant(String merchant) { this.merchant = merchant; }
}
