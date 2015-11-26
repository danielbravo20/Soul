/*
Created: 25/10/2015
Modified: 25/10/2015
Model: RE PostgreSQL 9.4
Database: PostgreSQL 9.4
*/
DROP SCHEMA soul CASCADE;

CREATE SCHEMA soul AUTHORIZATION postgres
;

-- Create tables section -------------------------------------------------

-- Table soul.atributo

CREATE TABLE soul.atributo(
 cod_atributo Integer NOT NULL,
 cod_clase Integer NOT NULL,
 nombre Character varying(120) NOT NULL,
 etiqueta Character varying(255) NOT NULL,
 tipo Character varying(255) NOT NULL,
 flg_lista Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_nombre Character varying(120),
 web_formato Character varying(120)
)
;

-- Add keys for table soul.atributo

ALTER TABLE soul.atributo ADD CONSTRAINT objeto_pk PRIMARY KEY (cod_atributo)
;

-- Table soul.atributo_sql

CREATE TABLE soul.atributo_sql(
 cod_tabla Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 campo Character varying(50) NOT NULL,
 tipo Character varying(50) NOT NULL,
 longitud Integer,
 precision Integer,
 pk Character(1) DEFAULT '0'::bpchar NOT NULL,
 obligatorio Character(1) DEFAULT '0'::bpchar NOT NULL,
 fk_tabla Integer,
 fk_uno_mucho Character(1),
 fn_bus_nombre Character varying(120),
 fn_bus_catalogo Character varying(50),
 val_defecto Character varying(120),
 fk_campo Integer,
 sequencial Character varying(120),
 flg_mantenimiento_listar Character(1) DEFAULT '0'::bpchar NOT NULL,
 flg_mantenimiento_filtrar Character(1) DEFAULT '0'::bpchar NOT NULL
)
;

-- Add keys for table soul.atributo_sql

ALTER TABLE soul.atributo_sql ADD CONSTRAINT obj_sql_pk PRIMARY KEY (cod_atributo)
;

-- Table soul.catalogo

CREATE TABLE soul.catalogo(
 cod_catalogo Character varying(50) NOT NULL,
 cod_atributo Character varying(50) NOT NULL,
 valor_1 Character varying(100),
 valor_2 Character varying(100),
 descripcion Character varying(100),
 lim_cod_atributo Integer,
 lim_valor_1 Integer,
 lim_valor_2 Integer,
 cabecera Character(1) NOT NULL,
 cod_proyecto Integer
)
;

-- Create indexes for table soul.catalogo

CREATE INDEX ix_relationship29 ON soul.catalogo (cod_proyecto)
;

-- Add keys for table soul.catalogo

ALTER TABLE soul.catalogo ADD CONSTRAINT catalogo_pk PRIMARY KEY (cod_catalogo,cod_atributo)
;

-- Table soul.clase

CREATE TABLE soul.clase(
 cod_clase Integer NOT NULL,
 nombre Character varying(120) NOT NULL,
 cod_proyecto Integer NOT NULL,
 nivel Integer,
 cod_tabla Integer NULL
)
;

-- Add keys for table soul.clase

ALTER TABLE soul.clase ADD CONSTRAINT clase_pk PRIMARY KEY (cod_clase)
;

-- Table soul.consulta

CREATE TABLE soul.consulta(
 cod_consulta Integer NOT NULL,
 cod_proyecto Integer NOT NULL,
 nombre Character varying(120) DEFAULT ''::character varying NOT NULL,
 es_reporte Character(1) DEFAULT '0'::bpchar NOT NULL,
 nombre_reporte Character varying(120)
)
;

-- Add keys for table soul.consulta

ALTER TABLE soul.consulta ADD CONSTRAINT consulta_pk PRIMARY KEY (cod_consulta)
;

-- Table soul.consulta_atributo

CREATE TABLE soul.consulta_atributo(
 cod_consulta Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 flg_condicion Character(1) DEFAULT '0'::bpchar NOT NULL,
 cod_tabla Integer DEFAULT 0 NOT NULL,
 flg_reporte_rastrear Character(1) DEFAULT '0'::bpchar NOT NULL,
 flg_reporte_busqueda Character(1) DEFAULT '0'::bpchar NOT NULL
)
;

