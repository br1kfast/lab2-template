package com.lab2.reservacationservice.dao;

import com.lab2.reservacationservice.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;
import java.util.UUID;


@Repository
public class ReservationDAOImpl implements ReservationDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ReservationData> getReservations(String username){
        return jdbcTemplate.query(
                "select*from reservation where username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username),
                new BeanPropertyRowMapper<>(ReservationData.class));
    }

    @Override
    public ReservationData getReservationById(String username, UUID reservationUid) {

        return jdbcTemplate.queryForObject(
                "select*from reservation where username = :username and reservationUid = :reservationUid",
                new MapSqlParameterSource()
                        .addValue("username", username)
                        .addValue("reservationUid", reservationUid),
                new BeanPropertyRowMapper<>(ReservationData.class));
    }

    @Override
    public CreateReservationResponse createReservation(String username, UUID hotelUid, Date startDate, Date endDate) {

        //get discount and get new price
        HotelPriceResponse hotelPriceResponse = jdbcTemplate.queryForObject(
                "select price from hotels where hotelUid =:hotelUid",
                new MapSqlParameterSource().addValue("hotelUid", hotelUid),
                new BeanPropertyRowMapper<>(HotelPriceResponse.class));

        Integer hotelPrice = hotelPriceResponse.getPrice();

        Integer discount = restTemplate.getForObject("http://localhost:8082/api/v1/discount/" + username, Integer.class);
        long l = endDate.getTime() - startDate.getTime();
        long i1 = l/(1000*60*60*24);
        int i2 = (int) i1;
        Integer newPrice = hotelPrice*(1 - (discount/100))*i2;

        //create payment
        UUID paymentUid = restTemplate.postForObject("http://localhost:8083/payment/create/{newPrice}", newPrice, UUID.class, newPrice);

        //update loyalty
        Integer reservationCount = restTemplate.postForObject("http://localhost:8082/api/v1/addCount/{username}", username, Integer.class, username);
        String status = restTemplate.postForObject("http://localhost:8082/api/v1/updateStatus/{username}", username, String.class, username);

        //create reservation
        HotelIdResponse hotelIdResponse = jdbcTemplate.queryForObject("select id from hotels where hotelUid =:hotelUid",
                new MapSqlParameterSource().addValue("hotelUid", hotelUid),
                new BeanPropertyRowMapper<>(HotelIdResponse.class));
        Integer hotelId = hotelIdResponse.getHotelId();
        jdbcTemplate.update(
                "insert into reservation(reservationUid,username,paymentUid,hotelId,status,startDate,endDate) values (:reservationUid,:username,:paymentUid,:hotelId,:status,:startDate,:endDate)",
                new MapSqlParameterSource()
                        .addValue("reservationUid", UUID.randomUUID())
                        .addValue("username", username)
                        .addValue("paymentUid", paymentUid)
                        .addValue("hotelId", hotelId)
                        .addValue("status", "PAID")
                        .addValue("startDate", startDate)
                        .addValue("endDate", endDate));

        //response
        CreateReservationResponse crr = new CreateReservationResponse();
        crr.setHotelUid(hotelUid);
        crr.setStartDate(startDate);
        crr.setEndDate(endDate);

        return crr;

    }

    @Override
    public ReservationStatusResponse cancelReservation(String username, UUID reservationUid){
        //set reservation status
        jdbcTemplate.update(
                "update reservation set status='CANCELED' where reservationUid =:reservationUid ",
                new MapSqlParameterSource()
                        .addValue("reservationUid", reservationUid));

        //set payment status
        PaymentUidResponse paymentUidResponse = jdbcTemplate.queryForObject(
                "select paymentUid from reservation where reservationUid = :reservationUid",
                new MapSqlParameterSource()
                        .addValue("reservationUid", reservationUid),
                new BeanPropertyRowMapper<>(PaymentUidResponse.class));
        UUID paymentUid = paymentUidResponse.getPaymentUid();
        String paymentStatus = restTemplate.postForObject("http://localhost:8083/payment/cancel/{paymentUid}", paymentUid, String.class, paymentUid);

        //update loyalty
        Integer reservationCount = restTemplate.postForObject("http://localhost:8082/api/v1/reduceCount/{username}", username, Integer.class, username);
        String Status = restTemplate.postForObject("http://localhost:8082/api/v1/updateStatus/{username}", username, String.class, username);

        //response
        return jdbcTemplate.queryForObject("select status from reservation where reservationUid =:reservationUid",
                new MapSqlParameterSource()
                        .addValue("reservationUid", reservationUid),
                new BeanPropertyRowMapper<>(ReservationStatusResponse.class));
    }

    @Override
    public ReservationUidResponse getReservationUid(String username){
        return jdbcTemplate.queryForObject("select reservationUid from reservation where username =:username",
                new MapSqlParameterSource()
                        .addValue("username", username),
                new BeanPropertyRowMapper<>(ReservationUidResponse.class));
    }





}