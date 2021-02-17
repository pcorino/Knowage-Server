-- 22/12/2020 Marco Balestri
-- CATALOG FUNCTION TABLES UPDATE

-- START

-- CLEAN old tables
DELETE FROM SBI_CATALOG_FUNCTION;
DELETE FROM SBI_FUNCTION_INPUT_VARIABLE;

-- DROP old tables
DROP TABLE SBI_FUNCTION_OUTPUT;
DROP TABLE SBI_FUNCTION_INPUT_DATASET;
DROP TABLE SBI_FUNCTION_INPUT_FILE;

-- ------------------------- TABLE sbi_catalog_function -------------------------
ALTER TABLE SBI_CATALOG_FUNCTION ADD (BENCHMARKS varchar2(4000), FAMILY varchar2(30), ONLINE_SCRIPT varchar2(4000), OFFLINE_SCRIPT_TRAIN varchar2(4000), OFFLINE_SCRIPT_USE varchar2(4000));
ALTER TABLE SBI_CATALOG_FUNCTION DROP (SCRIPT, URL, REMOTE);

-- ----------------------- TABLE sbi_function_input_column -----------------------
CREATE TABLE SBI_FUNCTION_INPUT_COLUMN (
	FUNCTION_ID 			INTEGER NOT NULL,
	COL_NAME				VARCHAR(100) NOT NULL,
	COL_TYPE				VARCHAR(100) NOT NULL,
	USER_IN 				VARCHAR(100) NOT NULL,
	USER_UP 				VARCHAR(100) DEFAULT NULL,
	USER_DE 				VARCHAR(100) DEFAULT NULL,
	TIME_IN 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	TIME_UP 				TIMESTAMP DEFAULT NULL,
	TIME_DE 				TIMESTAMP DEFAULT NULL,
	SBI_VERSION_IN 		VARCHAR(10) DEFAULT NULL,
	SBI_VERSION_UP 		VARCHAR(10) DEFAULT NULL,
	SBI_VERSION_DE 		VARCHAR(10) DEFAULT NULL,
	ORGANIZATION 			VARCHAR(20) DEFAULT NULL,
	PRIMARY KEY (FUNCTION_ID, COL_NAME)
);

ALTER TABLE SBI_FUNCTION_INPUT_COLUMN 		ADD CONSTRAINT FK_FUNCTION_INPUT_COLUMNS_1  	FOREIGN KEY (FUNCTION_ID) 			REFERENCES SBI_CATALOG_FUNCTION(FUNCTION_ID)   ON DELETE CASCADE;

-- ---------------------- TABLE sbi_function_input_variable ---------------------
ALTER TABLE SBI_FUNCTION_INPUT_VARIABLE ADD VAR_TYPE varchar2(100);

-- ---------------------- TABLE sbi_function_output_column -----------------------
CREATE TABLE SBI_FUNCTION_OUTPUT_COLUMN (
	FUNCTION_ID			INTEGER NOT NULL,
	COL_NAME				VARCHAR(100) NOT NULL,
	COL_TYPE				VARCHAR(100) NOT NULL,
	COL_FIELD_TYPE			VARCHAR(100) NOT NULL,
	USER_IN 				VARCHAR(100) NOT NULL,
	USER_UP 				VARCHAR(100) DEFAULT NULL,
	USER_DE 				VARCHAR(100) DEFAULT NULL,
	TIME_IN 				TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	TIME_UP 				TIMESTAMP DEFAULT NULL,
	TIME_DE 				TIMESTAMP DEFAULT NULL,
	SBI_VERSION_IN 		VARCHAR(10) DEFAULT NULL,
	SBI_VERSION_UP 		VARCHAR(10) DEFAULT NULL,
	SBI_VERSION_DE 		VARCHAR(10) DEFAULT NULL,
	ORGANIZATION 			VARCHAR(20) DEFAULT NULL,
	PRIMARY KEY (FUNCTION_ID,COL_NAME)
);

ALTER TABLE SBI_FUNCTION_OUTPUT_COLUMN 		ADD CONSTRAINT FK_FUNCTION_OUTPUT_COLUMNS_1  	FOREIGN KEY (FUNCTION_ID) 			REFERENCES SBI_CATALOG_FUNCTION(FUNCTION_ID)   ON DELETE CASCADE;

