    package org.acme;

    import jakarta.inject.Inject;
    import jakarta.ws.rs.Path;

    public class MasterResource {
        @Inject
        private PaymentResource paymentResource;

        @Inject
        private AccountResource accountResource;

        public PaymentResource getPaymentResource() {
            return paymentResource;
        }

        public AccountResource getAccountResource() {
            return accountResource;
        }
    }
