create schema if not exists catalogue;

create type catalogue.product_status as enum (
    'ACTIVE',
    'INACTIVE'
);

create table catalogue.t_product(
    id serial primary key,
    c_title varchar(50) not null check (length(trim(c_title)) >= 3),
    c_quantity integer not null,
    c_details varchar(1000),
    c_status catalogue.product_status not null
);
