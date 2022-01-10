package com.lab2.reservacationservice.dao;

import com.lab2.reservacationservice.data.*;

import java.util.List;
import java.util.UUID;

public interface HotelsDAO {

    List<HotelsData> list(Integer page, Integer size);
    HotelPriceResponse getHotelPrice(UUID hotelUid);
    UserHotelsResponse getUserHotelsResponse(Integer hotelId);

}
