/*
Created: 20/10/2015
Modified: 20/10/2015
Model: RE PostgreSQL 9.4
Database: PostgreSQL 9.4
*/


-- Create schemas section -------------------------------------------------

CREATE SCHEMA soul AUTHORIZATION postgres
;

-- Create tables section -------------------------------------------------

-- Table soul.atributo

CREATE TABLE soul.atributo(
 cod_atributo Integer NOT NULL,
 cod_clase Integer NOT NULL,
 nombre Character varying(120) NOT NULL,
 tipo Character varying(255) NOT NULL,
 flg_lista Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_nombre Character varying(120),
 web_formato Character varying(120),
 inf_nombre Character varying(120),
 inf_descripcion Character varying(255),
 inf_autor Character varying(120),
 web_men_validacion Character varying(250),
 web_for_validacion Character varying(250)
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
 fk_campo Integer
)
;

-- Add keys for table soul.atributo_sql

ALTER TABLE soul.atributo_sql ADD CONSTRAINT obj_sql_pk PRIMARY KEY (cod_tabla,cod_atributo)
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

CREATE INDEX IX_Relationship29 ON soul.catalogo (cod_proyecto)
;

-- Add keys for table soul.catalogo

ALTER TABLE soul.catalogo ADD CONSTRAINT catalogo_pk PRIMARY KEY (cod_catalogo,cod_atributo)
;

-- Table soul.clase

CREATE TABLE soul.clase(
 cod_clase Integer NOT NULL,
 nombre Character varying(120) NOT NULL,
 paquete Character varying(250) NOT NULL,
 cod_proyecto Integer NOT NULL,
 inf_autor Character varying(120),
 inf_descripcion Character varying(255),
 nivel Integer
)
;

-- Add keys for table soul.clase

ALTER TABLE soul.clase ADD CONSTRAINT clase_pk PRIMARY KEY (cod_clase)
;

-- Table soul.configuracion

CREATE TABLE soul.configuracion(
 cod_proyecto Integer NOT NULL,
 usuario Character varying(20) NOT NULL,
 ruta_workspace Character varying(255) NOT NULL,
 ruta_script_sql Character varying(255) NOT NULL
)
;

-- Add keys for table soul.configuracion

ALTER TABLE soul.configuracion ADD CONSTRAINT configuracion_pk PRIMARY KEY (cod_proyecto,usuario)
;

-- Table soul.consulta

