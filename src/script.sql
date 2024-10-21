create sequence cictema.public.usuarios_id_seq start with 1 increment by 1;

create table cictema.public.usuarios(
                                        id int not null default nextval('cictema.public.usuarios_id_seq'),
                                        nombres varchar(30) not null,
                                        apellidos varchar(50) not null,
                                        telefono varchar(10) not null,
                                        email varchar(50),
                                        username varchar(10) not null,
                                        password varchar(100) not null, -- default md5(random()::text || clock_timestamp()::text)::varchar(10),
                                        porcentaje_ganancia real not null,
                                        fecha_creacion timestamp default current_timestamp,
                                        fecha_modificacion timestamp,
                                        deleted boolean default false,
                                        unique (username),
                                        unique (email),
                                        primary key (id)
);

create sequence cictema.public.clientes_id_seq start with 1 increment by 1;

create table cictema.public.clientes(
                                        id int not null default nextval('cictema.public.clientes_id_seq'),
                                        nombres varchar(30) not null,
                                        apellidos varchar(50) not null,
                                        telefono varchar(10) not null,
                                        deleted boolean default false,
                                        primary key (id)
);

create sequence cictema.public.servicios_id_seq start with 1 increment by 1;

create table cictema.public.servicios(
                                         id int not null default nextval('cictema.public.servicios_id_seq'),
                                         nombre varchar(30) not null,
                                         descripcion varchar(50),
                                         costo int not null,
                                         primary key (id)
);

create sequence cictema.public.facturas_id_seq start with 1 increment by 1;

create table cictema.public.facturas(
                                        id int not null default nextval('cictema.public.facturas_id_seq'),
                                        fecha timestamp default current_timestamp,
                                        total int,
                                        ganancia_empleado real,
                                        id_empleado int not null,
                                        id_cliente int not null,
                                        primary key (id),
                                        foreign key (id_empleado) references cictema.public.usuarios(id),
                                        foreign key (id_cliente) references cictema.public.clientes(id)
);


create sequence  cictema.public.detalle_facturas_id_seq start with 1 increment by 1;

create table cictema.public.detalle_facturas(
                                                id int not null default nextval('cictema.public.detalle_facturas_id_seq'),
                                                costo int not null,
                                                cantidad int not null,
                                                subtotal int not null,
                                                id_factura int not null,
                                                id_servicio int not null,
                                                primary key (id),
                                                foreign key (id_factura) references cictema.public.facturas(id),
                                                foreign key (id_servicio) references cictema.public.servicios(id)
);
