
CREATE TABLE DEPT_MASTER (
	DEPT_ID INT,
	DEPT_NAME VARCHAR(64),
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(DEPT_ID)
) ENGINE=InnoDB;

CREATE TABLE DEPT_COST_HEAD_MASTER (
	HEAD_ID INT,
	HEAD_NAME VARCHAR(25),
	DEPT_ID INT,
	DESCRIPTION VARCHAR(64),
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(HEAD_ID),
	FOREIGN KEY (DEPT_ID) REFERENCES DEPT_MASTER (DEPT_ID),
	CONSTRAINT UNIQUE (DEPT_ID, HEAD_NAME)
) ENGINE=InnoDB;

CREATE TABLE DESG_MASTER(
	DESG_ID INT,
	HEAD_ID INT,
	DEPT_ID INT,
	DESG_NAME VARCHAR(64),
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	DESCRIPTION VARCHAR(64),
	PRIMARY KEY(DESG_ID),
	FOREIGN KEY (HEAD_ID)REFERENCES DEPT_COST_HEAD_MASTER(HEAD_ID),
	FOREIGN KEY (DEPT_ID) REFERENCES DEPT_MASTER (DEPT_ID),
	CONSTRAINT UNIQUE (HEAD_ID, DESG_NAME)
) ENGINE=InnoDB;

CREATE TABLE EMP_MASTER (
	EMP_ID INT,
	EMP_JOINING_DATE DATE,
	EMP_FNAME VARCHAR(64),
	EMP_MNAME VARCHAR(64),
	EMP_LNAME VARCHAR(64),
	BIRTH_DATE DATE,
	EMP_CONTACTNO VARCHAR(64),
	EMP_EMAIL VARCHAR(64),
	EMP_PHONE VARCHAR(64),
	EMP_PAN VARCHAR(30),
	EMP_AADHAR VARCHAR(30),
	EMP_LST_PROMO_DT DATE,
	ADDR_LINE1 VARCHAR(64),
	ADDR_LINE2 VARCHAR(64),
	ADDR_LINE3 VARCHAR(64),
	GENDER VARCHAR(20),
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY (EMP_ID)
) ENGINE=InnoDB;


CREATE TABLE EMP_SAL_MASTER (
	EMP_ID INT,
	YEAR INT,
	BASIC DOUBLE,
	GRD_PAY DOUBLE,
	SCALE_PAY DOUBLE,
	SCAE_INC DOUBLE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, STATUS)	
) ENGINE=InnoDB;

CREATE TABLE EMP_LEAVE_MASTER (
	LEAVE_ID INT,
	EMP_ID INT,
	LEAVE_TYPE VARCHAR(40),
	NO_OF_LEAVES INT,
	LEAVE_BAL INT,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(LEAVE_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, LEAVE_TYPE, STATUS)
) ENGINE=InnoDB;


CREATE TABLE BANK_MASTER (
	BANK_ID INT,
	BANK_NAME VARCHAR(30),
	IFSC_CODE VARCHAR(30),
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(BANK_ID),
	CONSTRAINT UNIQUE (BANK_NAME)
) ENGINE=InnoDB;

CREATE TABLE ADV_MASTER (
	ADV_ID INT,
	ADV_NAME VARCHAR(30),	
	ADV_AMT DOUBLE,
	PAYMENT_DATE DATE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(ADV_ID),
	CONSTRAINT UNIQUE(ADV_NAME, STATUS)
) ENGINE=InnoDB;