CREATE TABLE soul.consulta(
 cod_consulta Integer NOT NULL,
 sql_ale_sto_procedure Character varying(120) NOT NULL,
 jav_paquete Character varying(255) NOT NULL,
 jav_interface Character varying(255) NOT NULL,
 cod_proyecto Integer NOT NULL,
 nombre Character varying(120) DEFAULT ''::character varying NOT NULL
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
 flg_visible Character(1) DEFAULT '1'::bpchar NOT NULL,
 cod_tabla Integer DEFAULT 0 NOT NULL
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

CREATE INDEX IX_Relationship33 ON soul.datasource (cod_proyecto)
;

-- Add keys for table soul.datasource

ALTER TABLE soul.datasource ADD CONSTRAINT sql150708145914480 PRIMARY KEY (cod_datasource)
;

-- Table soul.esquema

CREATE TABLE soul.esquema(
 cod_esquema Character varying(100) NOT NULL,
 cod_proyecto Integer,
 estado Character(1),
 descripcion Character varying(255) NOT NULL
)
;

-- Create indexes for table soul.esquema

CREATE INDEX IX_Relationship35 ON soul.esquema (cod_proyecto)
;

-- Add keys for table soul.esquema

ALTER TABLE soul.esquema ADD CONSTRAINT sql150708145914380 PRIMARY KEY (cod_esquema)
;

-- Table soul.mantenimiento

CREATE TABLE soul.mantenimiento(
 cod_mantenimiento Character varying(80) NOT NULL,
 cod_proyecto Integer,
 nombre Character varying(100) NOT NULL,
 descripcion Character varying(255),
 cod_esquema Character varying(100) NOT NULL,
 cod_datasource Character varying(100) NOT NULL
)
;

-- Create indexes for table soul.mantenimiento

CREATE INDEX IX_Relationship38 ON soul.mantenimiento (cod_proyecto)
;

-- Add keys for table soul.mantenimiento

ALTER TABLE soul.mantenimiento ADD CONSTRAINT sql150717091917330 PRIMARY KEY (cod_mantenimiento)
;

-- Table soul.mantenimiento_atributo

CREATE TABLE soul.mantenimiento_atributo(
 cod_atributo Integer NOT NULL,
 cod_mantenimiento Character varying(80),
 nombre Character varying(50) NOT NULL,
 tipo_dato Character varying(50) NOT NULL,
 longitud Integer,
 precision Integer,
 es_llave_primaria Character(1),
 es_listado Character(1),
 es_busqueda Character(1),
 es_obligatorio Character(1),
 descripcion Character varying(255)
)
;

-- Create indexes for table soul.mantenimiento_atributo

CREATE INDEX IX_Relationship37 ON soul.mantenimiento_atributo (cod_mantenimiento)
;

-- Add keys for table soul.mantenimiento_atributo

ALTER TABLE soul.mantenimiento_atributo ADD CONSTRAINT sql150721084249850 PRIMARY KEY (cod_atributo)
;

-- Table soul.mantenimiento_rol

CREATE TABLE soul.mantenimiento_rol(
 cod_rol Character varying(100) NOT NULL,
 cod_mantenimiento Character varying(80) NOT NULL
)
;

-- Add keys for table soul.mantenimiento_rol

ALTER TABLE soul.mantenimiento_rol ADD CONSTRAINT sql150721084032400 PRIMARY KEY (cod_rol,cod_mantenimiento)
;

-- Table soul.proceso

CREATE TABLE soul.proceso(
 cod_proceso Integer NOT NULL,
 cod_proyecto Integer NOT NULL,
 inf_nombre Character varying(120) NOT NULL,
 cod_uni_negocio Character varying(12) NOT NULL,
 cod_producto Character varying(12) NOT NULL,
 inf_sufijo Character varying(6),
 bpm_plantilla Character varying(120) NOT NULL,
 bpm_act_inicio Integer NOT NULL,
 jav_paquete Character varying(255) NOT NULL,
 jav_clase Character varying(120) NOT NULL,
 jav_ale_proceso Character varying(120),
 jav_datasource Character varying(120) NOT NULL,
 cod_con_resumen Integer NOT NULL,
 cod_con_detalle Integer NOT NULL,
 jav_doc_sequence Character varying(120)
)
;

-- Create indexes for table soul.proceso

CREATE INDEX IX_proceso_proyecto_fk ON soul.proceso (cod_proyecto)
;

-- Add keys for table soul.proceso

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_pk PRIMARY KEY (cod_proceso)
;

-- Table soul.proceso_inicio

CREATE TABLE soul.proceso_inicio(
 cod_proceso Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 bpm_flg_entrada Character(1) DEFAULT '0'::bpchar NOT NULL,
 bpm_obj_referencia Character varying(60),
 bpm_flg_piid Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_flg_referencia Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_flg_validacion Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_men_validacion Character varying(255),
 web_val_omision Character varying(255),
 web_requerido Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_nom_cat_combo Character varying(255),
 sql_flg_autogenerado Character(1) DEFAULT '0'::bpchar NOT NULL,
 sql_nom_secuencial Character varying(255)
)
;

-- Add keys for table soul.proceso_inicio

ALTER TABLE soul.proceso_inicio ADD CONSTRAINT pro_ini_atributo_pk PRIMARY KEY (cod_proceso,cod_atributo)
;

-- Table soul.proyecto

CREATE TABLE soul.proyecto(
 cod_proyecto Integer NOT NULL,
 nombre Character varying(120) NOT NULL,
 jav_pro_libreria Character varying(255) NOT NULL,
 jav_pro_ejb Character varying(255) NOT NULL,
 jav_pro_cli_ejb Character varying(255) NOT NULL,
 jav_pro_web Character varying(255) NOT NULL,
 jav_paquete Character varying(255) NOT NULL,
 jav_paq_controlador Character varying(255) NOT NULL,
 jav_pre_controlador Character varying(50) NOT NULL,
 jav_pro_ejb_ext Character varying(255)
)
;

-- Add keys for table soul.proyecto

ALTER TABLE soul.proyecto ADD CONSTRAINT proyecto_pk PRIMARY KEY (cod_proyecto)
;

-- Table soul.rol

CREATE TABLE soul.rol(
 cod_rol Character varying(100) NOT NULL,
 cod_proyecto Integer,
 estado Character(1),
 descripcion Character varying(255) NOT NULL
)
;

-- Create indexes for table soul.rol

CREATE INDEX IX_Relationship32 ON soul.rol (cod_proyecto)
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
 orden Integer
)
;

