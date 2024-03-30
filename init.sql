CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public.product
(
    article character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    category character varying(255) COLLATE pg_catalog."default",
    price numeric(10,2),
    quantity integer,
    id uuid NOT NULL,
    last_quantity_change_timestamp timestamp without time zone,
    creation_timestamp timestamp without time zone,
    CONSTRAINT pk_product_id PRIMARY KEY (id),
    CONSTRAINT product_article_key UNIQUE (article)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product
    OWNER to postgres;
INSERT INTO public.product(id, article, name, description, category, price, quantity, last_quantity_change_timestamp, creation_timestamp)
VALUES 
    (uuid_generate_v4(), 'ART001', 'Product 1', 'Description for Product 1', 'Category 1', 10.99, 100, NOW(), NOW()),
    (uuid_generate_v4(), 'ART002', 'Product 2', 'Description for Product 2', 'Category 2', 15.99, 50, NOW(), NOW()),
    (uuid_generate_v4(), 'ART003', 'Product 3', 'Description for Product 3', 'Category 1', 20.49, 75, NOW(), NOW()),
    (uuid_generate_v4(), 'ART004', 'Product 4', 'Description for Product 4', 'Category 2', 8.99, 200, NOW(), NOW()),
    (uuid_generate_v4(), 'ART005', 'Product 5', 'Description for Product 5', 'Category 3', 12.79, 150, NOW(), NOW());

