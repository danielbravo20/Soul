DROP SCHEMA proceso CASCADE;

-- Create schemas section -------------------------------------------------

CREATE SCHEMA proceso AUTHORIZATION postgres
;

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE proceso.seq_codigo_proceso
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE proceso.seq_codigo_tarea
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

-- Create tables section -------------------------------------------------

-- Table proceso.administrador_proceso

CREATE TABLE proceso.administrador_proceso(
 codigo_proceso_plantilla Bigint NOT NULL,
 codigo_rol Character varying(120) NOT NULL
)
;

-- Add keys for table proceso.administrador_proceso

ALTER TABLE proceso.administrador_proceso ADD CONSTRAINT administrador_proceso_pk PRIMARY KEY (codigo_proceso_plantilla,codigo_rol)
;

-- Table proceso.administrador_tarea

CREATE TABLE proceso.administrador_tarea(
 codigo_tarea_plantilla Bigint NOT NULL,
 codigo_rol Character varying(120) NOT NULL
)
;

-- Add keys for table proceso.administrador_tarea

ALTER TABLE proceso.administrador_tarea ADD CONSTRAINT administrador_tarea_pk PRIMARY KEY (codigo_tarea_plantilla,codigo_rol)
;

-- Table proceso.editor_tarea

CREATE TABLE proceso.editor_tarea(
 codigo_tarea_plantilla Bigint NOT NULL,
 codigo_rol Character varying(120) NOT NULL
)
;

-- Add keys for table proceso.editor_tarea

ALTER TABLE proceso.editor_tarea ADD CONSTRAINT editor_tarea_pk PRIMARY KEY (codigo_tarea_plantilla,codigo_rol)
;

-- Table proceso.potencial_dueno

CREATE TABLE proceso.potencial_dueno(
 codigo_tarea_plantilla Bigint NOT NULL,
 codigo_rol Character varying(120) NOT NULL
)
;

-- Add keys for table proceso.potencial_dueno

ALTER TABLE proceso.potencial_dueno ADD CONSTRAINT potencial_dueno_pk PRIMARY KEY (codigo_tarea_plantilla,codigo_rol)
;

-- Table proceso.potencial_iniciador

CREATE TABLE proceso.potencial_iniciador(
 codigo_proceso_plantilla Bigint NOT NULL,
 rol_codigo_rol Character varying(120) NOT NULL
)
;

-- Add keys for table proceso.potencial_iniciador

ALTER TABLE proceso.potencial_iniciador ADD CONSTRAINT potencial_iniciador_pk PRIMARY KEY (codigo_proceso_plantilla,rol_codigo_rol)
;

-- Table proceso.proceso

CREATE TABLE proceso.proceso(
 codigo_proceso Bigint NOT NULL,
 estado_proceso Integer NOT NULL,
 nombre_proceso Character varying(120) NOT NULL,
 aleas_proceso Character varying(100) NOT NULL,
 version_proceso Character varying(12) NOT NULL,
 fecha_creacion_proceso Timestamp NOT NULL,
 fecha_termino_proceso Timestamp,
 usuario_creacion_proceso Character varying(40) NOT NULL,
 codigo_proceso_plantilla Bigint NOT NULL
)
;

-- Add keys for table proceso.proceso

ALTER TABLE proceso.proceso ADD CONSTRAINT proceso_pk PRIMARY KEY (codigo_proceso)
;

-- Table proceso.proceso_plantilla

CREATE TABLE proceso.proceso_plantilla(
 codigo_proceso_plantilla Bigint NOT NULL,
 estado_proceso Character(1) NOT NULL,
 nombre_proceso Character varying(120) NOT NULL,
 aleas_proceso Character varying(100) NOT NULL,
 version_proceso Character varying(12) NOT NULL,
 fecha_validez_proceso Timestamp NOT NULL,
 flag_todos_inician Boolean NOT NULL
)
;

-- Add keys for table proceso.proceso_plantilla

ALTER TABLE proceso.proceso_plantilla ADD CONSTRAINT proceso_plantilla_pk PRIMARY KEY (codigo_proceso_plantilla)
;

-- Table proceso.tarea

CREATE TABLE proceso.tarea(
 codigo_tarea Bigint NOT NULL,
 codigo_proceso Bigint NOT NULL,
 estado_tarea Integer NOT NULL,
 nombre_tarea Character varying(120) NOT NULL,
 aleas_tarea Character varying(100) NOT NULL,
 version_tarea Character varying(12) NOT NULL,
 prioridad_tarea Integer NOT NULL,
 fecha_creacion_tarea Timestamp NOT NULL,
 fecha_reclamo_tarea Timestamp,
 fecha_termino_tarea Timestamp,
 fecha_ultima_modificacion_tarea Timestamp NOT NULL,
 dueno_tarea Character varying(40),
 codigo_tarea_plantilla Bigint NOT NULL
)
;

-- Add keys for table proceso.tarea

ALTER TABLE proceso.tarea ADD CONSTRAINT tarea_pk PRIMARY KEY (codigo_tarea)
;

-- Table proceso.tarea_plantilla

CREATE TABLE proceso.tarea_plantilla(
 codigo_tarea_plantilla Bigint NOT NULL,
 codigo_proceso_plantilla Bigint NOT NULL,
 estado_tarea Integer NOT NULL,
 nombre_tarea Character varying(120) NOT NULL,
 aleas_tarea Character varying(100) NOT NULL,
 version_tarea Character varying(12) NOT NULL,
 prioridad_tarea Integer NOT NULL,
 orden_tarea Integer NOT NULL
)
;

-- Add keys for table proceso.tarea_plantilla

ALTER TABLE proceso.tarea_plantilla ADD CONSTRAINT tarea_plantilla_pk PRIMARY KEY (codigo_tarea_plantilla)
;

-- Create views section -------------------------------------------------

CREATE VIEW proceso.tarea_potencial_dueno
 AS
SELECT ta.codigo_tarea,
    ta.codigo_proceso,
    ta.codigo_tarea_plantilla,
    ta.estado_tarea,
    ta.nombre_tarea,
    ta.aleas_tarea,
    ta.version_tarea,
    ta.prioridad_tarea,
    ta.fecha_creacion_tarea,
    ta.fecha_reclamo_tarea,
    ta.fecha_termino_tarea,
    ta.fecha_ultima_modificacion_tarea,
    ta.dueno_tarea,
    pr.codigo_proceso_plantilla,
    pr.estado_proceso,
    pr.nombre_proceso,
    pr.aleas_proceso,
    pr.version_proceso,
    pr.fecha_creacion_proceso,
    pr.fecha_termino_proceso,
    pr.usuario_creacion_proceso,
    ur.usuario as dueno_potencial
   FROM proceso.tarea ta,
    proceso.proceso pr,
    proceso.potencial_dueno pd,
    seguridad.usuario_rol ur
  WHERE (((((ta.codigo_tarea_plantilla = pd.codigo_tarea_plantilla) AND (ta.codigo_proceso = pr.codigo_proceso)) AND (pd.codigo_rol = ur.codigo_rol)) AND (ta.estado_tarea <> 3)) AND (pr.estado_proceso = 1))
  ORDER BY ta.fecha_creacion_tarea
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
