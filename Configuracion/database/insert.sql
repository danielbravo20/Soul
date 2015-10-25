
INSERT INTO SEGURIDAD.USUARIO VALUES ('daniel', '1', 'a4a97ffc170ec7ab32b85b2129c69c50', 'daniel bravo', 'daniel@correo.com');
INSERT INTO SEGURIDAD.USUARIO VALUES ('carmen', '1', 'a4a97ffc170ec7ab32b85b2129c69c50', 'edwin rebaza', 'edwin@correo.com');
INSERT INTO SEGURIDAD.USUARIO VALUES ('marco', '1', 'a4a97ffc170ec7ab32b85b2129c69c50', 'david huertas', 'david@correo.com');
INSERT INTO SEGURIDAD.USUARIO VALUES ('manuel', '1', 'a4a97ffc170ec7ab32b85b2129c69c50', 'manuel flores', 'manuel@correo.com');
INSERT INTO SEGURIDAD.USUARIO VALUES ('maria', '1', 'a4a97ffc170ec7ab32b85b2129c69c50', 'manuel flores', 'manuel@correo.com');

INSERT INTO SEGURIDAD.ROL VALUES ('Administrador', 'Administrador');
INSERT INTO SEGURIDAD.ROL VALUES ('Analista', 'Analista');
INSERT INTO SEGURIDAD.ROL VALUES ('Operador', 'Operador');
INSERT INTO SEGURIDAD.ROL VALUES ('Asistente', 'Asistente');

INSERT INTO SEGURIDAD.USUARIO_ROL VALUES ('daniel', 'Administrador');
INSERT INTO SEGURIDAD.USUARIO_ROL VALUES ('carmen', 'Analista');
INSERT INTO SEGURIDAD.USUARIO_ROL VALUES ('maria', 'Analista');
INSERT INTO SEGURIDAD.USUARIO_ROL VALUES ('marco', 'Operador');
INSERT INTO SEGURIDAD.USUARIO_ROL VALUES ('manuel', 'Asistente');

INSERT INTO SEGURIDAD.MODULO VALUES (1, 'Tareas', 1, null, 'portal/modulo/tareas.html');
INSERT INTO SEGURIDAD.MODULO VALUES (2, 'Solicitudes', 2, null, 'portal/modulo/Solicitudes.html');
INSERT INTO SEGURIDAD.MODULO VALUES (3, 'Seguimiento', 3, null, 'portal/modulo/seguimiento.html');
INSERT INTO SEGURIDAD.MODULO VALUES (4, 'Reportes', 4, null, 'portal/modulo/reportes.html');
INSERT INTO SEGURIDAD.MODULO VALUES (5, 'Mantenimientos', 5, null, 'portal/modulo/mantenimientos.html');

INSERT INTO SEGURIDAD.MODULO_ROL VALUES ('Administrador', 1);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES ('Administrador', 2);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES ('Administrador', 3);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES ('Administrador', 4);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES ('Analista', 1);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES ('Analista', 2);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES ('Operador', 1);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES ('Asistente', 1);
