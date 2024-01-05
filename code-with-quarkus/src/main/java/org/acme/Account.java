package org.acme;

public class Account {

    String name;
    String lastname;
    String cpr;
    String bankId;
    String accountGUID;

    public Account(String name, String lastname, String cpr, String bankId) {
        this.name = name;
        this.lastname = lastname;
        this.cpr = cpr;
        this.bankId = bankId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAccountGUID() {
        return accountGUID;
    }

    public void setAccountGUID(String accountGUID) {
        this.accountGUID = accountGUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }


}
