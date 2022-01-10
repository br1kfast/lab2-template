package com.lab2.reservacationservice.data;

public class UserPaymentResponse {
    String status;
    Integer price;

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
