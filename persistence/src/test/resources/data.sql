INSERT INTO orders(id, customer_name, status, cost)
VALUES ('123a', 'Richard Capraro', 0, 20.0);

INSERT INTO items(id,
                  product,
                  quantity,
                  size,
                  milk,
                  order_id)
VALUES (1, 'Cappuccino', 2, 1, 1, '123a');

INSERT INTO orders(id, customer_name, status, cost)
VALUES ('456c', 'John Doe', 2, 40.0);