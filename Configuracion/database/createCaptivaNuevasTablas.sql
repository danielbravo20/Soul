

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

-- Table soul.proceso_iniciob

CREATE TABLE soul.proceso_iniciob(
 cod_proceso Integer NOT NULL,
 cod_sub_seccion Integer NOT NULL,
 cod_proceso_inicio Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 web_etiqueta Character varying(255) NOT NULL,
 web_tipo Character varying(60) NOT NULL,
 web_tipo_campo Character(1) NOT NULL,
 web_tipo_lista Character(1),
 web_catalogo Character varying(60),
 web_requerido Character(1) DEFAULT '0'::bpchar NOT NULL,
 web_mensaje_validacion Character varying(255)
)
;

-- Add keys for table soul.proceso_iniciob

ALTER TABLE soul.proceso_iniciob ADD CONSTRAINT proceso_iniciob_atributo_pk PRIMARY KEY (cod_proceso,cod_sub_seccion,cod_proceso_inicio)
;



-- Table soul.proceso_inicio_sub_seccion

CREATE TABLE soul.proceso_detalle_seccion(
 cod_proceso Integer,
 cod_seccion Character varying(20) NOT NULL,
 tipo Character(1) NOT NULL,
 tipo_widget Character varying(20),
 nombre Character varying(100),
 cod_seccion_padre Character varying(20) 
 )
;

-- Table soul.proceso_inicio_sub_seccion

CREATE TABLE soul.proceso_detalle_sub_seccion(
 cod_proceso Integer,
 cod_seccion Character varying(20) NOT NULL,
 cod_sub_seccion Integer NOT NULL,
 nombre Character varying(100) NOT NULL
 )
;

-- Table soul.proceso_iniciob

CREATE TABLE soul.proceso_detalle(
 cod_proceso Integer,
 cod_seccion Character varying(20) NOT NULL,
 cod_sub_seccion Integer NOT NULL,
 cod_proceso_detalle Integer NOT NULL,
 cod_atributo Integer NOT NULL,
 web_etiqueta Character varying(255) NOT NULL
)
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
 jav_doc_sequence Character varying(120),
 web_detalle_tipovista Character varying(1),
)
;

ALTER TABLE soul.proceso ADD COLUMN web_detalle_tipovista Character varying(1);S
