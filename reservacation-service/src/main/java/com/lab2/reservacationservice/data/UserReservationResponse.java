package com.lab2.reservacationservice.data;


public class UserReservationResponse {
    UserReservationResponseAdd userReservationResponseAdd;
    UserHotelsResponse userHotelsResponse;
    UserPaymentResponse userPaymentResponse;


    public UserReservationResponseAdd getUserReservationResponseAdd() {
        return userReservationResponseAdd;
    }

    public void setUserReservationResponseAdd(UserReservationResponseAdd userReservationResponseAdd) {
        this.userReservationResponseAdd = userReservationResponseAdd;
    }

    public UserHotelsResponse getUserHotelsResponse() {
        return userHotelsResponse;
    }

    public void setUserHotelsResponse(UserHotelsResponse userHotelsResponse) {
        this.userHotelsResponse = userHotelsResponse;
    }

    public UserPaymentResponse getUserPaymentResponse() {
        return userPaymentResponse;
    }

    public void setUserPaymentResponse(UserPaymentResponse userPaymentResponse) {
        this.userPaymentResponse = userPaymentResponse;
    }
}
