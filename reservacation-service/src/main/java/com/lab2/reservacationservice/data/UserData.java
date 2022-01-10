package com.lab2.reservacationservice.data;

public class UserData {
    UserReservationResponse userReservationResponse;
    UserLoyaltyResponse userLoyaltyResponse;

    public UserReservationResponse getUserReservationResponse() {
        return userReservationResponse;
    }

    public void setUserReservationResponse(UserReservationResponse userReservationResponse) {
        this.userReservationResponse = userReservationResponse;
    }

    public UserLoyaltyResponse getUserLoyaltyResponse() {
        return userLoyaltyResponse;
    }

    public void setUserLoyaltyResponse(UserLoyaltyResponse userLoyaltyResponse) {
        this.userLoyaltyResponse = userLoyaltyResponse;
    }
}
