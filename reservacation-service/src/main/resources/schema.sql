CREATE TABLE hotels
(
    id        SERIAL PRIMARY KEY,
    hotelUid uuid         NOT NULL UNIQUE,
    name      VARCHAR(255) NOT NULL,
    country   VARCHAR(80)  NOT NULL,
    city      VARCHAR(80)  NOT NULL,
    address   VARCHAR(255) NOT NULL,
    stars     INT,
    price     INT          NOT NULL
);

CREATE TABLE reservation
(
    id              SERIAL PRIMARY KEY,
    reservationUid uuid UNIQUE NOT NULL,
    username        VARCHAR(80) NOT NULL,
    paymentUid     uuid        NOT NULL,
    hotelId        INT REFERENCES hotels (id),
    status          VARCHAR(20) NOT NULL
        CHECK (status IN ('PAID', 'CANCELED')),
    startDate      TIMESTAMP WITH TIME ZONE,
    endDate        TIMESTAMP WITH TIME ZONE
);