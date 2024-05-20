CREATE TABLE especialidad (
	id         int NOT NULL,
	nombre     varchar(255) NOT NULL,
	CONSTRAINT especialidad_pkey PRIMARY KEY (id)
);

CREATE TABLE usuario (
	id          varchar(255) NOT NULL,
	email       varchar(100) NOT NULL,
	medico_id   varchar(36) NULL,
	rol         varchar(20) NOT NULL,
	CONSTRAINT uk_usuario_email UNIQUE (email),
	CONSTRAINT usuario_pkey PRIMARY KEY (id),
	CONSTRAINT usuario_rol_check CHECK (((rol)::text = ANY ((ARRAY['ADMIN'::character varying, 'RECEPCIONISTA'::character varying, 'MEDICO'::character varying])::text[])))
);

CREATE TABLE medico (
	id              varchar(255) NOT NULL,
	apellidos       varchar(255) NOT NULL,
	direccion       varchar(255) NOT NULL,
	documento       varchar(255) NOT NULL,
	email           varchar(255) NOT NULL,
	especialidad    varchar(255) NOT NULL,
	nombres         varchar(255) NOT NULL,
	telefono        varchar(255) NOT NULL,
	tipo_documento  varchar(255) NOT NULL,
	CONSTRAINT medico_pkey PRIMARY KEY (id),
	CONSTRAINT medico_tipo_documento_check CHECK (((tipo_documento)::text = ANY ((ARRAY['TI'::character varying, 'NUIP'::character varying, 'RC'::character varying, 'CC'::character varying, 'CE'::character varying, 'PP'::character varying])::text[])))
);


CREATE TABLE paciente (
	id              varchar(255) NOT NULL,
	apellidos       varchar(255) NOT NULL,
	ciudad          varchar(255) NULL,
	direccion       varchar(255) NULL,
	documento       varchar(20) NOT NULL,
	email           varchar(255) NULL,
	fecha_nacimiento date NULL,
	nombres         varchar(255) NOT NULL,
	sexo            varchar(255) NULL,
	telefono        varchar(255) NOT NULL,
	tipo_documento  varchar(5) NOT NULL,
	CONSTRAINT paciente_pkey PRIMARY KEY (id),
	CONSTRAINT paciente_sexo_check CHECK (((sexo)::text = ANY ((ARRAY['M'::character varying, 'F'::character varying])::text[]))),
	CONSTRAINT paciente_tipo_documento_check CHECK (((tipo_documento)::text = ANY ((ARRAY['TI'::character varying, 'NUIP'::character varying, 'RC'::character varying, 'CC'::character varying, 'CE'::character varying, 'PP'::character varying])::text[])))
);

CREATE TABLE cita (
	id              varchar(36) NOT NULL,
	diagnostico     varchar(2000) NULL,
	estado          varchar(20) NOT NULL,
	fecha_atencion  timestamp(6) NULL,
	fecha_creacion  timestamp(6) NOT NULL,
	fecha_hora      timestamp(6) NOT NULL,
	medico_id       varchar(36) NOT NULL,
	motivo          varchar(1000) NULL,
	paciente_id     varchar(36) NOT NULL,
	CONSTRAINT cita_estado_check CHECK (((estado)::text = ANY ((ARRAY['PROGRAMADA'::character varying, 'ATENDIDA'::character varying, 'ANULADA'::character varying, 'INCUMPLIDA'::character varying])::text[]))),
	CONSTRAINT cita_pkey PRIMARY KEY (id)
);