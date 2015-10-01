
CREATE SCHEMA SEGURIDAD AUTHORIZATION postgres;
CREATE SCHEMA PROCESO AUTHORIZATION postgres;

CREATE SEQUENCE PROCESO.SEQ_CODIGO_PROCESO START 101;
CREATE SEQUENCE PROCESO.SEQ_CODIGO_TAREA START 10001;

-- tables
-- Table: ADMINISTRADOR_PROCESO
CREATE TABLE PROCESO.ADMINISTRADOR_PROCESO (
    CODIGO_PROCESO_PLANTILLA bigint  NOT NULL,
    CODIGO_ROL bigint  NOT NULL,
    CONSTRAINT ADMINISTRADOR_PROCESO_pk PRIMARY KEY (CODIGO_PROCESO_PLANTILLA,CODIGO_ROL)
);



-- Table: ADMINISTRADOR_TAREA
CREATE TABLE PROCESO.ADMINISTRADOR_TAREA (
    CODIGO_TAREA_PLANTILLA bigint  NOT NULL,
    CODIGO_ROL bigint  NOT NULL,
    CONSTRAINT ADMINISTRADOR_TAREA_pk PRIMARY KEY (CODIGO_TAREA_PLANTILLA,CODIGO_ROL)
);



-- Table: EDITOR_TAREA
CREATE TABLE PROCESO.EDITOR_TAREA (
    CODIGO_TAREA_PLANTILLA bigint  NOT NULL,
    CODIGO_ROL bigint  NOT NULL,
    CONSTRAINT EDITOR_TAREA_pk PRIMARY KEY (CODIGO_TAREA_PLANTILLA,CODIGO_ROL)
);



-- Table: MODULO
CREATE TABLE SEGURIDAD.MODULO (
    CODIGO_MODULO int  NOT NULL,
    NOMBRE varchar(60)  NOT NULL,
    ORDEN int  NOT NULL,
    DESCRIPCION varchar(250)  NULL,
    URL varchar(120)  NOT NULL,
    CONSTRAINT MODULO_pk PRIMARY KEY (CODIGO_MODULO)
);



-- Table: MODULO_ROL
CREATE TABLE SEGURIDAD.MODULO_ROL (
    CODIGO_ROL bigint  NOT NULL,
    CODIGO_MODULO int  NOT NULL,
    CONSTRAINT MODULO_ROL_pk PRIMARY KEY (CODIGO_ROL,CODIGO_MODULO)
);



-- Table: POTENCIAL_DUENO
CREATE TABLE PROCESO.POTENCIAL_DUENO (
    CODIGO_TAREA_PLANTILLA bigint  NOT NULL,
    CODIGO_ROL bigint  NOT NULL,
    CONSTRAINT POTENCIAL_DUENO_pk PRIMARY KEY (CODIGO_TAREA_PLANTILLA,CODIGO_ROL)
);



-- Table: POTENCIAL_INICIADOR
CREATE TABLE PROCESO.POTENCIAL_INICIADOR (
    CODIGO_PROCESO_PLANTILLA bigint  NOT NULL,
    ROL_CODIGO_ROL bigint  NOT NULL,
    CONSTRAINT POTENCIAL_INICIADOR_pk PRIMARY KEY (CODIGO_PROCESO_PLANTILLA,ROL_CODIGO_ROL)
);



-- Table: PROCESO
CREATE TABLE PROCESO.PROCESO (
    CODIGO_PROCESO bigint  NOT NULL,
    CODIGO_PROCESO_PLANTILLA bigint  NOT NULL,
    ESTADO char(1)  NOT NULL,
    NOMBRE varchar(120)  NOT NULL,
    ALEAS varchar(100)  NOT NULL,
    VERSION varchar(12)  NOT NULL,
    FECHA_CREACION timestamp  NOT NULL,
    FECHA_TERMINO timestamp  NULL,
    CODIGO_USUARIO_CREACION bigint  NOT NULL,
    CONSTRAINT PROCESO_pk PRIMARY KEY (CODIGO_PROCESO)
);