-- Add keys for table soul.tabla

ALTER TABLE soul.tabla ADD CONSTRAINT tabla_pk PRIMARY KEY (cod_tabla)
;

-- Table soul.tarea

CREATE TABLE soul.tarea(
 cod_tarea Integer NOT NULL,
 cod_proceso Integer NOT NULL,
 nombre Character varying(120) NOT NULL,
 version Character varying(10) NOT NULL,
 cod_con_trabajar Integer NOT NULL,
 cod_con_completar Integer NOT NULL,
 sql_aleas Character varying(20) NOT NULL,
 sql_datasource Character varying(120) NOT NULL,
 bpm_nombre Character varying(255) NOT NULL,
 jav_paquete Character varying(255) NOT NULL,
 jav_clase Character varying(255) NOT NULL,
 web_acc_completar Character(1) DEFAULT '1'::bpchar NOT NULL,
 web_acc_grabar Character(1) DEFAULT '1'::bpchar NOT NULL,
 web_acc_cancelar Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_acc_rechazar Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_acc_observar Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_acc_salir Character(1) DEFAULT '1'::bpchar NOT NULL,
 web_acc_subsanar Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_par_his_comentario Character varying(120) NOT NULL,
 web_par_his_accion Character varying(120) NOT NULL,
 web_tie_rojo Integer NOT NULL,
 web_tie_amarillo Integer NOT NULL,
 web_flg_arc_adjuntos Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_flg_arc_adicionales Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_nom_configuracion Character varying(120),
 tipo_vista Character(1)
)
;

-- Add keys for table soul.tarea

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_pk PRIMARY KEY (cod_proceso,cod_tarea)
;

-- Table soul.tarea_atr_cancelar

CREATE TABLE soul.tarea_atr_cancelar(
 cod_proceso Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 jav_val_omision Character varying(250),
 web_flg_validacion Character(1)
)
;

-- Add keys for table soul.tarea_atr_cancelar

ALTER TABLE soul.tarea_atr_cancelar ADD CONSTRAINT tarea_atr_cancelar_pk PRIMARY KEY (cod_proceso,cod_tarea,cod_atributo)
;

-- Table soul.tarea_atr_completar

CREATE TABLE soul.tarea_atr_completar(
 cod_proceso Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 jav_val_omision Character varying(250),
 web_flg_validacion Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_tab_campo Integer,
 web_ord_validacion Integer
)
;

-- Add keys for table soul.tarea_atr_completar

ALTER TABLE soul.tarea_atr_completar ADD CONSTRAINT tar_atr_completar_pk PRIMARY KEY (cod_proceso,cod_tarea,cod_atributo)
;

-- Table soul.tarea_atr_observar

CREATE TABLE soul.tarea_atr_observar(
 cod_proceso Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 jav_val_omision Character varying(250),
 web_flg_validacion Character(1)
)
;

-- Add keys for table soul.tarea_atr_observar

ALTER TABLE soul.tarea_atr_observar ADD CONSTRAINT tarea_atr_observar_pk PRIMARY KEY (cod_proceso,cod_tarea,cod_atributo)
;

-- Table soul.tarea_atr_rechazar

CREATE TABLE soul.tarea_atr_rechazar(
 cod_proceso Integer NOT NULL,
 cod_tarea Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 jav_val_omision Character varying(250),
 web_flg_validacion Character(1)
)
;

-- Add keys for table soul.tarea_atr_rechazar

ALTER TABLE soul.tarea_atr_rechazar ADD CONSTRAINT tarea_atr_rechazar_pk PRIMARY KEY (cod_proceso,cod_tarea,cod_atributo)
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

