package com.lab2.loyaltyservice.dao;


import com.lab2.loyaltyservice.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoyaltyDAOImpl implements LoyaltyDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public LoyaltyData getLoyaltyByUsername(String username){
        return jdbcTemplate.queryForObject(
                "select*from loyalty where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                new BeanPropertyRowMapper<>(LoyaltyData.class));
    }

    @Override
    public GetDiscountResponse getDiscount(String username) {

        return jdbcTemplate.queryForObject(
                "select discount from loyalty where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                new BeanPropertyRowMapper<>(GetDiscountResponse.class));


    }

    @Override
    public ReservationCountResponse addReservationCount(String username){

        jdbcTemplate.update(
                "update loyalty set reservationCount=reservationCount+1 where username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username));

        return jdbcTemplate.queryForObject("select reservationCount from loyalty where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                new BeanPropertyRowMapper<>(ReservationCountResponse.class));

    }

    @Override
    public ReservationCountResponse reduceReservationCount(String username){
        jdbcTemplate.update(
                "update loyalty set reservationCount=reservationCount-1 where username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username));

        return jdbcTemplate.queryForObject("select reservationCount from loyalty where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                new BeanPropertyRowMapper<>(ReservationCountResponse.class));

    }

    @Override
    public String updateStatus(String username) {
        ReservationCountResponse count = jdbcTemplate.queryForObject("select reservationCount from loyalty where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                new BeanPropertyRowMapper<>(ReservationCountResponse.class));
        Integer count1 = count.getReservationCount();
        if (count1 >= 20) {
            jdbcTemplate.update(
                    "update loyalty set status='GOLD' where username = :username",
                    new MapSqlParameterSource()
                            .addValue("username", username));
        } else if (count1 >= 10) {
            jdbcTemplate.update(
                    "update loyalty set status='SILVER' where username = :username",
                    new MapSqlParameterSource()
                            .addValue("username", username));
        } else {
            jdbcTemplate.update(
                    "update loyalty set status='BRONZE' where username = :username",
                    new MapSqlParameterSource()
                            .addValue("username", username));
        }
         LoyaltyStatusResponse loyaltyStatusResponse = jdbcTemplate.queryForObject("select status from loyalty where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                new BeanPropertyRowMapper<>(LoyaltyStatusResponse.class));

         return loyaltyStatusResponse.getStatus();
    }

    @Override
    public UserLoyaltyResponse getUserLoyalty(String username){
        return jdbcTemplate.queryForObject(
                "select status,discount from loyalty where username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username),
                new BeanPropertyRowMapper<>(UserLoyaltyResponse.class));
    }



}
