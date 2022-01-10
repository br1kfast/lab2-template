package com.lab2.reservacationservice.controller;


import com.lab2.reservacationservice.dao.HotelsDAOImpl;
import com.lab2.reservacationservice.data.HotelsData;
import com.lab2.reservacationservice.data.ReservationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class HotelsController {
    @Autowired
    HotelsDAOImpl hotelsDAO;

    @GetMapping("/api/v1/hotels & page = {page} & size = {size}")
    public List<HotelsData> list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return hotelsDAO.list(page, size);
    }
}
