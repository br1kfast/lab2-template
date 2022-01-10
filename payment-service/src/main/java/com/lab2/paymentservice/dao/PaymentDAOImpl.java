package com.lab2.paymentservice.dao;

import com.lab2.paymentservice.data.CreatePaymentResponse;
import com.lab2.paymentservice.data.PaymentData;
import com.lab2.paymentservice.data.StatusResponse;
import com.lab2.paymentservice.data.UserPaymentResponse;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PaymentDAOImpl implements PaymentDAO{


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public UUID createPayment(Integer price){
        UUID u = UUID.randomUUID();
        jdbcTemplate.update(
                "insert into payment(paymentUid,status,price) values (:paymentUid,:status,:price)",
                new MapSqlParameterSource()
                        .addValue("paymentUid", u)
                        .addValue("status", "PAID")
                        .addValue("price", price));
        return u;
    }

    @Override
    public PaymentData getPayment(UUID paymentUid){
        return jdbcTemplate.queryForObject(
                "select*from payment where paymentUid =:paymentUid",
                new MapSqlParameterSource().addValue("paymentUid", paymentUid),
                new BeanPropertyRowMapper<>(PaymentData.class));

    }

    @Override
    public StatusResponse cancelPayment(UUID paymentUid){
        jdbcTemplate.update(
                "update payment set status='CANCELED' where paymentUid =:paymentUid",
                new MapSqlParameterSource()
                        .addValue("paymentUid", paymentUid));
        return jdbcTemplate.queryForObject(
                "select status from payment where paymentUid =:paymentUid",
                new MapSqlParameterSource().addValue("paymentUid", paymentUid),
                new BeanPropertyRowMapper<>(StatusResponse.class));
    }

    @Override
    public UserPaymentResponse getUserPaymentResponse(UUID paymentUid){
        return jdbcTemplate.queryForObject(
                "select status,price from payment where paymentUid =:paymentUid",
                new MapSqlParameterSource().addValue("paymentUid", paymentUid),
                new BeanPropertyRowMapper<>(UserPaymentResponse.class));
    }


}