-- Create relationships section ------------------------------------------------- 

ALTER TABLE soul.atributo ADD CONSTRAINT objeto_clase_fk FOREIGN KEY (cod_clase) REFERENCES soul.clase (cod_clase) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.atributo_sql ADD CONSTRAINT obj_sql_objeto_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.atributo_sql ADD CONSTRAINT obj_atr_sql_tabla_fk FOREIGN KEY (cod_tabla) REFERENCES soul.tabla (cod_tabla) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.clase ADD CONSTRAINT clase_proyecto_fk FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.configuracion ADD CONSTRAINT configuracion_proyecto_fk FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE CASCADE ON UPDATE NO ACTION
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

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_consulta_fk FOREIGN KEY (cod_con_resumen) REFERENCES soul.consulta (cod_consulta) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_consulta_fk1 FOREIGN KEY (cod_con_detalle) REFERENCES soul.consulta (cod_consulta) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso ADD CONSTRAINT proceso_proyecto_fk FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE RESTRICT ON UPDATE CASCADE
;

ALTER TABLE soul.proceso_inicio ADD CONSTRAINT pro_ini_atributo_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.proceso_inicio ADD CONSTRAINT pro_ini_atributo_proceso_fk FOREIGN KEY (cod_proceso) REFERENCES soul.proceso (cod_proceso) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tabla ADD CONSTRAINT tabla_proyecto_fk FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_consulta_fk FOREIGN KEY (cod_con_trabajar) REFERENCES soul.consulta (cod_consulta) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_consulta_fk1 FOREIGN KEY (cod_con_completar) REFERENCES soul.consulta (cod_consulta) ON DELETE RESTRICT ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea ADD CONSTRAINT tarea_proceso_fk FOREIGN KEY (cod_proceso) REFERENCES soul.proceso (cod_proceso) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_cancelar ADD CONSTRAINT tar_atr_cancelar_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_cancelar ADD CONSTRAINT tar_atr_cancelar_tarea_fk FOREIGN KEY (cod_tarea, cod_proceso) REFERENCES soul.tarea (cod_tarea, cod_proceso) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_completar ADD CONSTRAINT tar_atr_completar_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_completar ADD CONSTRAINT tar_atr_completar_tarea_fk FOREIGN KEY (cod_tarea, cod_proceso) REFERENCES soul.tarea (cod_tarea, cod_proceso) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_observar ADD CONSTRAINT tarea_atr_observar_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_observar ADD CONSTRAINT tarea_atr_observar_tarea_fk FOREIGN KEY (cod_tarea, cod_proceso) REFERENCES soul.tarea (cod_tarea, cod_proceso) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_rechazar ADD CONSTRAINT tar_atr_rechazar_atributo_fk FOREIGN KEY (cod_atributo) REFERENCES soul.atributo (cod_atributo) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.tarea_atr_rechazar ADD CONSTRAINT tar_atr_rechazar_tarea_fk FOREIGN KEY (cod_tarea, cod_proceso) REFERENCES soul.tarea (cod_tarea, cod_proceso) ON DELETE CASCADE ON UPDATE NO ACTION
;

ALTER TABLE soul.catalogo ADD CONSTRAINT Relationship29 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.rol ADD CONSTRAINT Relationship32 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.datasource ADD CONSTRAINT Relationship33 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.esquema ADD CONSTRAINT Relationship35 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.mantenimiento_atributo ADD CONSTRAINT Relationship37 FOREIGN KEY (cod_mantenimiento) REFERENCES soul.mantenimiento (cod_mantenimiento) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.mantenimiento ADD CONSTRAINT Relationship38 FOREIGN KEY (cod_proyecto) REFERENCES soul.proyecto (cod_proyecto) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.mantenimiento_rol ADD CONSTRAINT Relationship39 FOREIGN KEY (cod_rol) REFERENCES soul.rol (cod_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE soul.mantenimiento_rol ADD CONSTRAINT Relationship40 FOREIGN KEY (cod_mantenimiento) REFERENCES soul.mantenimiento (cod_mantenimiento) ON DELETE NO ACTION ON UPDATE NO ACTION
;



-- Create roles section -------------------------------------------------


-- Grant permissions section -------------------------------------------------

