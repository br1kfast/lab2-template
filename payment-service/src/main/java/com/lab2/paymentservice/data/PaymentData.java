package com.lab2.paymentservice.data;

import java.util.UUID;

public class PaymentData {

    private Long id;
    private UUID paymentUid;
    private String status;
    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPayment_uid() {
        return paymentUid;
    }

    public void setPayment_uid(UUID payment_uid) {
        this.paymentUid = payment_uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
