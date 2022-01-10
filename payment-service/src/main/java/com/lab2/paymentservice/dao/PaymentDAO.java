package com.lab2.paymentservice.dao;

import com.lab2.paymentservice.data.CreatePaymentResponse;
import com.lab2.paymentservice.data.PaymentData;
import com.lab2.paymentservice.data.StatusResponse;
import com.lab2.paymentservice.data.UserPaymentResponse;

import java.util.UUID;

public interface PaymentDAO {

    UUID createPayment(Integer price);
    PaymentData getPayment(UUID paymentUid);
    StatusResponse cancelPayment(UUID paymentUid);
    UserPaymentResponse getUserPaymentResponse(UUID paymentUid);

}
