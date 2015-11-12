INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('david', 'a4a97ffc170ec7ab32b85b2129c69c50', 'David', 'PRO_MAN', 'David');
INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('daniel','a4a97ffc170ec7ab32b85b2129c69c50', 'Daniel', 'PRO_MAN', 'Daniel');
INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('edwin','a4a97ffc170ec7ab32b85b2129c69c50', 'edwin', 'PRO_MAN', 'Daniel');
INSERT INTO soul.USUARIO(cod_usuario, clave, nombre, perfil, descripcion) VALUES ('LUIBRA','a4a97ffc170ec7ab32b85b2129c69c50', 'Daniel', 'PRO_MAN', 'Daniel');

INSERT INTO soul.PROYECTO (nombre,cod_proyecto,paquete,proyecto) VALUES ('CF','1','pe.com.finan.sd','WK')

INSERT INTO soul.EQUIPO (cod_usuario,carpeta_destino_workspace,es_Responsable,carpeta_destino_parcial,cod_proyecto) VALUES ('david','','0','','1')
INSERT INTO soul.EQUIPO (cod_usuario,carpeta_destino_parcial,carpeta_destino_workspace,es_Responsable,cod_proyecto) VALUES ('daniel','D:\IBM\LIB_CF','D:\IBM\LIB_CF','1','1')
INSERT INTO soul.EQUIPO (cod_usuario,carpeta_destino_parcial,carpeta_destino_workspace,cod_proyecto,es_Responsable) VALUES ('edwin','','','1','0')