-- Add keys for table soul.consulta_atributo

ALTER TABLE soul.consulta_atributo ADD CONSTRAINT con_atributo_pk PRIMARY KEY (cod_consulta,cod_atributo)
;

-- Table soul.consulta_tabla

CREATE TABLE soul.consulta_tabla(
 cod_consulta Integer NOT NULL,
 cod_tabla Integer NOT NULL,
 fk Character(1) DEFAULT '0'::bpchar NOT NULL,
 flg_uno_muchos Character(1) DEFAULT '0'::bpchar NOT NULL,
 cod_tab_padre Integer
)
;

-- Add keys for table soul.consulta_tabla

ALTER TABLE soul.consulta_tabla ADD CONSTRAINT con_tabla_pk PRIMARY KEY (cod_consulta,cod_tabla)
;

-- Table soul.datasource

CREATE TABLE soul.datasource(
 cod_datasource Character varying(100) NOT NULL,
 cod_proyecto Integer,
 estado Character(1),
 descripcion Character varying(255) NOT NULL
)
;

-- Create indexes for table soul.datasource

CREATE INDEX ix_relationship33 ON soul.datasource (cod_proyecto)
;

-- Add keys for table soul.datasource

ALTER TABLE soul.datasource ADD CONSTRAINT sql150708145914480 PRIMARY KEY (cod_datasource)
;

-- Table soul.equipo

CREATE TABLE soul.equipo(
 cod_proyecto Integer NOT NULL,
 cod_usuario Character varying(50) NOT NULL,
 es_responsable Character(1) NOT NULL,
 carpeta_destino_workspace Character varying(255) DEFAULT ''::character varying NOT NULL,
 carpeta_destino_parcial Character varying(250) DEFAULT ''::character varying NOT NULL
)
;

-- Add keys for table soul.equipo

ALTER TABLE soul.equipo ADD CONSTRAINT Key1 PRIMARY KEY (cod_proyecto,cod_usuario)
;

-- Table soul.proceso

CREATE TABLE soul.proceso(
 cod_proceso Integer NOT NULL,
 cod_proyecto Integer NOT NULL,
 nombre Character varying(120) NOT NULL,
 jav_clase Character varying(120) NOT NULL,
 jav_datasource Character varying(120) NOT NULL,
 cod_con_resumen Integer NOT NULL,
 cod_con_detalle Integer NOT NULL,
 web_detalle_tipovista Character varying(1),
 cod_tarea Integer
);

-- Table soul.proceso_detalle_seccion

CREATE TABLE soul.proceso_detalle_seccion(
 cod_proceso Integer,
 cod_seccion Character varying(20) NOT NULL,
 tipo Character(1) NOT NULL,
 tipo_widget Character varying(20),
 nombre Character varying(100),
 cod_seccion_padre Character varying(20) 
 )
;

-- Table soul.proceso_detalle_sub_seccion

CREATE TABLE soul.proceso_detalle_sub_seccion(
 cod_proceso Integer,
 cod_seccion Character varying(20) NOT NULL,
 cod_sub_seccion Integer NOT NULL,
 nombre Character varying(100) NOT NULL
 )
;

-- Table soul.proceso_detalle

CREATE TABLE soul.proceso_detalle(
 cod_proceso Integer,
 cod_seccion Character varying(20) NOT NULL,
 cod_sub_seccion Integer NOT NULL,
 cod_proceso_detalle Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 web_etiqueta Character varying(255) NOT NULL
)
;

-- Create indexes for table soul.proceso

CREATE INDEX ix_proceso_proyecto_fk ON soul.proceso (cod_proyecto)
;

-- Add keys for table soul.proceso

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_pk PRIMARY KEY (cod_proceso)
;

-- Table soul.proceso_inicio_sub_seccion

