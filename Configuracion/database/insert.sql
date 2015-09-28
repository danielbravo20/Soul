
INSERT INTO SEGURIDAD.USUARIO VALUES (1, '1', 'daniel', 'clave1', 'daniel bravo', 'daniel@correo.com');
INSERT INTO SEGURIDAD.USUARIO VALUES (2, '1', 'edwin', 'clave1', 'edwin rebaza', 'edwin@correo.com');
INSERT INTO SEGURIDAD.USUARIO VALUES (3, '1', 'david', 'clave1', 'david huertas', 'david@correo.com');
INSERT INTO SEGURIDAD.USUARIO VALUES (4, '1', 'manuel', 'clave1', 'manuel flores', 'manuel@correo.com');

INSERT INTO SEGURIDAD.ROL VALUES (1, 'Administrador');
INSERT INTO SEGURIDAD.ROL VALUES (2, 'Analista');
INSERT INTO SEGURIDAD.ROL VALUES (3, 'Operador');
INSERT INTO SEGURIDAD.ROL VALUES (4, 'Asistente');

INSERT INTO SEGURIDAD.USU_ROL VALUES (1, 1);
INSERT INTO SEGURIDAD.USU_ROL VALUES (2, 2);
INSERT INTO SEGURIDAD.USU_ROL VALUES (3, 3);
INSERT INTO SEGURIDAD.USU_ROL VALUES (4, 4);

INSERT INTO SEGURIDAD.MODULO VALUES (1, 'Tareas', 1, null, 'portal/modulo/tareas.html');
INSERT INTO SEGURIDAD.MODULO VALUES (2, 'Solicitudes', 2, null, 'portal/modulo/Solicitudes.html');
INSERT INTO SEGURIDAD.MODULO VALUES (3, 'Seguimiento', 3, null, 'portal/modulo/seguimiento.html');
INSERT INTO SEGURIDAD.MODULO VALUES (4, 'Reportes', 4, null, 'portal/modulo/reportes.html');
INSERT INTO SEGURIDAD.MODULO VALUES (5, 'Mantenimientos', 5, null, 'portal/modulo/mantenimientos.html');

INSERT INTO SEGURIDAD.MODULO_ROL VALUES (1, 1);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES (1, 2);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES (1, 3);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES (1, 4);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES (2, 1);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES (2, 2);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES (3, 1);
INSERT INTO SEGURIDAD.MODULO_ROL VALUES (4, 1);

INSERT INTO PROCESO.PROCESO_PLANTILLA VALUES (1, '1', 'EMISION DE CARTA FIANZA', 'emisionCartaFianzaV1', 'v1.0.0', '2010-01-01 00:00:00', false);
INSERT INTO PROCESO.PROCESO_PLANTILLA VALUES (2, '1', 'RENOVACION DE CARTA FIANZA', 'renovacionCartaFianzaV1', 'v1.0.0', '2010-01-01 00:00:00', false);
INSERT INTO PROCESO.PROCESO_PLANTILLA VALUES (3, '1', 'MODIFICACION DE CARTA FIANZA', 'modificacionCartaFianzaV1', 'v1.0.0', '2010-01-01 00:00:00', false);
INSERT INTO PROCESO.PROCESO_PLANTILLA VALUES (4, '1', 'CAMBIO DE ESTADO DE CARTA FIANZA', 'cambioEstadoCartaFianzaV1', 'v1.0.0', '2010-01-01 00:00:00', false);
INSERT INTO PROCESO.PROCESO_PLANTILLA VALUES (5, '1', 'CANCELACION DE CARTA FIANZA', 'cancelacionCartaFianzaV1', 'v1.0.0', '2010-01-01 00:00:00', false);
INSERT INTO PROCESO.PROCESO_PLANTILLA VALUES (6, '1', 'EJECUCION DE CARTA FIANZA', 'ejecucionCartaFianzaV1', 'v1.0.0', '2010-01-01 00:00:00', false);

INSERT INTO PROCESO.POTENCIAL_INICIADOR VALUES (1, 1);
INSERT INTO PROCESO.POTENCIAL_INICIADOR VALUES (2, 1);
INSERT INTO PROCESO.POTENCIAL_INICIADOR VALUES (3, 1);
INSERT INTO PROCESO.POTENCIAL_INICIADOR VALUES (4, 1);
INSERT INTO PROCESO.POTENCIAL_INICIADOR VALUES (5, 1);
INSERT INTO PROCESO.POTENCIAL_INICIADOR VALUES (6, 1);
INSERT INTO PROCESO.POTENCIAL_INICIADOR VALUES (1, 2);
INSERT INTO PROCESO.POTENCIAL_INICIADOR VALUES (1, 3);


SELECT * FROM SEGURIDAD.USUARIO;
SELECT * FROM SEGURIDAD.ROL;
SELECT * FROM SEGURIDAD.USU_ROL;
SELECT * FROM PROCESO.PROCESO_PLANTILLA;

