DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS
(
    ID            varchar(255),
    CUSTOMER_NAME varchar(255),
    STATUS        int,
    COST          decimal(10, 2)
);

DROP TABLE IF EXISTS ITEMS;
CREATE TABLE ITEMS
(
    ID       varchar(255),
    PRODUCT  varchar(255),
    QUANTITY int,
    SIZE     int,
    MILK     int,
    ORDER_ID varchar(255) REFERENCES ORDERS (ID)
);