package com.lab2.reservacationservice.dao;

import com.lab2.reservacationservice.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class HotelsDAOImpl implements HotelsDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<HotelsData> list(Integer page, Integer size){
        return jdbcTemplate.query("select*from hotels", new BeanPropertyRowMapper<>(HotelsData.class));
    }

    @Override
    public HotelPriceResponse getHotelPrice(UUID hotelUid){
        return jdbcTemplate.queryForObject(
                "select price from hotels where hotelUid =:hotelUid",
                new MapSqlParameterSource().addValue("hotelUid", hotelUid),
                new BeanPropertyRowMapper<>(HotelPriceResponse.class));
    }

    @Override
    public UserHotelsResponse getUserHotelsResponse(Integer hotelId){
        UserHotelsResponse userHotelsResponse = new UserHotelsResponse();

        UserHotelsResponseAdd userHotelsResponseAdd = jdbcTemplate.queryForObject(
                "select hotelUid,name,stars from hotels where id =:hotelId",
                new MapSqlParameterSource().addValue("hotelId", hotelId),
                new BeanPropertyRowMapper<>(UserHotelsResponseAdd.class));

        FullAddress fullAddress = jdbcTemplate.queryForObject(
                "select country,city,address from hotels where id =:hotelId",
                new MapSqlParameterSource().addValue("hotelId", hotelId),
                new BeanPropertyRowMapper<>(FullAddress.class));

        userHotelsResponse.setUserHotelsResponseAdd(userHotelsResponseAdd);
        userHotelsResponse.setFullAddress(fullAddress);

        return userHotelsResponse;
    }


}
