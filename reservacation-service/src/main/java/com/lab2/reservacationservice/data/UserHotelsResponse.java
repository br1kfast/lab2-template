package com.lab2.reservacationservice.data;

import java.util.UUID;

public class UserHotelsResponse {

    private UserHotelsResponseAdd userHotelsResponseAdd;
    private FullAddress fullAddress;

    public UserHotelsResponseAdd getUserHotelsResponseAdd() {
        return userHotelsResponseAdd;
    }

    public void setUserHotelsResponseAdd(UserHotelsResponseAdd userHotelsResponseAdd) {
        this.userHotelsResponseAdd = userHotelsResponseAdd;
    }

    public FullAddress getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(FullAddress fullAddress) {
        this.fullAddress = fullAddress;
    }
}
