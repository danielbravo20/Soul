--<ScriptOptions statementTerminator=";"/>

CREATE SCHEMA "BFP_CARTA_FIANZA";

CREATE TABLE "BFP_CARTA_FIANZA"."AS400_IN" (

		"COD_PROGRAMA" VARCHAR(10) NOT NULL,

		"NOM_ATRIBUTO" VARCHAR(10) NOT NULL,

		"TIPO_ATRIBUTO" VARCHAR(3) NOT NULL,

		"LONGITUD" INTEGER NOT NULL,

		"TIPO_DATO" VARCHAR(50) NOT NULL,

		"FORMATO_DATE" VARCHAR(20),

		"ESCALA" VARCHAR(2),

		"DESCRIPCION" VARCHAR(200),

		"VALOR_DEFECTO" VARCHAR(100),

		"ATRIBUTO_PROCESO" VARCHAR(100) NOT NULL,

		"OBJETO_LISTA" VARCHAR(10),

		"ORDEN" INTEGER NOT NULL DEFAULT 0,

		"TIMES" INTEGER,

		"LONGITUDLISTA" VARCHAR(3),

		"ORDENLISTA" INTEGER,

		"NUMEROELEMENTOS" INTEGER,

		"OBJETO_LISTA_DESTINO" VARCHAR(100)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."AS400_PROGRAMA" (

		"COD_PROGRAMA" VARCHAR(10) NOT NULL,

		"NOMBRE" VARCHAR(80) NOT NULL,

		"USUARIO" VARCHAR(50) NOT NULL,

		"COD_TRANSACCION" VARCHAR(10) NOT NULL,

		"BASE_NAMESPACE" VARCHAR(200) NOT NULL,

		"PATH_REQUEST_IN" VARCHAR(500),

		"PATH_REQUEST_OUT" VARCHAR(500),

		"PATH_RESPONSE_IN" VARCHAR(500),

		"PATH_RESPONSE_OUT" VARCHAR(500),

		"COD_PROYECTO" INTEGER NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."ATRIBUTO" (

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"COD_CLASE" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL,

		"TIPO" VARCHAR(255) NOT NULL,

		"FLG_LISTA" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_NOMBRE" VARCHAR(120),

		"WEB_FORMATO" VARCHAR(120),

		"INF_NOMBRE" VARCHAR(120),

		"INF_DESCRIPCION" VARCHAR(255),

		"INF_AUTOR" VARCHAR(120),

		"WEB_MEN_VALIDACION" VARCHAR(250),

		"WEB_FOR_VALIDACION" VARCHAR(250)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_DO" (

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"COD_OBJ_BPM" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL,

		"TIPO" VARCHAR(120),

		"OBJ_RELACIONADO" INTEGER

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_SQL" (

		"COD_TABLA" INTEGER NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"CAMPO" VARCHAR(50) NOT NULL,

		"TIPO" VARCHAR(50) NOT NULL,

		"LONGITUD" INTEGER,

		"PRECISION" INTEGER,

		"PK" CHAR(1) NOT NULL DEFAULT '0',

		"OBLIGATORIO" CHAR(1) NOT NULL DEFAULT '0',

		"FK_TABLA" INTEGER,

		"FK_UNO_MUCHO" CHAR(1),

		"FN_BUS_NOMBRE" VARCHAR(120),

		"FN_BUS_CATALOGO" VARCHAR(50),

		"VAL_DEFECTO" VARCHAR(120),

		"FK_CAMPO" INTEGER

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."CATALOGO" (

		"COD_CATALOGO" VARCHAR(50) NOT NULL,

		"COD_ATRIBUTO" VARCHAR(50) NOT NULL,

		"VALOR_1" VARCHAR(100),

		"VALOR_2" VARCHAR(100),

		"DESCRIPCION" VARCHAR(100),

		"LIM_COD_ATRIBUTO" INTEGER,

		"LIM_VALOR_1" INTEGER,

		"LIM_VALOR_2" INTEGER,

		"CABECERA" CHAR(1) NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL DEFAULT 0

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."CLASE" (

		"COD_CLASE" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL,

		"PAQUETE" VARCHAR(250) NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"INF_AUTOR" VARCHAR(120),

		"INF_DESCRIPCION" VARCHAR(255),

		"NIVEL" INTEGER

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."CONFIGURACION" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"USUARIO" VARCHAR(20) NOT NULL,

		"RUTA_WORKSPACE" VARCHAR(255) NOT NULL,

		"RUTA_SCRIPT_SQL" VARCHAR(255) NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."CONSULTA" (

		"COD_CONSULTA" INTEGER NOT NULL,

		"SQL_ALE_STO_PROCEDURE" VARCHAR(120) NOT NULL,

		"JAV_PAQUETE" VARCHAR(255) NOT NULL,

		"JAV_INTERFACE" VARCHAR(255) NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL DEFAULT ''

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."CONSULTA_ATRIBUTO" (

		"COD_CONSULTA" INTEGER NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"FLG_CONDICION" CHAR(1) NOT NULL DEFAULT '0',

		"FLG_VISIBLE" CHAR(1) NOT NULL DEFAULT '1',

		"COD_TABLA" INTEGER NOT NULL DEFAULT 0

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."CONSULTA_TABLA" (

		"COD_CONSULTA" INTEGER NOT NULL,

		"COD_TABLA" INTEGER NOT NULL,

		"FK" CHAR(1) NOT NULL DEFAULT '0',

		"FLG_UNO_MUCHOS" CHAR(1) NOT NULL DEFAULT '0',

		"COD_TAB_PADRE" INTEGER

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."DATASOURCE" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_DATASOURCE" VARCHAR(100) NOT NULL,

		"DESCRIPCION" VARCHAR(255) NOT NULL,

		"ESTADO" CHAR(1)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."EQUIPO" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_USUARIO" VARCHAR(50) NOT NULL,

		"ES_RESPONSABLE" CHAR(1) NOT NULL,

		"CARPETA_DESTINO_WORKSPACE" VARCHAR(255) NOT NULL DEFAULT '',

		"CARPETA_DESTINO_PARCIAL" VARCHAR(250) NOT NULL DEFAULT ''

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."ESQUEMA" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_ESQUEMA" VARCHAR(100) NOT NULL,

		"DESCRIPCION" VARCHAR(255) NOT NULL,

		"ESTADO" CHAR(1)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."MAE_PRODUCTO" (

		"COD_PRODUCTO" VARCHAR(12) NOT NULL,

		"COD_UNI_NEGOCIO" VARCHAR(12) NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."MAE_UNI_NEGOCIO" (

		"COD_UNI_NEGOCIO" VARCHAR(12) NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."MANTENIMIENTO" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_MANTENIMIENTO" VARCHAR(80) NOT NULL,

		"NOMBRE" VARCHAR(100) NOT NULL,

		"DESCRIPCION" VARCHAR(255),

		"COD_ESQUEMA" VARCHAR(100) NOT NULL,

		"COD_DATASOURCE" VARCHAR(100) NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."MANTENIMIENTO_ATRIBUTO" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_MANTENIMIENTO" VARCHAR(80) NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(50) NOT NULL,

		"TIPO_DATO" VARCHAR(50) NOT NULL,

		"LONGITUD" INTEGER,

		"PRECISION" INTEGER,

		"ES_LLAVE_PRIMARIA" CHAR(1),

		"ES_LISTADO" CHAR(1),

		"ES_BUSQUEDA" CHAR(1),

		"ES_OBLIGATORIO" CHAR(1),

		"DESCRIPCION" VARCHAR(255)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."MANTENIMIENTO_ROL" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_MANTENIMIENTO" VARCHAR(80) NOT NULL,

		"COD_ROL" VARCHAR(100) NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."OBJ_BPM" (

		"COD_OBJ_BPM" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(50) NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"FLG_PADRE" CHAR(1) NOT NULL DEFAULT '0',

		"COD_TABLA" INTEGER

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."PROCESO" (

		"COD_PROCESO" INTEGER NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"INF_NOMBRE" VARCHAR(120) NOT NULL,

		"COD_UNI_NEGOCIO" VARCHAR(12) NOT NULL,

		"COD_PRODUCTO" VARCHAR(12) NOT NULL,

		"INF_SUFIJO" VARCHAR(6),

		"BPM_PLANTILLA" VARCHAR(120) NOT NULL,

		"BPM_ACT_INICIO" INTEGER NOT NULL,

		"JAV_PAQUETE" VARCHAR(255) NOT NULL,

		"JAV_CLASE" VARCHAR(120) NOT NULL,

		"JAV_ALE_PROCESO" VARCHAR(120),

		"JAV_DATASOURCE" VARCHAR(120) NOT NULL,

		"COD_CON_RESUMEN" INTEGER NOT NULL,

		"COD_CON_DETALLE" INTEGER NOT NULL,

		"JAV_DOC_SEQUENCE" VARCHAR(120)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."PROCESO_INICIO" (

		"COD_PROCESO" INTEGER NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"BPM_FLG_ENTRADA" CHAR(1) NOT NULL DEFAULT '0',

		"BPM_OBJ_REFERENCIA" VARCHAR(60),

		"BPM_FLG_PIID" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_FLG_REFERENCIA" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_FLG_VALIDACION" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_MEN_VALIDACION" VARCHAR(255),

		"WEB_VAL_OMISION" VARCHAR(255),

		"WEB_REQUERIDO" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_NOM_CAT_COMBO" VARCHAR(255),

		"SQL_FLG_AUTOGENERADO" CHAR(1) NOT NULL DEFAULT '0',

		"SQL_NOM_SECUENCIAL" VARCHAR(255),

		"COD_PROYECTO" INTEGER NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."PROYECTO" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL,

		"JAV_PRO_LIBRERIA" VARCHAR(255) NOT NULL,

		"JAV_PRO_EJB" VARCHAR(255) NOT NULL,

		"JAV_PRO_CLI_EJB" VARCHAR(255) NOT NULL,

		"JAV_PRO_WEB" VARCHAR(255) NOT NULL,

		"JAV_PAQUETE" VARCHAR(255) NOT NULL,

		"JAV_PAQ_CONTROLADOR" VARCHAR(255) NOT NULL,

		"JAV_PRE_CONTROLADOR" VARCHAR(50) NOT NULL,

		"JAV_PRO_EJB_EXT" VARCHAR(255)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."ROL" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_ROL" VARCHAR(100) NOT NULL,

		"DESCRIPCION" VARCHAR(255) NOT NULL,

		"ESTADO" CHAR(1)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TABLA" (

		"COD_TABLA" INTEGER NOT NULL,

		"ESQUEMA" VARCHAR(50) NOT NULL,

		"NOMBRE" VARCHAR(50) NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"ORDEN" INTEGER

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TABLATIEMPO" (

		"VALOR1" TIME,

		"VALOR2" TIME

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA" (

		"COD_TAREA" INTEGER NOT NULL,

		"COD_PROCESO" INTEGER NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL,

		"VERSION" VARCHAR(10) NOT NULL,

		"COD_CON_TRABAJAR" INTEGER NOT NULL,

		"COD_CON_COMPLETAR" INTEGER NOT NULL,

		"SQL_ALEAS" VARCHAR(20) NOT NULL,

		"SQL_DATASOURCE" VARCHAR(120) NOT NULL,

		"BPM_NOMBRE" VARCHAR(255) NOT NULL,

		"JAV_PAQUETE" VARCHAR(255) NOT NULL,

		"JAV_CLASE" VARCHAR(255) NOT NULL,

		"WEB_ACC_COMPLETAR" CHAR(1) NOT NULL DEFAULT '1',

		"WEB_ACC_GRABAR" CHAR(1) NOT NULL DEFAULT '1',

		"WEB_ACC_CANCELAR" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_ACC_RECHAZAR" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_ACC_OBSERVAR" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_ACC_SALIR" CHAR(1) NOT NULL DEFAULT '1',

		"WEB_ACC_SUBSANAR" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_PAR_HIS_COMENTARIO" VARCHAR(120) NOT NULL,

		"WEB_PAR_HIS_ACCION" VARCHAR(120) NOT NULL,

		"WEB_TIE_ROJO" INTEGER NOT NULL,

		"WEB_TIE_AMARILLO" INTEGER NOT NULL,

		"WEB_FLG_ARC_ADJUNTOS" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_FLG_ARC_ADICIONALES" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_NOM_CONFIGURACION" VARCHAR(120),

		"TIPO_VISTA" CHAR(1)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_ATRIBUTO" (

		"TAREA_SECCION_ID" VARCHAR(20) NOT NULL,

		"TAREA_SUB_SECCION_ID" INTEGER NOT NULL,

		"TAREA_ATRIBUTO_ID" INTEGER NOT NULL,

		"COD_CLASE" INTEGER NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL,

		"ACTIVO" CHAR(1) NOT NULL,

		"EDITOR_TIPO_CAMPO" CHAR(1),

		"EDITOR_OBLIGATORIO" CHAR(1)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_CANCELAR" (

		"COD_PROCESO" INTEGER NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_TAREA" INTEGER NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"JAV_VAL_OMISION" VARCHAR(250),

		"WEB_FLG_VALIDACION" CHAR(1)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_COMPLETAR" (

		"COD_PROCESO" INTEGER NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_TAREA" INTEGER NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"JAV_VAL_OMISION" VARCHAR(250),

		"WEB_FLG_VALIDACION" CHAR(1) NOT NULL DEFAULT '0',

		"WEB_TAB_CAMPO" INTEGER,

		"WEB_ORD_VALIDACION" INTEGER

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_OBSERVAR" (

		"COD_PROCESO" INTEGER NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_TAREA" INTEGER NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"JAV_VAL_OMISION" VARCHAR(250),

		"WEB_FLG_VALIDACION" CHAR(1)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_RECHAZAR" (

		"COD_PROCESO" INTEGER NOT NULL,

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_TAREA" INTEGER NOT NULL,

		"COD_ATRIBUTO" INTEGER NOT NULL,

		"JAV_VAL_OMISION" VARCHAR(250),

		"WEB_FLG_VALIDACION" CHAR(1)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_COM_BPM" (

		"COD_TAREA" INTEGER NOT NULL,

		"COD_OBJ_BPM" INTEGER NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_SECCION" (

		"COD_TAREA" INTEGER,

		"COD_PROCESO" INTEGER,

		"COD_PROYECTO" INTEGER,

		"ID" VARCHAR(20) NOT NULL,

		"TAREA_SECCION_ID" INTEGER,

		"NOMBRE" VARCHAR(120),

		"ACTIVO" CHAR(1),

		"ID_PADRE" VARCHAR(20)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_SUB_SECCION" (

		"TAREA_SECCION_ID" VARCHAR(20) NOT NULL,

		"TAREA_SUB_SECCION_ID" INTEGER NOT NULL,

		"NOMBRE" VARCHAR(120) NOT NULL,

		"ACTIVO" CHAR(1) NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."TAREA_TRA_BPM" (

		"COD_TAREA" INTEGER NOT NULL,

		"COD_OBJ_BPM" INTEGER NOT NULL

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."USUARIO" (

		"COD_USUARIO" VARCHAR(50) NOT NULL,

		"CLAVE" VARCHAR(100) NOT NULL,

		"NOMBRE" VARCHAR(100) NOT NULL,

		"PERFIL" VARCHAR(50) NOT NULL,

		"DESCRIPCION" VARCHAR(250)

	)

	DATA CAPTURE NONE;

CREATE TABLE "BFP_CARTA_FIANZA"."VERSION" (

		"COD_PROYECTO" INTEGER NOT NULL,

		"COD_VERSION" INTEGER NOT NULL,

		"ESTADO" CHAR(1),

		"TIEMPO_ESTIMADO" INTEGER,

		"FECHA_CREACION" TIMESTAMP,

		"FECHA_FINALIZACION" TIMESTAMP

	)

	DATA CAPTURE NONE;

ALTER TABLE "BFP_CARTA_FIANZA"."AS400_IN" ADD CONSTRAINT "AS400_IN_PK" PRIMARY KEY

	("COD_PROGRAMA",

	 "NOM_ATRIBUTO",

	 "TIPO_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."AS400_PROGRAMA" ADD CONSTRAINT "AS400_PROGRAMA_PK" PRIMARY KEY

	("COD_PROGRAMA");

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO" ADD CONSTRAINT "OBJETO_PK" PRIMARY KEY

	("COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_DO" ADD CONSTRAINT "OBJ_PROCESO_PK" PRIMARY KEY

	("COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_SQL" ADD CONSTRAINT "OBJ_SQL_PK" PRIMARY KEY

	("COD_TABLA",

	 "COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."CATALOGO" ADD CONSTRAINT "CATALOGO_PK" PRIMARY KEY

	("COD_CATALOGO",

	 "COD_ATRIBUTO",

	 "COD_PROYECTO");

ALTER TABLE "BFP_CARTA_FIANZA"."CLASE" ADD CONSTRAINT "CLASE_PK" PRIMARY KEY

	("COD_CLASE");

ALTER TABLE "BFP_CARTA_FIANZA"."CONFIGURACION" ADD CONSTRAINT "CONFIGURACION_PK" PRIMARY KEY

	("COD_PROYECTO",

	 "USUARIO");

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA" ADD CONSTRAINT "CONSULTA_PK" PRIMARY KEY

	("COD_CONSULTA");

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA_ATRIBUTO" ADD CONSTRAINT "CON_ATRIBUTO_PK" PRIMARY KEY

	("COD_CONSULTA",

	 "COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA_TABLA" ADD CONSTRAINT "CON_TABLA_PK" PRIMARY KEY

	("COD_CONSULTA",

	 "COD_TABLA");

ALTER TABLE "BFP_CARTA_FIANZA"."DATASOURCE" ADD CONSTRAINT "SQL150708145914480" PRIMARY KEY

	("COD_PROYECTO",

	 "COD_DATASOURCE");

ALTER TABLE "BFP_CARTA_FIANZA"."EQUIPO" ADD CONSTRAINT "SQL150708144027730" PRIMARY KEY

	("COD_PROYECTO",

	 "COD_USUARIO");

ALTER TABLE "BFP_CARTA_FIANZA"."ESQUEMA" ADD CONSTRAINT "SQL150708145914380" PRIMARY KEY

	("COD_PROYECTO",

	 "COD_ESQUEMA");

ALTER TABLE "BFP_CARTA_FIANZA"."MAE_PRODUCTO" ADD CONSTRAINT "MAE_PRODUCTO_PK" PRIMARY KEY

	("COD_UNI_NEGOCIO",

	 "COD_PRODUCTO");

ALTER TABLE "BFP_CARTA_FIANZA"."MAE_UNI_NEGOCIO" ADD CONSTRAINT "MAE_UNI_NEGOCIO_PK" PRIMARY KEY

	("COD_UNI_NEGOCIO");

ALTER TABLE "BFP_CARTA_FIANZA"."MANTENIMIENTO" ADD CONSTRAINT "SQL150717091917330" PRIMARY KEY

	("COD_PROYECTO",

	 "COD_MANTENIMIENTO");

ALTER TABLE "BFP_CARTA_FIANZA"."MANTENIMIENTO_ATRIBUTO" ADD CONSTRAINT "SQL150721084249850" PRIMARY KEY

	("COD_PROYECTO",

	 "COD_MANTENIMIENTO",

	 "COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."MANTENIMIENTO_ROL" ADD CONSTRAINT "SQL150721084032400" PRIMARY KEY

	("COD_PROYECTO",

	 "COD_MANTENIMIENTO",

	 "COD_ROL");

ALTER TABLE "BFP_CARTA_FIANZA"."OBJ_BPM" ADD CONSTRAINT "OBJ_BPM_PK" PRIMARY KEY

	("COD_OBJ_BPM");

ALTER TABLE "BFP_CARTA_FIANZA"."PROCESO" ADD CONSTRAINT "PROCESO_PK" PRIMARY KEY

	("COD_PROCESO",

	 "COD_PROYECTO");

ALTER TABLE "BFP_CARTA_FIANZA"."PROCESO_INICIO" ADD CONSTRAINT "PRO_INI_ATRIBUTO_PK" PRIMARY KEY

	("COD_PROCESO",

	 "COD_ATRIBUTO",

	 "COD_PROYECTO");

ALTER TABLE "BFP_CARTA_FIANZA"."PROYECTO" ADD CONSTRAINT "PROYECTO_PK" PRIMARY KEY

	("COD_PROYECTO");

ALTER TABLE "BFP_CARTA_FIANZA"."ROL" ADD CONSTRAINT "SQL150708145914240" PRIMARY KEY

	("COD_PROYECTO",

	 "COD_ROL");

ALTER TABLE "BFP_CARTA_FIANZA"."TABLA" ADD CONSTRAINT "TABLA_PK" PRIMARY KEY

	("COD_TABLA");

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA" ADD CONSTRAINT "TAREA_PK" PRIMARY KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA");

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_CANCELAR" ADD CONSTRAINT "TAREA_ATR_CANCELAR_PK" PRIMARY KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA",

	 "COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_COMPLETAR" ADD CONSTRAINT "TAR_ATR_COMPLETAR_PK" PRIMARY KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA",

	 "COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_OBSERVAR" ADD CONSTRAINT "TAREA_ATR_OBSERVAR_PK" PRIMARY KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA",

	 "COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_RECHAZAR" ADD CONSTRAINT "TAREA_ATR_RECHAZAR_PK" PRIMARY KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA",

	 "COD_ATRIBUTO");

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_COM_BPM" ADD CONSTRAINT "TAREA_COM_BPM_PK" PRIMARY KEY

	("COD_TAREA",

	 "COD_OBJ_BPM");

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_TRA_BPM" ADD CONSTRAINT "TAREA_TRA_BPM_PK" PRIMARY KEY

	("COD_TAREA",

	 "COD_OBJ_BPM");

ALTER TABLE "BFP_CARTA_FIANZA"."USUARIO" ADD CONSTRAINT "PK_COD_USUARIO" PRIMARY KEY

	("COD_USUARIO");

ALTER TABLE "BFP_CARTA_FIANZA"."VERSION" ADD CONSTRAINT "SQL150708145101350" PRIMARY KEY

	("COD_PROYECTO",

	 "COD_VERSION");

ALTER TABLE "BFP_CARTA_FIANZA"."AS400_IN" ADD CONSTRAINT "AS400_IN_AS400_PROGRAMA_FK" FOREIGN KEY

	("COD_PROGRAMA")

	REFERENCES "BFP_CARTA_FIANZA"."AS400_PROGRAMA"

	("COD_PROGRAMA")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO" ADD CONSTRAINT "OBJETO_CLASE_FK" FOREIGN KEY

	("COD_CLASE")

	REFERENCES "BFP_CARTA_FIANZA"."CLASE"

	("COD_CLASE")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_DO" ADD CONSTRAINT "OBJ_ATR_BPM_OBJ_BPM_FK" FOREIGN KEY

	("COD_OBJ_BPM")

	REFERENCES "BFP_CARTA_FIANZA"."OBJ_BPM"

	("COD_OBJ_BPM")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_DO" ADD CONSTRAINT "OBJ_ATR_BPM_OBJ_BPM_FK1" FOREIGN KEY

	("OBJ_RELACIONADO")

	REFERENCES "BFP_CARTA_FIANZA"."OBJ_BPM"

	("COD_OBJ_BPM")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_DO" ADD CONSTRAINT "OBJ_PROCESO_OBJETO_FK" FOREIGN KEY

	("COD_ATRIBUTO")

	REFERENCES "BFP_CARTA_FIANZA"."ATRIBUTO"

	("COD_ATRIBUTO")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_SQL" ADD CONSTRAINT "OBJ_ATR_SQL_TABLA_FK" FOREIGN KEY

	("COD_TABLA")

	REFERENCES "BFP_CARTA_FIANZA"."TABLA"

	("COD_TABLA")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."ATRIBUTO_SQL" ADD CONSTRAINT "OBJ_SQL_OBJETO_FK" FOREIGN KEY

	("COD_ATRIBUTO")

	REFERENCES "BFP_CARTA_FIANZA"."ATRIBUTO"

	("COD_ATRIBUTO")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."CLASE" ADD CONSTRAINT "CLASE_PROYECTO_FK" FOREIGN KEY

	("COD_PROYECTO")

	REFERENCES "BFP_CARTA_FIANZA"."PROYECTO"

	("COD_PROYECTO")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."CONFIGURACION" ADD CONSTRAINT "CONFIGURACION_PROYECTO_FK" FOREIGN KEY

	("COD_PROYECTO")

	REFERENCES "BFP_CARTA_FIANZA"."PROYECTO"

	("COD_PROYECTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA" ADD CONSTRAINT "CONSULTA_PROYECTO_FK" FOREIGN KEY

	("COD_PROYECTO")

	REFERENCES "BFP_CARTA_FIANZA"."PROYECTO"

	("COD_PROYECTO")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA_ATRIBUTO" ADD CONSTRAINT "CON_ATRIBUTO_ATRIBUTO_FK" FOREIGN KEY

	("COD_ATRIBUTO")

	REFERENCES "BFP_CARTA_FIANZA"."ATRIBUTO"

	("COD_ATRIBUTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA_ATRIBUTO" ADD CONSTRAINT "CON_ATRIBUTO_CONSULTA_FK" FOREIGN KEY

	("COD_CONSULTA")

	REFERENCES "BFP_CARTA_FIANZA"."CONSULTA"

	("COD_CONSULTA")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA_TABLA" ADD CONSTRAINT "CON_TABLA_CONSULTA_FK" FOREIGN KEY

	("COD_CONSULTA")

	REFERENCES "BFP_CARTA_FIANZA"."CONSULTA"

	("COD_CONSULTA")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA_TABLA" ADD CONSTRAINT "CON_TABLA_TABLA_FK" FOREIGN KEY

	("COD_TABLA")

	REFERENCES "BFP_CARTA_FIANZA"."TABLA"

	("COD_TABLA")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."CONSULTA_TABLA" ADD CONSTRAINT "CON_TABLA_TABLA_FK1" FOREIGN KEY

	("COD_TAB_PADRE")

	REFERENCES "BFP_CARTA_FIANZA"."TABLA"

	("COD_TABLA")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."MAE_PRODUCTO" ADD CONSTRAINT "MAE_PRODUCTO_MAE_UNI_NEGOCIO_FK" FOREIGN KEY

	("COD_UNI_NEGOCIO")

	REFERENCES "BFP_CARTA_FIANZA"."MAE_UNI_NEGOCIO"

	("COD_UNI_NEGOCIO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."OBJ_BPM" ADD CONSTRAINT "OBJ_BPM_PROYECTO_FK" FOREIGN KEY

	("COD_PROYECTO")

	REFERENCES "BFP_CARTA_FIANZA"."PROYECTO"

	("COD_PROYECTO")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."PROCESO" ADD CONSTRAINT "PROCESO_CONSULTA_FK" FOREIGN KEY

	("COD_CON_RESUMEN")

	REFERENCES "BFP_CARTA_FIANZA"."CONSULTA"

	("COD_CONSULTA")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."PROCESO" ADD CONSTRAINT "PROCESO_CONSULTA_FK1" FOREIGN KEY

	("COD_CON_DETALLE")

	REFERENCES "BFP_CARTA_FIANZA"."CONSULTA"

	("COD_CONSULTA")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."PROCESO" ADD CONSTRAINT "PROCESO_MAE_PRODUCTO_FK" FOREIGN KEY

	("COD_UNI_NEGOCIO",

	 "COD_PRODUCTO")

	REFERENCES "BFP_CARTA_FIANZA"."MAE_PRODUCTO"

	("COD_UNI_NEGOCIO",

	 "COD_PRODUCTO")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."PROCESO" ADD CONSTRAINT "PROCESO_PROYECTO_FK" FOREIGN KEY

	("COD_PROYECTO")

	REFERENCES "BFP_CARTA_FIANZA"."PROYECTO"

	("COD_PROYECTO")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."PROCESO_INICIO" ADD CONSTRAINT "PRO_INI_ATRIBUTO_ATRIBUTO_FK" FOREIGN KEY

	("COD_ATRIBUTO")

	REFERENCES "BFP_CARTA_FIANZA"."ATRIBUTO"

	("COD_ATRIBUTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."PROCESO_INICIO" ADD CONSTRAINT "PRO_INI_ATRIBUTO_PROCESO_FK" FOREIGN KEY

	("COD_PROCESO",

	 "COD_PROYECTO")

	REFERENCES "BFP_CARTA_FIANZA"."PROCESO"

	("COD_PROCESO",

	 "COD_PROYECTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TABLA" ADD CONSTRAINT "TABLA_PROYECTO_FK" FOREIGN KEY

	("COD_PROYECTO")

	REFERENCES "BFP_CARTA_FIANZA"."PROYECTO"

	("COD_PROYECTO")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA" ADD CONSTRAINT "TAREA_CONSULTA_FK" FOREIGN KEY

	("COD_CON_TRABAJAR")

	REFERENCES "BFP_CARTA_FIANZA"."CONSULTA"

	("COD_CONSULTA")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA" ADD CONSTRAINT "TAREA_CONSULTA_FK1" FOREIGN KEY

	("COD_CON_COMPLETAR")

	REFERENCES "BFP_CARTA_FIANZA"."CONSULTA"

	("COD_CONSULTA")

	ON DELETE RESTRICT;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA" ADD CONSTRAINT "TAREA_PROCESO_FK" FOREIGN KEY

	("COD_PROCESO",

	 "COD_PROYECTO")

	REFERENCES "BFP_CARTA_FIANZA"."PROCESO"

	("COD_PROCESO",

	 "COD_PROYECTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_CANCELAR" ADD CONSTRAINT "TAR_ATR_CANCELAR_ATRIBUTO_FK" FOREIGN KEY

	("COD_ATRIBUTO")

	REFERENCES "BFP_CARTA_FIANZA"."ATRIBUTO"

	("COD_ATRIBUTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_CANCELAR" ADD CONSTRAINT "TAR_ATR_CANCELAR_TAREA_FK" FOREIGN KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA")

	REFERENCES "BFP_CARTA_FIANZA"."TAREA"

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_COMPLETAR" ADD CONSTRAINT "TAR_ATR_COMPLETAR_ATRIBUTO_FK" FOREIGN KEY

	("COD_ATRIBUTO")

	REFERENCES "BFP_CARTA_FIANZA"."ATRIBUTO"

	("COD_ATRIBUTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_COMPLETAR" ADD CONSTRAINT "TAR_ATR_COMPLETAR_TAREA_FK" FOREIGN KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA")

	REFERENCES "BFP_CARTA_FIANZA"."TAREA"

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_OBSERVAR" ADD CONSTRAINT "TAREA_ATR_OBSERVAR_ATRIBUTO_FK" FOREIGN KEY

	("COD_ATRIBUTO")

	REFERENCES "BFP_CARTA_FIANZA"."ATRIBUTO"

	("COD_ATRIBUTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_OBSERVAR" ADD CONSTRAINT "TAREA_ATR_OBSERVAR_TAREA_FK" FOREIGN KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA")

	REFERENCES "BFP_CARTA_FIANZA"."TAREA"

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_RECHAZAR" ADD CONSTRAINT "TAR_ATR_RECHAZAR_ATRIBUTO_FK" FOREIGN KEY

	("COD_ATRIBUTO")

	REFERENCES "BFP_CARTA_FIANZA"."ATRIBUTO"

	("COD_ATRIBUTO")

	ON DELETE CASCADE;

ALTER TABLE "BFP_CARTA_FIANZA"."TAREA_ATR_RECHAZAR" ADD CONSTRAINT "TAR_ATR_RECHAZAR_TAREA_FK" FOREIGN KEY

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA")

	REFERENCES "BFP_CARTA_FIANZA"."TAREA"

	("COD_PROCESO",

	 "COD_PROYECTO",

	 "COD_TAREA")

	ON DELETE CASCADE;

CREATE VIEW "BFP_CARTA_FIANZA"."PROCESO_INICIO_ATRIBUTO_CLASE" ("COD_PROYECTO", "COD_PROCESO", "COD_ATRIBUTO", "NOM_ATRIBUTO", "COD_CLASE", "NOM_CLASE", "BPM_FLG_ENTRADA", "BPM_OBJ_REFERENCIA", "BPM_FLG_PIID", "WEB_FLG_REFERENCIA", "WEB_FLG_VALIDACION", "WEB_MEN_VALIDACION", "WEB_VAL_OMISION", "WEB_REQUERIDO", "WEB_NOM_CAT_COMBO", "SQL_FLG_AUTOGENERADO", "SQL_NOM_SECUENCIAL") AS

SELECT VISTA.COD_PROYECTO AS COD_PROYECTO,

	       VISTA.COD_PROCESO AS COD_PROCESO,

		   VISTA.COD_ATRIBUTO AS COD_ATRIBUTO,

		   VISTA.NOM_ATRIBUTO AS NOM_ATRIBUTO,

		   VISTA.COD_CLASE AS COD_CLASE,

		   VISTA.NOM_CLASE AS NOM_CLASE,

		   VISTA.BPM_FLG_ENTRADA AS BPM_FLG_ENTRADA,

		   VISTA.BPM_OBJ_REFERENCIA AS BPM_OBJ_REFERENCIA,

		   VISTA.BPM_FLG_PIID AS BPM_FLG_PIID,

		   VISTA.WEB_FLG_REFERENCIA AS WEB_FLG_REFERENCIA,

		   VISTA.WEB_FLG_VALIDACION AS WEB_FLG_VALIDACION,

		   VISTA.WEB_MEN_VALIDACION AS WEB_MEN_VALIDACION,

		   VISTA.WEB_VAL_OMISION AS WEB_VAL_OMISION,

		   VISTA.WEB_REQUERIDO AS WEB_REQUERIDO,

		   VISTA.WEB_NOM_CAT_COMBO AS WEB_NOM_CAT_COMBO,

		   VISTA.SQL_FLG_AUTOGENERADO AS SQL_FLG_AUTOGENERADO,

		   VISTA.SQL_NOM_SECUENCIAL AS SQL_NOM_SECUENCIAL	

  FROM (SELECT PI.COD_PROYECTO AS COD_PROYECTO,

       PI.COD_PROCESO AS COD_PROCESO,

	   ATR.COD_ATRIBUTO AS COD_ATRIBUTO,

	   ATR.NOMBRE AS NOM_ATRIBUTO,

	   ATR.COD_CLASE AS COD_CLASE,

	   CLA.NOMBRE AS NOM_CLASE,

	   PI.BPM_FLG_ENTRADA AS BPM_FLG_ENTRADA,

	   PI.BPM_OBJ_REFERENCIA AS BPM_OBJ_REFERENCIA,

	   PI.BPM_FLG_PIID AS BPM_FLG_PIID,

	   PI.WEB_FLG_REFERENCIA AS WEB_FLG_REFERENCIA,

	   PI.WEB_FLG_VALIDACION AS WEB_FLG_VALIDACION,

	   PI.WEB_MEN_VALIDACION AS WEB_MEN_VALIDACION,

	   PI.WEB_VAL_OMISION AS WEB_VAL_OMISION,

	   PI.WEB_REQUERIDO AS WEB_REQUERIDO,

	   PI.WEB_NOM_CAT_COMBO AS WEB_NOM_CAT_COMBO,

	   PI.SQL_FLG_AUTOGENERADO AS SQL_FLG_AUTOGENERADO,

	   PI.SQL_NOM_SECUENCIAL AS SQL_NOM_SECUENCIAL	

  FROM BFP_CARTA_FIANZA.PROCESO_INICIO PI, 

       BFP_CARTA_FIANZA.ATRIBUTO ATR,

       BFP_CARTA_FIANZA.CLASE CLA

 WHERE PI.COD_ATRIBUTO = ATR.COD_ATRIBUTO

   AND ATR.COD_CLASE = CLA.COD_CLASE

   ORDER BY ATR.COD_CLASE, ATR.COD_ATRIBUTO ASC) AS VISTA;

CREATE FUNCTION FN_GETCODATRIBUTO(IN IN_CLASE VARCHAR(120), IN IN_ATRIBUTO VARCHAR(120))RETURNS INTEGER

	NO EXTERNAL ACTION

  

  F1: BEGIN ATOMIC

  DECLARE V_ATRIBUTO INTEGER;

	

  SET V_ATRIBUTO = (SELECT T1.COD_ATRIBUTO

                      FROM BFP_CARTA_FIANZA.ATRIBUTO T1, BFP_CARTA_FIANZA.CLASE T2

                     WHERE T1.COD_CLASE = T2.COD_CLASE

                       AND T2.NOMBRE    = IN_CLASE

                       AND T1.NOMBRE    = IN_ATRIBUTO);

     

	RETURN V_ATRIBUTO;

END;

CREATE FUNCTION FN_SIGCOD_ATRSQL()RETURNS INTEGER

	NO EXTERNAL ACTION

  

  F1: BEGIN ATOMIC

  DECLARE V_COD INTEGER;

	

  SET V_COD = (select nvl(max(cod_atr_obj_sql),0)+1 from BFP_CARTA_FIANZA.OBJ_ATR_SQL);

     

	RETURN V_COD;

END;

CREATE OR REPLACE PROCEDURE BFP_CARTA_FIANZA.SP_MANTENIMIENTO_MAE_ACUERDO_SERVICIO_LIS (

	IN  PI_CODIGO_UNIDAD_NEGOCIO VARCHAR(20),

	IN  PI_CODIGO_PRODUCTO VARCHAR(20),

	IN  PI_CODIGO_PROCESO VARCHAR(20),

	IN  PI_CODIGO_TAREA VARCHAR(70),

	IN  PI_PAGINADO_INDICE INTEGER,

	IN  PI_PAGINADO_CANTIDAD INTEGER,

	IN  PI_ORDEN VARCHAR(5),

	IN  PI_INDICADOR_CARGA_TOTAL INTEGER,

	OUT PO_TOTAL_SOLICITUDES INTEGER

)

RESULT SETS 1

LANGUAGE SQL

BEGIN

	DECLARE SQLCODE INT DEFAULT 0;

	DECLARE V_DINAMICO_c1 VARCHAR(4000);

	DECLARE V_DINAMICO_c2 VARCHAR(4000);

	DECLARE V_SQL_QUERY VARCHAR(4000);

	DECLARE V_SQL_PAGINACION VARCHAR(4000);

	DECLARE V_SQL_TOTAL VARCHAR(4000);

	DECLARE V_SQL_TOTAL_STR VARCHAR(5);

	DECLARE V_CANTIDAD VARCHAR(200);

	DECLARE c1 CURSOR WITH RETURN FOR V_DINAMICO_c1;

	DECLARE c2 CURSOR WITH RETURN FOR V_DINAMICO_c2;

	SET PO_TOTAL_SOLICITUDES = 0;



	IF (PI_CODIGO_UNIDAD_NEGOCIO IS NULL)  THEN SET PI_CODIGO_UNIDAD_NEGOCIO = 'NULL'; ELSE SET PI_CODIGO_UNIDAD_NEGOCIO = '''%'||PI_CODIGO_UNIDAD_NEGOCIO||'%'''; END IF;

	IF (PI_CODIGO_PRODUCTO IS NULL)  THEN SET PI_CODIGO_PRODUCTO = 'NULL'; ELSE SET PI_CODIGO_PRODUCTO = '''%'||PI_CODIGO_PRODUCTO||'%'''; END IF;

	IF (PI_CODIGO_PROCESO IS NULL)  THEN SET PI_CODIGO_PROCESO = 'NULL'; ELSE SET PI_CODIGO_PROCESO = '''%'||PI_CODIGO_PROCESO||'%'''; END IF;

	IF (PI_CODIGO_TAREA IS NULL)  THEN SET PI_CODIGO_TAREA = 'NULL'; ELSE SET PI_CODIGO_TAREA = '''%'||PI_CODIGO_TAREA||'%'''; END IF;

	IF (PI_ORDEN IS NULL) THEN SET PI_ORDEN = 'ASC'; END IF;



	SET V_SQL_QUERY = '

		SELECT 

			ROWNUMBER() OVER() AS NUMERO_FILA, 

			RESULTADO.*

		FROM (

			SELECT 

				CODIGO_UNIDAD_NEGOCIO,CODIGO_PRODUCTO,CODIGO_PROCESO,CODIGO_TAREA,DESCRIPCION,URL,VERSION,TIEMPO_ROJO,TIEMPO_AMARILLO,NAMESPACE,ESTADO

			FROM 

				BFP_CARTA_FIANZA.MAE_ACUERDO_SERVICIO

			WHERE 1 = 1

				AND ('||PI_CODIGO_UNIDAD_NEGOCIO||' IS NULL OR CODIGO_UNIDAD_NEGOCIO LIKE '||PI_CODIGO_UNIDAD_NEGOCIO||')

				AND ('||PI_CODIGO_PRODUCTO||' IS NULL OR CODIGO_PRODUCTO LIKE '||PI_CODIGO_PRODUCTO||')

				AND ('||PI_CODIGO_PROCESO||' IS NULL OR CODIGO_PROCESO LIKE '||PI_CODIGO_PROCESO||')

				AND ('||PI_CODIGO_TAREA||' IS NULL OR CODIGO_TAREA LIKE '||PI_CODIGO_TAREA||')

        	ORDER BY

				FECHA_CREACION '||PI_ORDEN||'

      	) AS RESULTADO

	';



	IF (PI_PAGINADO_CANTIDAD = 0) THEN 

		SET V_CANTIDAD = ''; 

	ELSE 

		SET V_CANTIDAD = 'WHERE RESULTADO_PAGINADO.NUMERO_FILA > '||(PI_PAGINADO_INDICE)||' AND RESULTADO_PAGINADO.NUMERO_FILA <= '||(PI_PAGINADO_INDICE+PI_PAGINADO_CANTIDAD);

	END IF;

	SET V_SQL_PAGINACION = 'SELECT * FROM ( '||V_SQL_QUERY||' ) AS RESULTADO_PAGINADO ' || V_CANTIDAD;

	PREPARE V_DINAMICO_c1 FROM V_SQL_PAGINACION;



	IF (PI_INDICADOR_CARGA_TOTAL = 1)  THEN

		SET V_SQL_TOTAL = 'SELECT COUNT(*) FROM ( '||V_SQL_QUERY||' )';

		PREPARE V_DINAMICO_c2 FROM V_SQL_TOTAL;

		OPEN c2;

		FETCH c2 into V_SQL_TOTAL_STR;

		CLOSE c2;

		SET PO_TOTAL_SOLICITUDES = INTEGER(V_SQL_TOTAL_STR);

	END IF;

	OPEN c1;

END;

GRANT CREATEIN ON SCHEMA "BFP_CARTA_FIANZA" TO  PUBLIC;
