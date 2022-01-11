package com.lab2.paymentservice.controller;

import com.lab2.paymentservice.dao.PaymentDAOImpl;
import com.lab2.paymentservice.data.CreatePaymentResponse;
import com.lab2.paymentservice.data.PaymentData;
import com.lab2.paymentservice.data.StatusResponse;
import com.lab2.paymentservice.data.UserPaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payment/")
public class PaymentController {

    @Autowired
    PaymentDAOImpl paymentDAO;

    @PostMapping("/create/{price}")
    public UUID createPayment(@PathVariable("price") Integer price){
        return paymentDAO.createPayment(price);
    }
    @GetMapping("/getStatus/{paymentUid}")
    public String getPaymentStatus(@PathVariable("paymentUid") UUID paymentUid){
        return paymentDAO.getPaymentStatus(paymentUid);
    }

    @GetMapping("/get/{paymentUid}")
    public PaymentData getPayment(@PathVariable("paymentUid") UUID paymentUid){
        return paymentDAO.getPayment(paymentUid);
    }

    @PostMapping("/cancel/{paymentUid}")
    public String cancelPayment(@PathVariable("paymentUid") UUID paymentUid){
        StatusResponse statusResponse = paymentDAO.cancelPayment(paymentUid);
        String status = statusResponse.getStatus();
        return status;
    }

    @GetMapping("/getUserPaymentResponse/{paymentUid}")
    public UserPaymentResponse getUserPaymentResponse(@PathVariable("paymentUid") UUID paymentUid){
        return paymentDAO.getUserPaymentResponse(paymentUid);
    }




}
