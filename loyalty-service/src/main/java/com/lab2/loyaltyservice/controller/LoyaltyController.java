package com.lab2.loyaltyservice.controller;


import com.lab2.loyaltyservice.dao.LoyaltyDAOImpl;
import com.lab2.loyaltyservice.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/")
public class LoyaltyController {

    @Autowired
    LoyaltyDAOImpl loyaltyDAOImpl;

    @GetMapping("/loyalty")
    public ResponseEntity<LoyaltyData> getLoyaltyByUsername(@RequestParam(value = "username", required = false) String username){

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Name",username);
        return ResponseEntity.status(200).headers(headers).body(loyaltyDAOImpl.getLoyaltyByUsername(username));
    }

    @GetMapping("/discount/{username}")
    public Integer getDiscount(@PathVariable("username") String username){
        GetDiscountResponse getDiscountResponse = loyaltyDAOImpl.getDiscount(username);
        return getDiscountResponse.getDiscount();

    }

    @PostMapping("/addCount/{username}")
    public Integer addReservationCount(@PathVariable("username") String username){
        ReservationCountResponse reservationCountResponse1 = loyaltyDAOImpl.addReservationCount(username);
        return reservationCountResponse1.getReservationCount();

    }

    @PostMapping("/reduceCount/{username}")
    public Integer reduceReservationCount(@PathVariable("username") String username){
        ReservationCountResponse reservationCountResponse2 = loyaltyDAOImpl.reduceReservationCount(username);
        return reservationCountResponse2.getReservationCount();
    }

    @PostMapping("/updateStatus/{username}")
    public String updateStatus(@PathVariable("username") String username){
        return loyaltyDAOImpl.updateStatus(username);

    }

    @GetMapping("/getUserLoyalty/{username}")
    public UserLoyaltyResponse getUserLoyalty(@PathVariable("username") String username){
        return loyaltyDAOImpl.getUserLoyalty(username);
    }


}
