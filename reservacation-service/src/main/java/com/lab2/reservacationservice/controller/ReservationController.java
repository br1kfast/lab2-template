package com.lab2.reservacationservice.controller;


import com.lab2.reservacationservice.data.*;
import com.lab2.reservacationservice.dao.ReservationDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    @Autowired
    ReservationDAOImpl reservationDAOImpl;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationData>> getReservations(@RequestParam(value = "username", required = false) String username){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Name",username);
        return ResponseEntity.status(200).headers(headers).body(reservationDAOImpl.getReservations(username));

    }

    @GetMapping("/{reservationUid}")
    public ResponseEntity<ReservationData> getReservationById(@PathVariable("reservationUid") UUID reservationUid,@RequestParam(value = "username", required = false) String username){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Name",username);
        return ResponseEntity.status(200).headers(headers).body(reservationDAOImpl.getReservationById(username,reservationUid));
    }

    @PostMapping("/reservations")
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "hotelUid", required = false) UUID hotelUid, @RequestParam(value = "startDate", required = false) Date startDate, @RequestParam(value = "endDate", required = false) Date endDate){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Name",username);
        return ResponseEntity.status(201).headers(headers).body(reservationDAOImpl.createReservation(username,hotelUid,startDate,endDate));
    }

    @DeleteMapping("/{reservationUid}")
    public ResponseEntity<ReservationStatusResponse> cancelReservation(@PathVariable("reservationUid") UUID reservationUid, @RequestParam(value = "username", required = false) String username){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Name",username);
        return ResponseEntity.status(200).headers(headers).body(reservationDAOImpl.cancelReservation(username,reservationUid));
    }

    @GetMapping("/reservationUid/{username}")
    public UUID getReservationUid(@PathVariable("username") String username){
        ReservationUidResponse reservationUidResponse = reservationDAOImpl.getReservationUid(username);
        UUID uuid = reservationUidResponse.getReservationUid();
        return uuid;
    }




}