CREATE TABLE EMP_BANK_DETAILS (
	EMP_ID INT,
	BANK_ID INT,
	ACCT_NO VARCHAR(30),
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	FOREIGN KEY (BANK_ID) REFERENCES BANK_MASTER (BANK_ID),
	CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;

CREATE TABLE EMP_DESG_DETAILS (
	DESG_ID INT,
	EMP_ID INT,
	START_DATE DATE,
	LW_DATE DATE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (DESG_ID) REFERENCES DESG_MASTER (DESG_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_DEPT_DETAILS (
	DEPT_ID INT,
	EMP_ID INT,
	START_DATE DATE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (DEPT_ID) REFERENCES DEPT_MASTER (DEPT_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_COST_HEAD_DETAILS (
	HEAD_ID INT,
	EMP_ID INT,
	START_DATE DATE,
	LW_DATE DATE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (HEAD_ID)REFERENCES DEPT_COST_HEAD_MASTER(HEAD_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_ALLOWANCES_MASTER (
 EMP_ID INT,
 CCA DOUBLE,
 WASHING_ALLOWANCE DOUBLE,
 CONV_ALLOWANCE DOUBLE,
 NON_PRACT_ALLOWANCE DOUBLE,
 UNIFORM_ALLOWANCE DOUBLE,
 FAMILY_PLANNING_ALLOWANCE DOUBLE,
 CYCLE_ALLOWANCE DOUBLE,
 HRA_FLAG BIT,
 STATUS VARCHAR(2),
 ROW_UPD_DATE DATETIME,
 FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
 CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;

/*  Yet to work on
CREATE TABLE ADV_DETAIL (
	ADV_ID INT,
	EMP_ID INT,
	ADV_BAL_AMT DOUBLE,
	INST_AMT DOUBLE,	
	NXT_INST_AMT DOUBLE,
	PAYMENT_DATE DATE,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	FOREIGN KEY (ADV_ID) REFERENCES ADV_MASTER (ADV_ID)
) ENGINE=InnoDB;
*/
/*CREATE TABLE HEAD_DEPT (
	DEPT_ID INT,
	HEAD_ID INT,
	START_DATE DATE,
	FOREIGN KEY (HEAD_ID)REFERENCES DEPT_COST_HEAD_MASTER(HEAD_ID),
	FOREIGN KEY (DEPT_ID) REFERENCES DEPARTMENT (DEPT_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_MASTER (
	DEPT_ID INT,
	HEAD_ID INT,
	DESG_ID INT,
	EMP_ID INT,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME
) ENGINE=InnoDB;
*/

CREATE TABLE EMP_OT_DETAIL (
	OVR_TIM_ID INT,
	EMP_ID INT,
	OTIME_ORDER VARCHAR(30),
	OTIME_DATE DATE,
	OTIME_HRS DOUBLE,
	OTIME_AMT DOUBLE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(OVR_TIM_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, OTIME_DATE, STATUS)
) ENGINE=InnoDB;


CREATE TABLE EMP_LIC_MASTER (
	EMP_ID INT,
	POLICY_NO VARCHAR(30),
	INSTALL_AMT DOUBLE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

/* Need to check about this table
CREATE TABLE EMP_LIC_DETAILS (
	EMP_ID INT,
	POLICY_NO VARCHAR(30),
	PAYMENT_DATE DATE,
	PAYMENT_AMT DOUBLE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;
*/
CREATE TABLE EMP_PF_DETAILS(
	EMP_ID INT,
	PF_DATE DATE,
	PFS_CPF_CNTRB DOUBLE,
	APF_ACPF_CNTRB DOUBLE,
	PF_LON_REC_AMT DOUBLE,
	CF_LON_REC_AMT DOUBLE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;
/* No conveyance seperate table, can ignore
CREATE TABLE CONVEY_ALWNCE (
	CNV_ALW_ID INT,
	DESG_ID INT,
	CON_AMT DOUBLE,
	FOREIGN KEY (DESG_ID) REFERENCES DESG_MASTER (DESG_ID)
) ENGINE=InnoDB;
*/
CREATE TABLE EMP_QTR (
	EMP_ID INT,
	AFK_QTR BIT,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;

CREATE TABLE EMP_TAX_DEDUCTION (
 EMP_ID INT,
 SECITION80C_DED DOUBLE,
 HOME_LOAN_INTEREST_88EE DOUBLE,
 SELF_DISABLE_80U DOUBLE,
 CESS DOUBLE,
 STATUS VARCHAR(2),
 ROW_UPD_DATE DATETIME,
 FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
 CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;


CREATE TABLE EMP_TAXES (
 EMP_ID INT,
 INCOME_TAX DOUBLE,
 SURCHARGE DOUBLE,
 CESS DOUBLE,
 ROW_UPD_DATE DATETIME,
 FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE INCOME_TAX_SLAB_MASTER (
 INCOME_TAX_ID INT,
 FIN_YEAR VARCHAR(30),
 LOWER_SLAB DOUBLE,
 HIGHER_SLAB DOUBLE,
 INCOME_TAX_PERCENT DOUBLE,
 SURCHARGE DOUBLE,
 EDUCATION_CESS DOUBLE,
 OTHER_CESS DOUBLE,
 STATUS VARCHAR(2),
 ROW_UPD_DATE DATETIME
) ENGINE=InnoDB;

CREATE TABLE EMP_DEDUCTION_DETAILS (
	EMP_ID INT,
	AFK_RENT DOUBLE,
	SOCITY DOUBLE,
	ELECT_RCVRY DOUBLE,
	COURT_RCVRY DOUBLE,
	UNION_FEE DOUBLE,
	OTHER_DEDUCTION DOUBLE,
	MIS_RCVRY DOUBLE,
	KSS_UNION_FEE DOUBLE,
	MONTH_DATE DATE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME
)ENGINE=InnoDB;

ALTER TABLE EMP_MASTER ADD COLUMN RETMT_DATE DATE;

-- 01-2018 Tables realted to USER
CREATE TABLE user_roles (
  ROLE_ID INT,
  ROLE_NAME VARCHAR(64),
  ROLE_DESC VARCHAR(256),
  STATUS VARCHAR(2),
  ROW_UPD_DATE DATETIME,
  PRIMARY KEY (ROLE_ID)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE user_master (
  USR_ID_PK INT,
  EMP_ID INT,
  ROLE_ID INT,
  USR_ID VARCHAR(64),
  USR_PWD VARCHAR(256),
  STATUS varchar(2),
  ROW_UPD_DATE DATETIME,
  PRIMARY KEY (USR_ID_PK),
  FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
  FOREIGN KEY (ROLE_ID) REFERENCES USER_ROLES (ROLE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;