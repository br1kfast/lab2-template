package com.lab2.reservacationservice.data;

import java.util.UUID;

public class ReservationUidResponse {

    UUID reservationUid;

    public UUID getReservationUid() {
        return reservationUid;
    }

    public void setReservationUid(UUID reservationUid) {
        this.reservationUid = reservationUid;
    }
}
