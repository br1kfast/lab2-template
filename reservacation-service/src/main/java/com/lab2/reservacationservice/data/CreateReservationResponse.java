package com.lab2.reservacationservice.data;

import java.sql.Date;
import java.util.UUID;

public class CreateReservationResponse {
    private UUID hotelUid;
    private Date startDate;
    private Date endDate;

    public CreateReservationResponse(UUID hotelUid, Date startDate, Date endDate) {
        this.hotelUid = hotelUid;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public CreateReservationResponse() {

    }


    public UUID getHotelUid() {
        return hotelUid;
    }

    public void setHotelUid(UUID hotelUid) {
        this.hotelUid = hotelUid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
