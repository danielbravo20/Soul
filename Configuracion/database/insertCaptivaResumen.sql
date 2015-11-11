INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('david', 'a4a97ffc170ec7ab32b85b2129c69c50', 'David', 'PRO_MAN', 'David');
INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('daniel','a4a97ffc170ec7ab32b85b2129c69c50', 'Daniel', 'PRO_MAN', 'Daniel');
INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('edwin','a4a97ffc170ec7ab32b85b2129c69c50', 'edwin', 'PRO_MAN', 'Daniel');
INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('LUIBRA','a4a97ffc170ec7ab32b85b2129c69c50', 'Daniel', 'PRO_MAN', 'Daniel');

INSERT INTO soul.PROYECTO VALUES (1, 'Workflow de Carta Fianza', 'CartaFianza', 'pe.com.cartaFianza');
 
INSERT INTO soul.EQUIPO(cod_proyecto, cod_usuario, es_responsable, carpeta_destino_workspace, carpeta_destino_parcial) VALUES (1, 'david', '1', 'D:\oscar\Compartido\generadoData', 'D:\oscar\Compartido\generadoData');
INSERT INTO soul.EQUIPO(cod_proyecto, cod_usuario, es_responsable, carpeta_destino_workspace, carpeta_destino_parcial) VALUES (1, 'daniel', '1', 'E:\JBOSS\git\', 'E:\CF_Temporal\');
INSERT INTO soul.EQUIPO(cod_proyecto, cod_usuario, es_responsable, carpeta_destino_workspace, carpeta_destino_parcial) VALUES (1, 'LUIBRA', '1', 'D:\IBM\git\Soul\', 'D:\IBM\jbdevstudio\soul\sql\');

INSERT INTO soul.CONSULTA(COD_CONSULTA, SQL_ALE_STO_PROCEDURE, JAV_PAQUETE, JAV_INTERFACE, COD_PROYECTO, NOMBRE) VALUES (1, 'RES_CF_EMISION', 'pe.com.financiero.bpm.cf.dao', 'ResumenEmisionBEDao', 1, 'EMISION - RESUMEN');
INSERT INTO soul.CONSULTA(COD_CONSULTA, SQL_ALE_STO_PROCEDURE, JAV_PAQUETE, JAV_INTERFACE, COD_PROYECTO, NOMBRE) VALUES (2, 'DET_CF_EMISION', 'pe.com.financiero.bpm.cf.dao', 'DetalleEmisionBEDao', 1, 'EMISION - DETALLE');
