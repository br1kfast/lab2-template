package com.lab2.loyaltyservice.dao;

import com.lab2.loyaltyservice.data.*;

public interface LoyaltyDAO {

    LoyaltyData getLoyaltyByUsername(String username);
    GetDiscountResponse getDiscount(String username);
    ReservationCountResponse addReservationCount(String username);
    ReservationCountResponse reduceReservationCount(String username);
    String updateStatus(String username);
    UserLoyaltyResponse getUserLoyalty(String username);
}
