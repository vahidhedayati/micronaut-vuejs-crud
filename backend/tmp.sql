create sequence hibernate_sequence start with 1 increment by 1;

    create table hotel (
       id bigint not null,
        code varchar(255),
        email varchar(255),
        lastUpdated timestamp,
        name varchar(255),
        phone varchar(255),
        update_user_id bigint,
        primary key (id)
    );

    create table hotel_rooms (
       type varchar(31) not null,
        id bigint not null,
        price decimal(19,2),
        room_type varchar(255),
        stock_total bigint,
        hotel_id bigint,
        primary key (id)
    );

    create table user (
       id bigint not null,
        firstname varchar(255),
        lastUpdated timestamp,
        password varchar(255),
        surname varchar(255),
        username varchar(255),
        primary key (id)
    );

    alter table hotel_rooms 
       add constraint FKrxmhxdnj4fjesel7s5aw9yx0e 
       foreign key (hotel_id) 
       references hotel;