CREATE TABLE soul.proceso_inicio_sub_seccion(
 cod_proceso Integer NOT NULL,
 cod_sub_seccion Integer NOT NULL,
 nombre Character varying(100) NOT NULL
 )
;

-- Add keys for table soul.proceso_inicio_sub_seccion

ALTER TABLE soul.proceso_inicio_sub_seccion ADD CONSTRAINT proceso_inicio_sub_seccion_pk PRIMARY KEY (cod_proceso,cod_sub_seccion)
;

-- Table soul.proceso_inicio

CREATE TABLE soul.proceso_inicio(
 cod_proceso Integer NOT NULL,
 cod_sub_seccion Integer NOT NULL,
 cod_proceso_inicio Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 web_etiqueta Character varying(255),
 web_tipo Character varying(60),
 web_tipo_campo Character(1),
 web_tipo_lista Character(1),
 web_catalogo Character varying(60),
 web_requerido Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_mensaje_validacion Character varying(255),
 val_omision Character varying(255)
)
;

-- Add keys for table soul.proceso_inicio

ALTER TABLE soul.proceso_inicio ADD CONSTRAINT proceso_inicio_atributo_pk PRIMARY KEY (cod_proceso,cod_sub_seccion,cod_proceso_inicio)
;

-- Table soul.proceso_rol_potencial

CREATE TABLE soul.proceso_rol_potencial(
 cod_proceso Integer NOT NULL,
 cod_rol Character varying(120) NOT NULL
)
;

-- Add keys for table soul.proceso_rol_potencial

ALTER TABLE soul.proceso_rol_potencial ADD CONSTRAINT pk_proceso_rol_potencial PRIMARY KEY (cod_proceso,cod_rol)
;

-- Table soul.proyecto

CREATE TABLE soul.proyecto(
 cod_proyecto Integer NOT NULL,
 nombre Character varying(120) NOT NULL,
 proyecto Character varying(255) NOT NULL,
 paquete Character varying(255) NOT NULL
)
;

-- Add keys for table soul.proyecto

ALTER TABLE soul.proyecto ADD CONSTRAINT proyecto_pk PRIMARY KEY (cod_proyecto)
;

-- Table soul.rol

CREATE TABLE soul.rol(
 cod_rol Character varying(120) NOT NULL,
 cod_proyecto Integer,
 estado Character(1),
 descripcion Character varying(255) NOT NULL
)
;

-- Create indexes for table soul.rol

CREATE INDEX ix_relationship32 ON soul.rol (cod_proyecto)
;

-- Add keys for table soul.rol

ALTER TABLE soul.rol ADD CONSTRAINT sql150708145914240 PRIMARY KEY (cod_rol)
;

-- Table soul.tabla

CREATE TABLE soul.tabla(
 cod_tabla Integer NOT NULL,
 esquema Character varying(50) NOT NULL,
 nombre Character varying(50) NOT NULL,
 cod_proyecto Integer NOT NULL,
 es_mantenimiento Character(1) DEFAULT '0'::bpchar NOT NULL,
 flg_mantenimiento_eliminar Character(1) DEFAULT '0'::bpchar NOT NULL
)
;

-- Add keys for table soul.tabla

ALTER TABLE soul.tabla ADD CONSTRAINT tabla_pk PRIMARY KEY (cod_tabla)
;

-- Table soul.tarea

CREATE TABLE soul.tarea(
 cod_tarea Integer NOT NULL,
 cod_proceso Integer,
 nombre Character varying(120) NOT NULL,
 cod_con_trabajar Integer NOT NULL,
 cod_con_completar Integer NOT NULL,
 clase Character varying(255) NOT NULL,
 tipo_vista Character(1),
 cod_tarea_siguiente Integer,
 cod_tarea_observado Integer
 web_acc_completar Character(1) DEFAULT '1'::bpchar NOT NULL,
 web_acc_grabar Character(1) DEFAULT '1'::bpchar NOT NULL,
 web_acc_cancelar Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_acc_rechazar Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_acc_observar Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_acc_subsanar Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_wid_documento Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_wid_historial Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_tie_rojo Integer NOT NULL,
 web_tie_amarillo Integer NOT NULL,
 web_plantilla_resumen Character varying(20)
)
;

