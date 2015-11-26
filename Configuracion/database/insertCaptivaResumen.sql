INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('david', 'a4a97ffc170ec7ab32b85b2129c69c50', 'David', 'PRO_MAN', 'David');
INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('daniel','a4a97ffc170ec7ab32b85b2129c69c50', 'Daniel', 'PRO_MAN', 'Daniel');

INSERT INTO soul.PROYECTO VALUES (1, 'Workflow de Carta Fianza', 'CartaFianza', 'pe.com.cartaFianza');
 
INSERT INTO soul.EQUIPO(cod_proyecto, cod_usuario, es_responsable, carpeta_destino_workspace, carpeta_destino_parcial) VALUES (1, 'david', '1', 'D:\oscar\Compartido\generadoData', 'D:\oscar\Compartido\generadoData');
INSERT INTO soul.EQUIPO(cod_proyecto, cod_usuario, es_responsable, carpeta_destino_workspace, carpeta_destino_parcial) VALUES (1, 'daniel', '1', 'E:\JBOSS\git\', 'E:\CF_Temporal\');

INSERT INTO soul.Rol (descripcion,estado,cod_rol,cod_proyecto) VALUES ('Analista','1','Analista','1');

INSERT INTO soul.TABLA (cod_proyecto,nombre,esquema,cod_tabla) VALUES ('1','solicitud','bfp_carta_fianza','1');

INSERT INTO soul.CLASE (nivel,cod_proyecto,cod_tabla,cod_clase,nombre) VALUES ('1','1','1','1','Solicitud');

INSERT INTO soul.ATRIBUTO (tipo,nombre,cod_atributo,cod_clase,etiqueta) VALUES ('Long','codigoSolicitud','1','1','');
INSERT INTO soul.ATRIBUTO (tipo,nombre,cod_atributo,cod_clase,etiqueta) VALUES ('String','evento','2','1','');
INSERT INTO soul.ATRIBUTO (tipo,nombre,cod_atributo,cod_clase,etiqueta) VALUES ('java.math.BigDecimal','monto','3','1','');
INSERT INTO soul.ATRIBUTO (tipo,nombre,cod_atributo,cod_clase,etiqueta) VALUES ('java.sql.Date','vigencia','4','1','');

INSERT INTO soul.ATRIBUTO_SQL (obligatorio,cod_tabla,campo,sequencial,cod_atributo,tipo,PK) VALUES ('1','1','cod_solicitud','bfp_carta_fianza.seq_cod_solicitud','1','BIGINT','1');
INSERT INTO soul.ATRIBUTO_SQL (cod_tabla,longitud,campo,cod_atributo,tipo) VALUES ('1','3','evento','2','VARCHAR');
INSERT INTO soul.ATRIBUTO_SQL (cod_tabla,longitud,campo,cod_atributo,tipo,precision) VALUES ('1','12','monto','3','DECIMAL','3');
INSERT INTO soul.ATRIBUTO_SQL (cod_tabla,campo,cod_atributo,tipo) VALUES ('1','vigencia','4','DATE');

INSERT INTO soul.CONSULTA(COD_CONSULTA, COD_PROYECTO, NOMBRE) VALUES (1, 1, 'EMISION - RESUMEN');

INSERT INTO soul.CONSULTA_TABLA (FLG_UNO_MUCHOS,COD_TAB_PADRE,cod_tabla,FK,cod_consulta) VALUES ('0','1','1','0','1');

INSERT INTO soul.CONSULTA_ATRIBUTO (cod_tabla,cod_atributo,flg_condicion,cod_consulta) VALUES ('1','1','0','1');
INSERT INTO soul.CONSULTA_ATRIBUTO (cod_atributo,cod_consulta,cod_tabla,flg_condicion) VALUES ('2','1','1','0');

INSERT INTO soul.CONSULTA(COD_CONSULTA, COD_PROYECTO, NOMBRE) VALUES (2, 1, 'EMISION - DETALLE');

INSERT INTO soul.CONSULTA_TABLA (cod_tabla,cod_consulta,FLG_UNO_MUCHOS,COD_TAB_PADRE,FK) VALUES ('1','2','0','1','0');

INSERT INTO soul.CONSULTA_ATRIBUTO (flg_condicion,cod_tabla,cod_atributo,cod_consulta) VALUES ('0','1','3','2');
INSERT INTO soul.CONSULTA_ATRIBUTO (flg_condicion,cod_tabla,cod_atributo,cod_consulta) VALUES ('0','1','1','2');
INSERT INTO soul.CONSULTA_ATRIBUTO (cod_atributo,cod_consulta,cod_tabla,flg_condicion) VALUES ('2','2','1','0');
INSERT INTO soul.CONSULTA_ATRIBUTO (cod_atributo,cod_tabla,flg_condicion,cod_consulta) VALUES ('4','1','0','2');

INSERT INTO soul.PROCESO (jav_datasource,cod_proceso,nombre,jav_clase,cod_proyecto,cod_con_resumen,cod_con_detalle) VALUES ('java:jboss/soulDS','1','Emision','Emision','1','1','2');

INSERT INTO soul.proceso_rol_potencial (cod_proceso,cod_rol) VALUES ('1','Analista');

INSERT INTO soul.proceso_inicio_sub_seccion (nombre,cod_proceso,cod_sub_seccion) VALUES ('Sub Seccion Nro 1','1','1');
INSERT INTO soul.proceso_inicio (cod_proceso_inicio,cod_sub_seccion,web_mensaje_validacion,web_tipo,cod_atributo,cod_proceso,web_etiqueta,web_tipo_campo,web_requerido) VALUES ('1','1','Por favor ingrese el monto','java.math.BigDecimal','3','1','Monto:','C','1');