-- --------------------------- TABLE sbi_obj_function ---------------------------
CREATE TABLE SBI_OBJ_FUNCTION (
    BIOBJ_FUNCTION_ID INTEGER NOT NULL,
    BIOBJ_ID INTEGER NOT NULL,
    FUNCTION_ID INTEGER NOT NULL,
    IS_DETAIL SMALLINT DEFAULT 0,
    USER_IN VARCHAR2(100) NOT NULL,
    USER_UP VARCHAR2(100) NULL,
    USER_DE VARCHAR2(100) ,
    TIME_IN TIMESTAMP,
    TIME_UP TIMESTAMP NULL ,
    TIME_DE TIMESTAMP NULL ,
    SBI_VERSION_IN VARCHAR2(10) NULL,
    SBI_VERSION_UP VARCHAR2(10) NULL,
    SBI_VERSION_DE VARCHAR2(10) NULL,
    META_VERSION VARCHAR2(100) NULL,
    ORGANIZATION VARCHAR2(20) NULL,
    CONSTRAINT XAK1SBI_OBJ_FUNCTION UNIQUE (BIOBJ_ID ,FUNCTION_ID, ORGANIZATION),
  PRIMARY KEY (BIOBJ_FUNCTION_ID)
);

ALTER TABLE SBI_OBJ_FUNCTION ADD CONSTRAINT FK_SBI_OBJ_FUNCTION_1 FOREIGN KEY (BIOBJ_ID) REFERENCES SBI_OBJECTS (BIOBJ_ID);

-- END

-- 15/02/2021 - Cockpit complexity initializer
CREATE TABLE SBI_COCKPIT_ASSOCIATION (
	SBI_COCKPIT_ASSOCIATION_ID INTEGER NOT NULL ,
	BIOBJ_ID INTEGER NOT NULL ,
	DS_ID_FROM INTEGER NOT NULL ,
	COLUMN_NAME_FROM VARCHAR2(50) NULL,
	DS_ID_TO INTEGER NOT NULL ,
	COLUMN_NAME_TO VARCHAR2(50) NULL,
	USER_IN VARCHAR2(100) NOT NULL ENABLE, 
	USER_UP VARCHAR2(100), 
	USER_DE VARCHAR2(100), 
	TIME_IN TIMESTAMP (6) NOT NULL ENABLE, 
	TIME_UP TIMESTAMP (6) DEFAULT NULL, 
	TIME_DE TIMESTAMP (6) DEFAULT NULL, 
	SBI_VERSION_IN VARCHAR2(10), 
	SBI_VERSION_UP VARCHAR2(10), 
	SBI_VERSION_DE VARCHAR2(10), 
	META_VERSION VARCHAR2(100), 
	ORGANIZATION VARCHAR2(20),
	PRIMARY KEY (SBI_COCKPIT_ASSOCIATION_ID)
);
ALTER TABLE SBI_COCKPIT_ASSOCIATION ADD CONSTRAINT FK_SBI_COCKPIT_ASSOCIATION_1 FOREIGN KEY (BIOBJ_ID) REFERENCES SBI_OBJECTS (BIOBJ_ID);

CREATE TABLE SBI_COCKPIT_WIDGET (
	SBI_COCKPIT_WIDGET_ID INTEGER NOT NULL ,
	BIOBJ_ID INTEGER NOT NULL ,
	TAB VARCHAR2(50) NULL,
	WIDGET_TYPE VARCHAR2(50) NULL,
	DS_ID INTEGER NULL,
	ASSOCIATIVE SMALLINT NULL,
	CACHE SMALLINT NULL,
	FILTERS SMALLINT NULL,
	USER_IN VARCHAR2(100) NOT NULL ENABLE, 
	USER_UP VARCHAR2(100), 
	USER_DE VARCHAR2(100), 
	TIME_IN TIMESTAMP (6) NOT NULL ENABLE, 
	TIME_UP TIMESTAMP (6) DEFAULT NULL, 
	TIME_DE TIMESTAMP (6) DEFAULT NULL, 
	SBI_VERSION_IN VARCHAR2(10), 
	SBI_VERSION_UP VARCHAR2(10), 
	SBI_VERSION_DE VARCHAR2(10), 
	META_VERSION VARCHAR2(100), 
	ORGANIZATION VARCHAR2(20),
	PRIMARY KEY (SBI_COCKPIT_WIDGET_ID)
);
ALTER TABLE SBI_COCKPIT_WIDGET ADD CONSTRAINT FK_SBI_COCKPIT_WIDGET_1 FOREIGN KEY (BIOBJ_ID) REFERENCES SBI_OBJECTS (BIOBJ_ID);