-- Create indexes for table soul.tarea

CREATE INDEX IX_Relationship41 ON soul.tarea (cod_proceso)
;

-- Add keys for table soul.tarea

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_pk PRIMARY KEY (cod_tarea)
;

-- Table soul.tarea_atr_cancelar

CREATE TABLE soul.tarea_atr_cancelar(
 cod_tarea Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 cod_proceso Integer NOT NULL,
 jav_val_omision Character varying(250),
 web_flg_validacion Character(1)
)
;

-- Add keys for table soul.tarea_atr_cancelar

ALTER TABLE soul.tarea_atr_cancelar ADD CONSTRAINT tarea_atr_cancelar_pk PRIMARY KEY (cod_tarea,cod_atributo)
;

-- Table soul.tarea_atr_completar

CREATE TABLE soul.tarea_atr_completar(
 cod_tarea Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 cod_proceso Integer NOT NULL,
 jav_val_omision Character varying(250),
 web_flg_validacion Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_tab_campo Integer,
 web_ord_validacion Integer
)
;

-- Add keys for table soul.tarea_atr_completar

ALTER TABLE soul.tarea_atr_completar ADD CONSTRAINT tar_atr_completar_pk PRIMARY KEY (cod_tarea,cod_atributo)
;

-- Table soul.tarea_atr_observar

CREATE TABLE soul.tarea_atr_observar(
 cod_tarea Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 cod_proceso Integer NOT NULL,
 jav_val_omision Character varying(250),
 web_flg_validacion Character(1)
)
;

-- Add keys for table soul.tarea_atr_observar

ALTER TABLE soul.tarea_atr_observar ADD CONSTRAINT tarea_atr_observar_pk PRIMARY KEY (cod_tarea,cod_atributo)
;

-- Table soul.tarea_atr_rechazar

CREATE TABLE soul.tarea_atr_rechazar(
 cod_tarea Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 cod_proceso Integer NOT NULL,
 jav_val_omision Character varying(250),
 web_flg_validacion Character(1)
)
;

-- Add keys for table soul.tarea_atr_rechazar

ALTER TABLE soul.tarea_atr_rechazar ADD CONSTRAINT tarea_atr_rechazar_pk PRIMARY KEY (cod_tarea,cod_atributo)
;

-- Table soul.usuario

CREATE TABLE soul.usuario(
 cod_usuario Character varying(50) NOT NULL,
 clave Character varying(100) NOT NULL,
 nombre Character varying(100) NOT NULL,
 perfil Character varying(50) NOT NULL,
 descripcion Character varying(250)
)
;

-- Add keys for table soul.usuario

ALTER TABLE soul.usuario ADD CONSTRAINT pk_cod_usuario PRIMARY KEY (cod_usuario)
;

-- Table tarea_rol_potencial

CREATE TABLE soul.tarea_rol_potencial(
 cod_tarea Integer NOT NULL,
 cod_rol Character varying(120) NOT NULL
)
;

-- Add keys for table tarea_rol_potencial

ALTER TABLE soul.tarea_rol_potencial ADD CONSTRAINT Key2 PRIMARY KEY (cod_tarea,cod_rol)
;

-- Table tarea_rol_administrador

CREATE TABLE soul.tarea_rol_administrador(
 cod_tarea Integer NOT NULL,
 cod_rol Character varying(120) NOT NULL
)
;

-- Add keys for table tarea_rol_administrador

ALTER TABLE soul.tarea_rol_administrador ADD CONSTRAINT pk_tarea_rol_administrador PRIMARY KEY (cod_tarea,cod_rol)
;



-- Create relationships section ------------------------------------------------- 