-- Table: PROCESO_PLANTILLA
CREATE TABLE PROCESO.PROCESO_PLANTILLA (
    CODIGO_PROCESO_PLANTILLA bigint  NOT NULL,
    ESTADO char(1)  NOT NULL,
    NOMBRE varchar(120)  NOT NULL,
    ALEAS varchar(100)  NOT NULL,
    VERSION varchar(12)  NOT NULL,
    FECHA_VALIDEZ timestamp  NOT NULL,
    FLAG_TODOS_INICIAN boolean  NOT NULL,
    CONSTRAINT PROCESO_PLANTILLA_pk PRIMARY KEY (CODIGO_PROCESO_PLANTILLA)
);



-- Table: ROL
CREATE TABLE SEGURIDAD.ROL (
    CODIGO_ROL bigint  NOT NULL,
    NOMBRE varchar(120)  NOT NULL,
    CONSTRAINT ROL_pk PRIMARY KEY (CODIGO_ROL)
);



-- Table: TAREA
CREATE TABLE PROCESO.TAREA (
    CODIGO_TAREA bigint  NOT NULL,
    CODIGO_PROCESO bigint  NOT NULL,
    CODIGO_TAREA_PLANTILLA bigint  NOT NULL,
    ESTADO char(1)  NOT NULL,
    NOMBRE varchar(120)  NOT NULL,
    ALEAS varchar(100)  NOT NULL,
    VERSION varchar(12)  NOT NULL,
    PRIORIDAD int  NOT NULL,
    FECHA_CREACION timestamp  NOT NULL,
    FECHA_RECLAMO timestamp  NULL,
    FECHA_TERMINO timestamp  NULL,
    FECHA_ULTIMA_MODIFICACION timestamp  NOT NULL,
    CODIGO_DUENO bigint  NULL,
    CONSTRAINT TAREA_pk PRIMARY KEY (CODIGO_TAREA)
);



-- Table: TAREA_PLANTILLA
CREATE TABLE PROCESO.TAREA_PLANTILLA (
    CODIGO_TAREA_PLANTILLA bigint  NOT NULL,
    CODIGO_PROCESO_PLANTILLA bigint  NOT NULL,
    ESTADO char(1)  NOT NULL,
    NOMBRE varchar(120)  NOT NULL,
    ALEAS varchar(100)  NOT NULL,
    VERSION varchar(12)  NOT NULL,
    PRIORIDAD int  NOT NULL,
    ORDEN int  NOT NULL,
    CONSTRAINT TAREA_PLANTILLA_pk PRIMARY KEY (CODIGO_TAREA_PLANTILLA)
);



-- Table: USUARIO
CREATE TABLE SEGURIDAD.USUARIO (
    CODIGO_USUARIO bigint  NOT NULL,
    ESTADO char(1)  NOT NULL,
    USUARIO varchar(40)  NOT NULL,
    CLAVE varchar(20)  NOT NULL,
    NOMBRE_COMPLETO varchar(120)  NOT NULL,
    CORREO varchar(120)  NULL,
    CONSTRAINT USUARIO_pk PRIMARY KEY (CODIGO_USUARIO)
);



-- Table: USU_ROL
CREATE TABLE SEGURIDAD.USU_ROL (
    CODIGO_USUARIO bigint  NOT NULL,
    CODIGO_ROL bigint  NOT NULL,
    CONSTRAINT USU_ROL_pk PRIMARY KEY (CODIGO_USUARIO,CODIGO_ROL)
);







-- foreign keys
-- Reference:  EDITOR_TAREA_ROL (table: PROCESO.EDITOR_TAREA)


ALTER TABLE PROCESO.EDITOR_TAREA ADD CONSTRAINT EDITOR_TAREA_ROL 
    FOREIGN KEY (CODIGO_ROL)
    REFERENCES SEGURIDAD.ROL (CODIGO_ROL)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  EDITOR_TAREA_TAR_PLANTILLA (table: PROCESO.EDITOR_TAREA)


