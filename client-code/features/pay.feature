Feature: Payment
    Scenario: Successful Payment
        Given a customer with id "cid1"
        And a merchant with id "mid1"
        When the merchant initiates a payment for 10 kr by the customer
        Then the payment is successful

    Scenario: Bad actor
        Given a customer with id "BannedUser"
        And a merchant with id "mid1"
        When the merchant initiates a payment for 100 kr by the customer
        Then the payment is not successful

    Scenario: List of payments
        Given a successful payment of 10 kr from customer "cid1" to merchant "mid1"
        When the manager asks for a list of payments
        Then the list contains a payments where customer "cid1" paid 10 kr to merchant "mid1"
    
    Scenario: Customer is not known
        Given a customer with id "cid2"
        And a merchant with id "mid1"
        When the merchant initiates a payment for 10 kr by the customer
        Then the payment is not successful
        And an error message is returned saying "customer with id cid2 is unknown"
