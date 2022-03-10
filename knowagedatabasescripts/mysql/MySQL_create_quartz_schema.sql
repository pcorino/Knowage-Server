--
-- In your Quartz properties file, you'll need to set 
-- org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate
--
--
-- By: Ron Cordell - roncordell
--  I didn't see this anywhere, so I thought I'd post it here. This is the script from Quartz to create the tables in a MySQL database, modified to use INNODB instead of MYISAM.


CREATE TABLE QRTZ_JOB_DETAILS(
	JOB_NAME VARCHAR(80) NOT NULL,
	JOB_GROUP VARCHAR(80) NOT NULL,
	DESCRIPTION VARCHAR(120) NULL,
	JOB_CLASS_NAME VARCHAR(128) NOT NULL,
	IS_DURABLE VARCHAR(1) NOT NULL,
	REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
	JOB_DATA BLOB,
	IS_NONCONCURRENT TINYINT(1) NULL,
	IS_UPDATE_DATA TINYINT(1) NULL,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP),
	INDEX IDX_QRTZ_J_REQ_RECOVERY (SCHED_NAME,REQUESTS_RECOVERY),
	INDEX IDX_QRTZ_J_GRP (SCHED_NAME,JOB_GROUP)
);

CREATE TABLE QRTZ_TRIGGERS(
	TRIGGER_NAME VARCHAR(80) NOT NULL,
	TRIGGER_GROUP VARCHAR(80) NOT NULL,
	JOB_NAME VARCHAR(80) NOT NULL,
	JOB_GROUP VARCHAR(80) NOT NULL,
	DESCRIPTION VARCHAR(120) NULL,
	NEXT_FIRE_TIME BIGINT(13) NULL,
	PREV_FIRE_TIME BIGINT(13) NULL,
	PRIORITY INT(11) NULL,
	TRIGGER_STATE VARCHAR(16) NOT NULL,
	TRIGGER_TYPE VARCHAR(8) NOT NULL,
	START_TIME BIGINT(13) NOT NULL,
	END_TIME BIGINT(13) NULL,
	CALENDAR_NAME VARCHAR(80) NULL,
	MISFIRE_INSTR smallint(2) NULL,
	JOB_DATA BLOB,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
	KEY JOB_NAME (JOB_NAME,JOB_GROUP),
	INDEX IDX_QRTZ_T_J (SCHED_NAME,JOB_NAME,JOB_GROUP),
	INDEX IDX_QRTZ_T_JG (SCHED_NAME,JOB_GROUP),
	INDEX IDX_QRTZ_T_C (SCHED_NAME,CALENDAR_NAME),
	INDEX IDX_QRTZ_T_G (SCHED_NAME,TRIGGER_GROUP),
	INDEX IDX_QRTZ_T_STATE (SCHED_NAME,TRIGGER_STATE),
	INDEX IDX_QRTZ_T_N_STATE (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE),
	INDEX IDX_QRTZ_T_N_G_STATE (SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE),
	INDEX IDX_QRTZ_T_NEXT_FIRE_TIME (SCHED_NAME,NEXT_FIRE_TIME),
	INDEX IDX_QRTZ_T_NFT_ST (SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME),
	INDEX IDX_QRTZ_T_NFT_MISFIRE (SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME),
	INDEX IDX_QRTZ_T_NFT_ST_MISFIRE (SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE),
	INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP (SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE),
	CONSTRAINT QRTZ_TRIGGERS_FKEY FOREIGN KEY (SCHED_NAME, JOB_NAME, JOB_GROUP) REFERENCES QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

CREATE TABLE QRTZ_SIMPLE_TRIGGERS(
	TRIGGER_NAME VARCHAR(80) NOT NULL,
	TRIGGER_GROUP VARCHAR(80) NOT NULL,
	REPEAT_COUNT BIGINT(7) NOT NULL,
	REPEAT_INTERVAL BIGINT(12) NOT NULL,
	TIMES_TRIGGERED BIGINT(7) NOT NULL,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
	KEY TRIGGER_NAME (TRIGGER_NAME,TRIGGER_GROUP),
	CONSTRAINT QRTZ_SIMPLE_TRIGGERS_FKEY FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

CREATE TABLE QRTZ_CRON_TRIGGERS(
	TRIGGER_NAME VARCHAR(80) NOT NULL,
	TRIGGER_GROUP VARCHAR(80) NOT NULL,
	CRON_EXPRESSION VARCHAR(80) NOT NULL,
	TIME_ZONE_ID VARCHAR(80) NULL,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
	KEY TRIGGER_NAME (TRIGGER_NAME,TRIGGER_GROUP),
	CONSTRAINT QRTZ_CRON_TRIGGERS_FKEY FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

CREATE TABLE QRTZ_BLOB_TRIGGERS(
	TRIGGER_NAME VARCHAR(80) NOT NULL,
	TRIGGER_GROUP VARCHAR(80) NOT NULL,
	BLOB_DATA BLOB,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
	KEY TRIGGER_NAME (TRIGGER_NAME,TRIGGER_GROUP),
	CONSTRAINT QRTZ_BLOB_TRIGGERS_FKEY FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

CREATE TABLE QRTZ_CALENDARS(
	CALENDAR_NAME VARCHAR(80) NOT NULL,
	CALENDAR BLOB NOT NULL,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,CALENDAR_NAME)
);

CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS(
	TRIGGER_GROUP VARCHAR(80) NOT NULL,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP)
);

CREATE TABLE QRTZ_FIRED_TRIGGERS(
	ENTRY_ID VARCHAR(95) NOT NULL,
	TRIGGER_NAME VARCHAR(80) NOT NULL,
	TRIGGER_GROUP VARCHAR(80) NOT NULL,
	INSTANCE_NAME VARCHAR(80) NOT NULL,
	FIRED_TIME BIGINT(13) NOT NULL,
	PRIORITY INT(11) NOT NULL,
	STATE VARCHAR(16) NOT NULL,
	JOB_NAME VARCHAR(80) NULL,
	JOB_GROUP VARCHAR(80) NULL,
	REQUESTS_RECOVERY VARCHAR(1) NULL,
	IS_NONCONCURRENT TINYINT(1) NULL,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	SCHED_TIME BIGINT(13) NOT NULL,
	PRIMARY KEY (SCHED_NAME,ENTRY_ID),
	INDEX IDX_QRTZ_FT_TRIG_INST_NAME (SCHED_NAME,INSTANCE_NAME),
	INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY (SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY),
	INDEX IDX_QRTZ_FT_J_G (SCHED_NAME,JOB_NAME,JOB_GROUP),
	INDEX IDX_QRTZ_FT_JG (SCHED_NAME,JOB_GROUP),
	INDEX IDX_QRTZ_FT_T_G (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
	INDEX IDX_QRTZ_FT_TG (SCHED_NAME,TRIGGER_GROUP)
);

CREATE TABLE QRTZ_SCHEDULER_STATE(
	INSTANCE_NAME VARCHAR(80) NOT NULL,
	LAST_CHECKIN_TIME BIGINT(13) NOT NULL,
	CHECKIN_INTERVAL BIGINT(13) NOT NULL,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,INSTANCE_NAME)
);

CREATE TABLE QRTZ_LOCKS(
	LOCK_NAME VARCHAR(40) NOT NULL,
	SCHED_NAME VARCHAR(120) NOT NULL DEFAULT 'TESTSCHEDULER',
	PRIMARY KEY (SCHED_NAME,LOCK_NAME)
);

CREATE TABLE QRTZ_SIMPROP_TRIGGERS (
	SCHED_NAME VARCHAR(120) NOT NULL,
	TRIGGER_NAME VARCHAR(200) NOT NULL,
	TRIGGER_GROUP VARCHAR(200) NOT NULL,
	STR_PROP_1 VARCHAR(512) NULL,
	STR_PROP_2 VARCHAR(512) NULL,
	STR_PROP_3 VARCHAR(512) NULL,
	INT_PROP_1 INT NULL,
	INT_PROP_2 INT NULL,
	LONG_PROP_1 BIGINT NULL,
	LONG_PROP_2 BIGINT NULL,
	DEC_PROP_1 NUMERIC(13,4) NULL,
	DEC_PROP_2 NUMERIC(13,4) NULL,
	BOOL_PROP_1 BOOL NULL,
	BOOL_PROP_2 BOOL NULL,
	PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
	FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
	REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);

INSERT INTO QRTZ_LOCKS values('TRIGGER_ACCESS','TESTSCHEDULER');
INSERT INTO QRTZ_LOCKS values('JOB_ACCESS','TESTSCHEDULER');
INSERT INTO QRTZ_LOCKS values('CALENDAR_ACCESS','TESTSCHEDULER');
INSERT INTO QRTZ_LOCKS values('STATE_ACCESS','TESTSCHEDULER');
INSERT INTO QRTZ_LOCKS values('MISFIRE_ACCESS','TESTSCHEDULER');
