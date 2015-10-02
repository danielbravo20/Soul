-- Cambio para soportar clave encriptada
alter table seguridad.usuario
 alter column clave set data type character varying(70);
 
-- clave encriptada
-- clave1 = 9010e72389a80487d473017425c6ec7951068abed82a4df32459c91f0e45d2ea
 
--update seguridad.usuario set clave = '9010e72389a80487d473017425c6ec7951068abed82a4df32459c91f0e45d2ea';