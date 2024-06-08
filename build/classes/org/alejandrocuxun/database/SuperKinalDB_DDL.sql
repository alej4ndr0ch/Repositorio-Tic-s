drop database if exists superkinadb;

create database superkinadb;

Use superkinadb;
-- ********************************** TABLA ********************************** --
create table Clientes(
    clienteId int not null auto_increment,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    telefono varchar(80) not null,
    direccion varchar(50) not null,
    nit varchar(15)not null,
    primary key pk_clienteId(clienteId)
);
 
create table Cargos(
    cargoId int not null auto_increment,
    nombreCargo varchar(30) not null,
    descripcionCargo varchar(100) not null,
    primary key pk_cargoId(cargoId)
);
 
create table Empleados(
    empleadoId int not null auto_increment,
    nombreEmpleado varchar(30) not null,
    apellidoEmpleado varchar(30) not null,
    horaEntrada time,
    horaSalida time,
    cargoId int not null,
    encargadoId int,
    sueldo decimal(10.2) not null,
    primary key pk_empleadoId(empleadoId),
    constraint FK_encargadoId_Empleados foreign key (encargadoId)
		references Empleados(empleadoId),
    constraint FK_cargoId_Empleados foreign key (cargoId)
		references Cargos(cargoId)
);

create table Distribuidores(
    distribuidorId int not null,
    nombreDistribuidor varchar(30) not null,
    direccionDistribuidor varchar(200),
    nitDistribuidor varchar(15) not null,
    telefonoDistribuidor varchar(15) not null,
    web varchar(50),
    primary key pk_distribuidorId(distribuidorId)
);

create table categoriaProductos(
    categoriaProductoId int not null auto_increment,
    nombreCategoria varchar(30) not null,
    descripcionCategoria varchar(100) not null,
    primary key pk_categoriaProductoId(categoriaProductoId)
);

create table productos(
    productoId int not null auto_increment,
    nombreProducto varchar(50) not null,
    descripcionProducto varchar(100),
    cantidadProducto int not null,
    precioVentaUnitaria decimal not null,
    precioVentaMayor decimal not null,
    precioCompra decimal not null,
    imagenProducto blob,
    distribuidorId int not null,
    categoriaProductoId int not null,
    primary key pk_productoId(productoId),
    constraint pk_Productos_Distribuidores foreign key(distribuidorId)
		references Distribuidores(distribuidorId),
    constraint pk_Productos_categoriaProductos foreign key(categoriaProductoId)
        references categoriaProductos(categoriaProductoId)
);

create table Facturas(
    facturaId int not null auto_increment,
    fecha date not null,
    hora time not null,
    clienteId int not null,
    empleadoId int not null,
    total decimal,
    primary key pk_facturaId(facturaId),
    constraint fk_Facturas_Clientes foreign key(clienteId)
	references Clientes(clienteId),
    constraint fk_Facturas_Empleados foreign key(empleadoId)
	references Empleados(empleadoId)
);

create table DetalleFaturas(
    detalleFacturaId int not null auto_increment,
    facturaId int not null,
    productoId int not null,
    primary key pk_detalleFacturaId(detalleFacturaId),
    constraint fk_DetalleFactura_Facturas foreign key(facturaId)
		references Facturas(facturaId),
    constraint fk_DetalleFacturas_Productos foreign key(productoId)
		references Productos(productoId)
);

create table TicketSoporte(
    ticketSoporteId int not null auto_increment,
    descripcionTicket varchar(250) not null,
    estatus varchar(30) not null,
    clienteId int not null,
    facturaId int,
    primary key pk_ticketSoporteId(ticketSoporteId),
    constraint fk_TicketSoporte_Clientes foreign key(clienteId)
		references Clientes(clienteId),
    constraint fk_TicketSoporte_Facturas foreign key(facturaId)
		references Facturas(facturaId)
);

create table Promociones(
    promocionId int not null auto_increment,
    precioPromocion decimal(10.2),
    descripcionPromocion varchar(100),
    fechaInicio Date not null,
    fechaFinalizacion Date not null,
    productoId int not null,
    primary key pk_promocionId(promocionId),
    constraint fk_Promociones_Productos foreign key (productoId)
		references Productos(productoId)
);

create table Compras(
    compraId int not null auto_increment,
    fechaCompra date not null,
    totalCompra decimal,
    primary key pk_compraId(compraId) 
);

create table detalleCompras(
    detalleCompraId int not null auto_increment,
    cantidadCompra int not null,
    productoId int not null,
    compraId int not null,
    primary key pk_detalleCompraId(detalleCompraId),
    constraint fk_detalleCompras_Productos foreign key(productoId)
        references Productos(productoId),
    constraint fk_detalleCompras_Compras foreign key(compraId)
		references Compras(compraId)
);

CREATE TABLE NivelesAcceso (
    nivelAccesoId INT NOT NULL AUTO_INCREMENT,
    nivelAcceso VARCHAR(40) NOT NULL,
    PRIMARY KEY (nivelAccesoId)
);

CREATE TABLE Usuarios (
    usuarioId INT NOT NULL AUTO_INCREMENT,
    usuario VARCHAR(30) NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    nivelAccesoId INT NOT NULL,
    empleadoId INT NOT NULL,
    PRIMARY KEY (usuarioId),
    CONSTRAINT FK_Usuarios_NivelesAcceso FOREIGN KEY (nivelAccesoId)
        REFERENCES NivelesAcceso(nivelAccesoId),
    CONSTRAINT FK_Usuarios_Empleados FOREIGN KEY (empleadoId)
        REFERENCES Empleados(empleadoId)
);

INSERT INTO NivelesAcceso(nivelAcceso)VALUES 
('Admin'),
('User');

set global time_zone = '-6:00';