ALTER TABLE PROCESO.EDITOR_TAREA ADD CONSTRAINT EDITOR_TAREA_TAR_PLANTILLA 
    FOREIGN KEY (CODIGO_TAREA_PLANTILLA)
    REFERENCES PROCESO.TAREA_PLANTILLA (CODIGO_TAREA_PLANTILLA)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  MOD_ROL_MODULO (table: SEGURIDAD.MODULO_ROL)


ALTER TABLE SEGURIDAD.MODULO_ROL ADD CONSTRAINT MOD_ROL_MODULO 
    FOREIGN KEY (CODIGO_MODULO)
    REFERENCES SEGURIDAD.MODULO (CODIGO_MODULO)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  MOD_ROL_ROL (table: SEGURIDAD.MODULO_ROL)


ALTER TABLE SEGURIDAD.MODULO_ROL ADD CONSTRAINT MOD_ROL_ROL 
    FOREIGN KEY (CODIGO_ROL)
    REFERENCES SEGURIDAD.ROL (CODIGO_ROL)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  POTENCIAL_DUENO_PROCESO_PLANTILLA (table: PROCESO.POTENCIAL_INICIADOR)


ALTER TABLE PROCESO.POTENCIAL_INICIADOR ADD CONSTRAINT POTENCIAL_DUENO_PROCESO_PLANTILLA 
    FOREIGN KEY (CODIGO_PROCESO_PLANTILLA)
    REFERENCES PROCESO.PROCESO_PLANTILLA (CODIGO_PROCESO_PLANTILLA)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  POTENCIAL_DUENO_ROL (table: PROCESO.POTENCIAL_DUENO)


ALTER TABLE PROCESO.POTENCIAL_DUENO ADD CONSTRAINT POTENCIAL_DUENO_ROL 
    FOREIGN KEY (CODIGO_ROL)
    REFERENCES SEGURIDAD.ROL (CODIGO_ROL)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  POTENCIAL_DUENO_TAR_PLANTILLA (table: PROCESO.POTENCIAL_DUENO)


ALTER TABLE PROCESO.POTENCIAL_DUENO ADD CONSTRAINT POTENCIAL_DUENO_TAR_PLANTILLA 
    FOREIGN KEY (CODIGO_TAREA_PLANTILLA)
    REFERENCES PROCESO.TAREA_PLANTILLA (CODIGO_TAREA_PLANTILLA)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  POTENCIAL_INICIADOR_ROL (table: PROCESO.POTENCIAL_INICIADOR)


ALTER TABLE PROCESO.POTENCIAL_INICIADOR ADD CONSTRAINT POTENCIAL_INICIADOR_ROL 
    FOREIGN KEY (ROL_CODIGO_ROL)
    REFERENCES SEGURIDAD.ROL (CODIGO_ROL)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  PRO_INSTANCIA_PRO_PLANTILLA (table: PROCESO.PROCESO)


ALTER TABLE PROCESO.PROCESO ADD CONSTRAINT PRO_INSTANCIA_PRO_PLANTILLA 
    FOREIGN KEY (CODIGO_PROCESO_PLANTILLA)
    REFERENCES PROCESO.PROCESO_PLANTILLA (CODIGO_PROCESO_PLANTILLA)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  PRO_INSTANCIA_USUARIO (table: PROCESO.PROCESO)


ALTER TABLE PROCESO.PROCESO ADD CONSTRAINT PRO_INSTANCIA_USUARIO 
    FOREIGN KEY (CODIGO_USUARIO_CREACION)
    REFERENCES SEGURIDAD.USUARIO (CODIGO_USUARIO)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  PRO_PLA_ROL_PRO_PLANTILLA (table: PROCESO.ADMINISTRADOR_PROCESO)


ALTER TABLE PROCESO.ADMINISTRADOR_PROCESO ADD CONSTRAINT PRO_PLA_ROL_PRO_PLANTILLA 
    FOREIGN KEY (CODIGO_PROCESO_PLANTILLA)
    REFERENCES PROCESO.PROCESO_PLANTILLA (CODIGO_PROCESO_PLANTILLA)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  PRO_PLA_ROL_ROL (table: PROCESO.ADMINISTRADOR_PROCESO)


