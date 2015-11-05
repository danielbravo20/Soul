

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