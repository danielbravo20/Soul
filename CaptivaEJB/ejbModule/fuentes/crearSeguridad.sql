-- ESQUEMA -------------------------------------------------

DROP SCHEMA seguridad CASCADE
;
CREATE SCHEMA seguridad AUTHORIZATION postgres
;

-- TABLAS -------------------------------------------------

-- Table seguridad.modulo

CREATE TABLE seguridad.modulo(
 codigo_modulo Integer NOT NULL,
 nombre Character varying(60) NOT NULL,
 orden Integer NOT NULL,
 descripcion Character varying(250),
 url Character varying(120) NOT NULL
)
;

-- Add keys for table seguridad.modulo

ALTER TABLE seguridad.modulo ADD CONSTRAINT modulo_pk PRIMARY KEY (codigo_modulo)
;

-- Table seguridad.modulo_rol

CREATE TABLE seguridad.modulo_rol(
 codigo_rol Character varying(120) NOT NULL,
 codigo_modulo Integer NOT NULL
)
;

-- Add keys for table seguridad.modulo_rol

ALTER TABLE seguridad.modulo_rol ADD CONSTRAINT modulo_rol_pk PRIMARY KEY (codigo_rol,codigo_modulo)
;

-- Table seguridad.rol

CREATE TABLE seguridad.rol(
 codigo_rol Character varying(120) NOT NULL,
 nombre_rol Character varying(120) NOT NULL
)
;

-- Add keys for table seguridad.rol

ALTER TABLE seguridad.rol ADD CONSTRAINT rol_pk PRIMARY KEY (codigo_rol)
;

-- Table seguridad.usuario

CREATE TABLE seguridad.usuario(
 usuario Character varying(40) NOT NULL,
 estado Integer NOT NULL,
 clave Character varying(70) NOT NULL,
 nombre_completo Character varying(120) NOT NULL,
 correo Character varying(120)
)
;

-- Add keys for table seguridad.usuario

ALTER TABLE seguridad.usuario ADD CONSTRAINT usuario_pk PRIMARY KEY (usuario)
;

-- Table seguridad.usuario_rol

CREATE TABLE seguridad.usuario_rol(
 usuario Character varying(40) NOT NULL,
 codigo_rol Character varying(120) NOT NULL
)
;

-- Add keys for table seguridad.usuario_rol

ALTER TABLE seguridad.usuario_rol ADD CONSTRAINT usuario_rol_pk PRIMARY KEY (usuario,codigo_rol)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE seguridad.modulo_rol ADD CONSTRAINT mod_rol_modulo FOREIGN KEY (codigo_modulo) REFERENCES seguridad.modulo (codigo_modulo) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE seguridad.modulo_rol ADD CONSTRAINT mod_rol_rol FOREIGN KEY (codigo_rol) REFERENCES seguridad.rol (codigo_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE seguridad.usuario_rol ADD CONSTRAINT usuario_rol_rol FOREIGN KEY (codigo_rol) REFERENCES seguridad.rol (codigo_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE seguridad.usuario_rol ADD CONSTRAINT usuario_rol_usuario FOREIGN KEY (usuario) REFERENCES seguridad.usuario (usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
;
