DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id            varchar(255),
    customer_name varchar(255),
    status        int,
    cost          decimal(10, 2)
);

DROP TABLE IF EXISTS items;
CREATE TABLE items
(
    id       varchar(255),
    product  varchar(255),
    quantity int,
    size     int,
    milk     int,
    order_id varchar(255),
    foreign key (order_id) references orders (id)
);