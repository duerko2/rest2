
package org.acme;

public class Payment {
    String customer;
    String merchant;
    int amount;

    public Payment() {
    }

    public int getAmount() {
        return amount;
    }

    public String getCustomer() {
        return customer;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
}