ALTER TABLE soul.atributo ADD CONSTRAINT objeto_clase_fk FOREIGN KEY (cod_clase) REFERENCES soul.clase (cod_clase) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.atributo_sql ADD CONSTRAINT obj_sql_objeto_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE soul.atributo_sql ADD CONSTRAINT obj_atr_sql_tabla_fk FOREIGN KEY (cod_tabla) REFERENCES soul.tabla (cod_tabla) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE soul.catalogo ADD CONSTRAINT relationship29 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.clase ADD CONSTRAINT clase_proyecto_fk FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.clase ADD CONSTRAINT clase_tabla_fk FOREIGN KEY (cod_tabla) REFERENCES soul.tabla (cod_tabla) ON DELETE RESTRICT ON UPDATE CASCADE
;

ALTER TABLE soul.consulta ADD CONSTRAINT consulta_proyecto_fk FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.consulta_atributo ADD CONSTRAINT con_atributo_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.consulta_atributo ADD CONSTRAINT con_atributo_consulta_fk FOREIGN KEY (cod_consulta) REFERENCES soul.consulta (cod_consulta) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.consulta_tabla ADD CONSTRAINT con_tabla_consulta_fk FOREIGN KEY (cod_consulta) REFERENCES soul.consulta (cod_consulta) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.consulta_tabla ADD CONSTRAINT con_tabla_tabla_fk FOREIGN KEY (cod_tabla) REFERENCES soul.tabla (cod_tabla) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.consulta_tabla ADD CONSTRAINT con_tabla_tabla_fk1 FOREIGN KEY (cod_tab_padre) REFERENCES soul.tabla (cod_tabla) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.datasource ADD CONSTRAINT relationship33 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.mantenimiento ADD CONSTRAINT relationship38 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.mantenimiento_atributo ADD CONSTRAINT relationship37 FOREIGN KEY (cod_mantenimiento) REFERENCES soul.mantenimiento (cod_mantenimiento) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.mantenimiento_rol ADD CONSTRAINT relationship40 FOREIGN KEY (cod_mantenimiento) REFERENCES soul.mantenimiento (cod_mantenimiento) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.mantenimiento_rol ADD CONSTRAINT relationship39 FOREIGN KEY (cod_rol) REFERENCES soul.rol (cod_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_consulta_fk FOREIGN KEY (cod_con_resumen) REFERENCES soul.consulta (cod_consulta) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_consulta_fk1 FOREIGN KEY (cod_con_detalle) REFERENCES soul.consulta (cod_consulta) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_proyecto_fk FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE RESTRICT ON UPDATE CASCADE
;

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_tarea_fk FOREIGN KEY (cod_tarea) REFERENCES soul.tarea (cod_tarea) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso_inicio ADD CONSTRAINT pro_ini_atributo_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso_inicio ADD CONSTRAINT pro_ini_atributo_proceso_fk FOREIGN KEY (cod_proceso) REFERENCES soul.proceso (cod_proceso) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.rol ADD CONSTRAINT relationship32 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.tabla ADD CONSTRAINT tabla_proyecto_fk FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_consulta_fk FOREIGN KEY (cod_con_trabajar) REFERENCES soul.consulta (cod_consulta) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_consulta_fk1 FOREIGN KEY (cod_con_completar) REFERENCES soul.consulta (cod_consulta) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_siguiente_fk FOREIGN KEY (cod_tarea_siguiente) REFERENCES soul.tarea (cod_tarea) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_observado_fk FOREIGN KEY (cod_tarea_observado) REFERENCES soul.tarea (cod_tarea) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE soul.tarea_atr_cancelar ADD CONSTRAINT tar_atr_cancelar_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_cancelar ADD CONSTRAINT tar_atr_cancelar_tarea_fk FOREIGN KEY (cod_tarea) REFERENCES soul.tarea (cod_tarea) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_completar ADD CONSTRAINT tar_atr_completar_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_completar ADD CONSTRAINT tar_atr_completar_tarea_fk FOREIGN KEY (cod_tarea) REFERENCES soul.tarea (cod_tarea) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_observar ADD CONSTRAINT tarea_atr_observar_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_observar ADD CONSTRAINT tarea_atr_observar_tarea_fk FOREIGN KEY (cod_tarea) REFERENCES soul.tarea (cod_tarea) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_rechazar ADD CONSTRAINT tar_atr_rechazar_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_rechazar ADD CONSTRAINT tar_atr_rechazar_tarea_fk FOREIGN KEY (cod_tarea) REFERENCES soul.tarea (cod_tarea) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso_rol_potencial ADD CONSTRAINT Relationship35 FOREIGN KEY (cod_proceso) REFERENCES soul.proceso (cod_proceso) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso_rol_potencial ADD CONSTRAINT Relationship36 FOREIGN KEY (cod_rol) REFERENCES soul.rol (cod_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_rol_potencial ADD CONSTRAINT Relationship37 FOREIGN KEY (cod_tarea) REFERENCES soul.tarea (cod_tarea) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_rol_potencial ADD CONSTRAINT Relationship38 FOREIGN KEY (cod_rol) REFERENCES soul.rol (cod_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_rol_administrador ADD CONSTRAINT fk_tarea_rol_administrador_1 FOREIGN KEY (cod_tarea) REFERENCES soul.tarea (cod_tarea) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_rol_administrador ADD CONSTRAINT fk_tarea_rol_administrador_2 FOREIGN KEY (cod_rol) REFERENCES soul.rol (cod_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.equipo ADD CONSTRAINT Relationship39 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.equipo ADD CONSTRAINT Relationship40 FOREIGN KEY (cod_usuario) REFERENCES soul.usuario (cod_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea ADD CONSTRAINT Relationship41 FOREIGN KEY (cod_proceso) REFERENCES soul.proceso (cod_proceso) ON DELETE NO ACTION ON UPDATE NO ACTION
;


-- Table soul.tarea_resumen_plantilla

CREATE TABLE soul.tarea_resumen_plantilla (
 cod_plantilla Character varying(20),
 nombre Character varying(100)
 )
;

-- Table: soul.tarea_resumen_plantilla_sub_seccion

-- DROP TABLE soul.tarea_resumen_plantilla_sub_seccion;

CREATE TABLE soul.tarea_resumen_plantilla_sub_seccion
(
  cod_plantilla character varying(20) NOT NULL,
  cod_sub_seccion integer NOT NULL,
  nombre character varying(100) NOT NULL
);

ALTER TABLE soul.tarea_resumen_plantilla_sub_seccion ADD CONSTRAINT tarea_resumen_plantilla_sub_seccion_pk PRIMARY KEY (cod_plantilla,cod_sub_seccion)
;

-- Table: soul.tarea_resumen_plantilla_atributo

-- DROP TABLE soul.tarea_resumen_plantilla_atributo;

CREATE TABLE soul.tarea_resumen_plantilla_atributo
(
  cod_plantilla character varying(20) NOT NULL,
  cod_sub_seccion integer NOT NULL,
  cod_tarea_resumen_plantilla_atributo integer NOT NULL,
  cod_atributo integer NOT NULL,
  web_etiqueta character varying(255) NOT NULL
);

ALTER TABLE soul.tarea_resumen_plantilla_atributo ADD CONSTRAINT tarea_resumen_plantilla_atributo_pk PRIMARY KEY (cod_plantilla,cod_sub_seccion,cod_tarea_resumen_plantilla_atributo)
;

-- Table: soul.tarea_resumen

-- DROP TABLE soul.tarea_resumen;

CREATE TABLE soul.tarea_resumen
(
  cod_tarea integer NOT NULL,,
  cod_sub_seccion integer NOT NULL,
  cod_tarea_resumen integer NOT NULL,
  cod_atributo integer NOT NULL,
  web_etiqueta character varying(255) NOT NULL
);

ALTER TABLE soul.tarea_resumen ADD CONSTRAINT tarea_resumen_pk PRIMARY KEY (cod_tarea,cod_sub_seccion,cod_tarea_resumen)
;

-- Table: soul.tarea_resumen_sub_seccion

-- DROP TABLE soul.tarea_resumen_sub_seccion;

CREATE TABLE soul.tarea_resumen_sub_seccion
(
  cod_tarea integer NOT NULL,
  cod_sub_seccion integer NOT NULL,
  nombre character varying(100) NOT NULL
);

ALTER TABLE soul.tarea_resumen_sub_seccion ADD CONSTRAINT tarea_resumen_sub_seccion_pk PRIMARY KEY (cod_tarea,cod_sub_seccion)
;

-- Table soul.tarea_resumen_sub_seccion

CREATE TABLE soul.tarea_resumen_sub_seccion(
 cod_tarea Integer,
 cod_plantilla Character varying(20),
 cod_sub_seccion Integer NOT NULL,
 nombre Character varying(100) NOT NULL
 )
;

-- Table soul.tarea_resumen

CREATE TABLE soul.tarea_resumen(
 cod_tarea Integer,
 cod_plantilla Character varying(20),
 cod_sub_seccion Integer NOT NULL,
 cod_tarea_resumen Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 web_etiqueta Character varying(255) NOT NULL
)
;

-- Table soul.mae_motivo_cancelar

CREATE TABLE soul.mae_motivo_cancelar(
 cod_proyecto Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_mae_motivo_cancelar Integer NOT NULL,
 nombre Character varying(100) NOT NULL,
 descripcion Character varying(250),
 estado Character(1) DEFAULT '0'::bpchar NOT NULL
)
;

-- Create indexes for table soul.mae_motivo_cancelar

ALTER TABLE soul.mae_motivo_cancelar ADD CONSTRAINT mae_motivo_cancelar_pk PRIMARY KEY (cod_proyecto,cod_tarea,cod_mae_motivo_cancelar)
;

-- Table soul.mae_motivo_rechazar

CREATE TABLE soul.mae_motivo_rechazar(
 cod_proyecto Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_mae_motivo_rechazar Integer NOT NULL,
 nombre Character varying(100) NOT NULL,
 descripcion Character varying(250),
 estado Character(1) DEFAULT '0'::bpchar NOT NULL
)
;

-- Create indexes for table soul.mae_motivo_rechazar

ALTER TABLE soul.mae_motivo_rechazar ADD CONSTRAINT mae_motivo_rechazar_pk PRIMARY KEY (cod_proyecto,cod_tarea,cod_mae_motivo_rechazar)
;

-- Table soul.mae_documento

CREATE TABLE soul.mae_documento(
 cod_proyecto Integer NOT NULL,
 cod_mae_documento Integer NOT NULL,
 nombre Character varying(100) NOT NULL,
 descripcion Character varying(250),
 formatos Character varying(50) NOT NULL,
 estado Character(1) DEFAULT '0'::bpchar NOT NULL
)
;

-- Create indexes for table soul.mae_documento

ALTER TABLE soul.mae_documento ADD CONSTRAINT mae_documento_pk PRIMARY KEY (cod_proyecto,cod_mae_documento)
;

-- Table soul.mae_documento_tarea | tipo : "F" FIJO  -  "A" ADICIONAL

CREATE TABLE soul.mae_documento_tarea(
 cod_proyecto Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_mae_documento_tarea Integer NOT NULL,
 cod_mae_documento Integer NOT NULL,
 tipo Character(1) NOT NULL,
 es_obligatorio Character(1) DEFAULT '0'::bpchar NOT NULL,
 estado Character(1) DEFAULT '0'::bpchar NOT NULL
)
;

-- Create indexes for table soul.mae_documento_tarea

ALTER TABLE soul.mae_documento_tarea ADD CONSTRAINT mae_documento_tarea_pk PRIMARY KEY (cod_proyecto,cod_tarea,cod_mae_documento_tarea)
;

-- Table soul.mae_observacion

CREATE TABLE soul.mae_observacion(
 cod_proyecto Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_mae_observacion Integer NOT NULL,
 nombre Character varying(100) NOT NULL,
 descripcion Character varying(250),
 estado Character(1) DEFAULT '0'::bpchar NOT NULL
)
;

-- Create indexes for table soul.mae_observacion

ALTER TABLE soul.mae_observacion ADD CONSTRAINT mae_observacion_pk PRIMARY KEY (cod_proyecto,cod_tarea,cod_mae_observacion)
;

-- Table soul.mae_subsanacion | tipo_sustento : "A" ADJUNTO  -  "T" TEXTO

CREATE TABLE soul.mae_subsanacion(
 cod_proyecto Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_mae_observacion Integer NOT NULL,
 cod_mae_subsanacion Integer NOT NULL,
 nombre Character varying(100) NOT NULL,
 descripcion Character varying(250),
 estado Character(1) DEFAULT '0'::bpchar NOT NULL,
 tipo_sustento Character(1) DEFAULT 'F'::bpchar NOT NULL,
 cod_mae_documento_tarea Integer
)
;

-- Create indexes for table soul.mae_subsanacion

ALTER TABLE soul.mae_subsanacion ADD CONSTRAINT mae_subsanacion_pk PRIMARY KEY (cod_proyecto,cod_tarea,cod_mae_observacion, cod_mae_subsanacion)
;

CREATE TABLE soul.tarea_accion
(
  cod_tarea integer,
  cod_seccion character varying(20) NOT NULL,
  cod_sub_seccion integer,
  cod_tarea_accion integer NOT NULL,
  cod_tipo_accion Character(1) NOT NULL,
  cod_atributo Integer NOT NULL,
  web_etiqueta Character varying(255),
  web_tipo Character varying(60),
  web_tipo_campo Character(1),
  web_tipo_lista Character(1),
  web_catalogo Character varying(60),
  web_requerido Character(1) DEFAULT '0'::bpchar NOT NULL,
  web_mensaje_validacion Character varying(255),
  val_omision Character varying(255)
)
;

-- Table: soul.tarea_accion_seccion

-- DROP TABLE soul.tarea_accion_seccion;

CREATE TABLE soul.tarea_accion_seccion(
  cod_tarea integer,
  cod_seccion character varying(20) NOT NULL,
  tipo character(1) NOT NULL,
  tipo_widget character varying(20),
  nombre character varying(100),
  cod_seccion_padre character varying(20)
)
;

-- Table: soul.tarea_accion_sub_seccion

-- DROP TABLE soul.tarea_accion_sub_seccion;

CREATE TABLE soul.tarea_accion_sub_seccion(
  cod_tarea integer,
  cod_seccion character varying(20) NOT NULL,
  cod_sub_seccion integer NOT NULL,
  nombre character varying(100) NOT NULL
);

-- Table: soul.mae_catalogo

-- DROP TABLE soul.mae_catalogo;

CREATE TABLE soul.mae_catalogo
(
  cod_proyecto integer,
  cod_catalogo character varying(50) NOT NULL,
  cod_atributo character varying(50) NOT NULL,
  valor_1 character varying(100),
  valor_2 character varying(100),
  descripcion character varying(100),
  lim_cod_atributo integer,
  lim_valor_1 integer,
  lim_valor_2 integer,
  cabecera character(1) NOT NULL,
  CONSTRAINT mae_catalogo_pk PRIMARY KEY (cod_catalogo, cod_atributo),
  CONSTRAINT mae_catalogo_fk FOREIGN KEY (cod_proyecto)
      REFERENCES soul.proyecto (cod_proyecto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: soul.tabla_mantenimiento_rol

-- DROP TABLE soul.tabla_mantenimiento_rol;

CREATE TABLE soul.tabla_mantenimiento_rol(
  cod_tabla Integer NOT NULL,
  cod_rol character varying(120) NOT NULL
);

ALTER TABLE soul.tabla_mantenimiento_rol ADD CONSTRAINT tabla_mantenimiento_rol_pk PRIMARY KEY (cod_tabla,cod_rol)
;

-- DROP TABLE soul.consulta_reporte_rol;

CREATE TABLE soul.consulta_reporte_rol(
  cod_consulta Integer NOT NULL,
  cod_rol character varying(120) NOT NULL
);

ALTER TABLE soul.consulta_reporte_rol ADD CONSTRAINT consulta_reporte_rol_pk PRIMARY KEY (cod_consulta,cod_rol)
;