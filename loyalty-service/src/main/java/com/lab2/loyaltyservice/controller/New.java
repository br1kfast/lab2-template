package com.lab2.loyaltyservice.controller;

import com.lab2.loyaltyservice.data.LoyaltyStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class New {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @RequestMapping("/get/{username}")
    public String get(@PathVariable("username") String username){
    jdbcTemplate.update(
            "update loyalty set status='SILVER' where username = :username",
            new MapSqlParameterSource()
                            .addValue("username", username));
    LoyaltyStatusResponse loyaltyStatusResponse = jdbcTemplate.queryForObject("select status from loyalty where username = :username",
                new MapSqlParameterSource().addValue("username", username),
                new BeanPropertyRowMapper<>(LoyaltyStatusResponse.class));
    return loyaltyStatusResponse.getStatus();

    }


}