ALTER TABLE PROCESO.ADMINISTRADOR_PROCESO ADD CONSTRAINT PRO_PLA_ROL_ROL 
    FOREIGN KEY (CODIGO_ROL)
    REFERENCES SEGURIDAD.ROL (CODIGO_ROL)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  TAR_INSTANCIA_PRO_INSTANCIA (table: PROCESO.TAREA)


ALTER TABLE PROCESO.TAREA ADD CONSTRAINT TAR_INSTANCIA_PRO_INSTANCIA 
    FOREIGN KEY (CODIGO_PROCESO)
    REFERENCES PROCESO.PROCESO (CODIGO_PROCESO)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  TAR_INSTANCIA_TAR_PLANTILLA (table: PROCESO.TAREA)


ALTER TABLE PROCESO.TAREA ADD CONSTRAINT TAR_INSTANCIA_TAR_PLANTILLA 
    FOREIGN KEY (CODIGO_TAREA_PLANTILLA)
    REFERENCES PROCESO.TAREA_PLANTILLA (CODIGO_TAREA_PLANTILLA)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  TAR_INSTANCIA_USUARIO (table: PROCESO.TAREA)


ALTER TABLE PROCESO.TAREA ADD CONSTRAINT TAR_INSTANCIA_USUARIO 
    FOREIGN KEY (CODIGO_DUENO)
    REFERENCES SEGURIDAD.USUARIO (CODIGO_USUARIO)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  TAR_PLANTILLA_PRO_PLANTILLA (table: PROCESO.TAREA_PLANTILLA)


ALTER TABLE PROCESO.TAREA_PLANTILLA ADD CONSTRAINT TAR_PLANTILLA_PRO_PLANTILLA 
    FOREIGN KEY (CODIGO_PROCESO_PLANTILLA)
    REFERENCES PROCESO.PROCESO_PLANTILLA (CODIGO_PROCESO_PLANTILLA)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  TAR_PLA_ROL_ROL (table: PROCESO.ADMINISTRADOR_TAREA)


ALTER TABLE PROCESO.ADMINISTRADOR_TAREA ADD CONSTRAINT TAR_PLA_ROL_ROL 
    FOREIGN KEY (CODIGO_ROL)
    REFERENCES SEGURIDAD.ROL (CODIGO_ROL)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  TAR_PLA_ROL_TAR_PLANTILLA (table: PROCESO.ADMINISTRADOR_TAREA)


ALTER TABLE PROCESO.ADMINISTRADOR_TAREA ADD CONSTRAINT TAR_PLA_ROL_TAR_PLANTILLA 
    FOREIGN KEY (CODIGO_TAREA_PLANTILLA)
    REFERENCES PROCESO.TAREA_PLANTILLA (CODIGO_TAREA_PLANTILLA)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  USU_ROL_ROL (table: SEGURIDAD.USU_ROL)


ALTER TABLE SEGURIDAD.USU_ROL ADD CONSTRAINT USU_ROL_ROL 
    FOREIGN KEY (CODIGO_ROL)
    REFERENCES SEGURIDAD.ROL (CODIGO_ROL)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  USU_ROL_USUARIO (table: SEGURIDAD.USU_ROL)


ALTER TABLE SEGURIDAD.USU_ROL ADD CONSTRAINT USU_ROL_USUARIO 
    FOREIGN KEY (CODIGO_USUARIO)
    REFERENCES SEGURIDAD.USUARIO (CODIGO_USUARIO)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;




-- sequences
-- Sequence: SEQ_PROCESO


CREATE SEQUENCE PROCESO.SEQ_PROCESO
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 10000 
      
      NO CYCLE
      
;


-- Sequence: SEQ_TAREA


CREATE SEQUENCE PROCESO.SEQ_TAREA
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 10000 
      
      NO CYCLE
      
;


-- Sequence: SEQ_USUARIO


CREATE SEQUENCE SEGURIDAD.SEQ_USUARIO
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1000 
      
      NO CYCLE
      
;






-- End of file.
