CREATE SEQUENCE seq START WITH 1 INCREMENT BY 1;


insert into city(name, is_big_city)
values (Tehran, false),
       (Isfahan, true),
       (Gilan, true),
       (Fars, true),
       (Ghom, true),
       (Khuzestan, true);