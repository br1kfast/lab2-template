CREATE TABLE payment
(
    id          SERIAL PRIMARY KEY,
    paymentUid uuid        NOT NULL,
    status      VARCHAR(20) NOT NULL
        CHECK (status IN ('PAID', 'CANCELED')),
    price       INT         NOT NULL
);