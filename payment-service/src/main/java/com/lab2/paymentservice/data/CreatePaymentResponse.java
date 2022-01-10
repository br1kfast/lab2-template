package com.lab2.paymentservice.data;

import java.util.UUID;

public class CreatePaymentResponse {
    UUID paymentUid;

    public UUID getPaymentUid() {
        return paymentUid;
    }

    public void setPaymentUid(UUID paymentUid) {
        this.paymentUid = paymentUid;
    }
}
