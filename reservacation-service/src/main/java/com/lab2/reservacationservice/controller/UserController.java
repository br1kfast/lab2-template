package com.lab2.reservacationservice.controller;

import com.lab2.reservacationservice.dao.UserDAOImpl;
import com.lab2.reservacationservice.data.ReservationData;
import com.lab2.reservacationservice.data.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    UserDAOImpl userDAO;

    @GetMapping("/api/v1/me")
    public ResponseEntity<UserData> getReservations(@RequestParam(value = "username", required = false) String username){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Name",username);
        return ResponseEntity.status(200).headers(headers).body(userDAO.getUserIfo(username));
    }


}
