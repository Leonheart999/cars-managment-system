CREATE SEQUENCE cars_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE cars
(
    id           BIGINT NOT NULL DEFAULT nextval('cars_id_seq') PRIMARY KEY,
    production_year INTEGER,
    price        DECIMAL,
    color        VARCHAR(255),
    model        VARCHAR(255),
    brand        VARCHAR(255),
    active       BOOLEAN
);