package com.lab2.reservacationservice.dao;

import com.lab2.reservacationservice.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public UserData getUserIfo(String username){

        //get hotel response
        HotelIdResponse hotelIdResponse = jdbcTemplate.queryForObject(
                "select hotelId from reservation where username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username),
                new BeanPropertyRowMapper<>(HotelIdResponse.class));
        Integer hotelId = hotelIdResponse.getHotelId();
        HotelsDAOImpl hotelsDAO1 = new HotelsDAOImpl();
        UserHotelsResponse userHotelsResponse = hotelsDAO1.getUserHotelsResponse(hotelId);

        //get payment response
        PaymentUidResponse paymentUidResponse = jdbcTemplate.queryForObject(
                "select paymentUid from reservation where username =:username",
                new MapSqlParameterSource()
                        .addValue("username", username),
                new BeanPropertyRowMapper<>(PaymentUidResponse.class));
        UUID paymentUid = paymentUidResponse.getPaymentUid();
        UserPaymentResponse userPaymentResponse = restTemplate.getForObject("http://localhost:8083/payment/getUserPaymentResponse/" + paymentUid, UserPaymentResponse.class);

        //get reservation response
        UserReservationResponse userReservationResponse = new UserReservationResponse();
        UserReservationResponseAdd userReservationResponseAdd = jdbcTemplate.queryForObject(
                "select reservationUid,startDate,endDate,status from reservation where username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username),
                new BeanPropertyRowMapper<>(UserReservationResponseAdd.class));
        userReservationResponse.setUserReservationResponseAdd(userReservationResponseAdd);
        userReservationResponse.setUserHotelsResponse(userHotelsResponse);
        userReservationResponse.setUserPaymentResponse(userPaymentResponse);

        //get loyalty response
        UserLoyaltyResponse userLoyaltyResponse = restTemplate.getForObject("http://localhost:8082/api/v1/getUserLoyalty/" + username, UserLoyaltyResponse.class);

        //get response
        UserData userData = new UserData();
        userData.setUserLoyaltyResponse(userLoyaltyResponse);
        userData.setUserReservationResponse(userReservationResponse);
        return userData;
    }

}
