CREATE TABLE DEPT_MASTER (
	DEPT_ID INT,
	DEPT_NAME VARCHAR(64),
	STATUS VARCHAR(2),
	SECTION VARCHAR(64),
	DESCRIPTION VARCHAR(80),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(DEPT_ID)
) ENGINE=InnoDB;

CREATE TABLE DEPT_COST_HEAD_MASTER (
	HEAD_ID INT,
	HEAD_NAME VARCHAR(25),
	DEPT_ID INT,
	DESCRIPTION VARCHAR(64),
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
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
	DESCRIPTION VARCHAR(64),
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(DESG_ID),
	FOREIGN KEY (HEAD_ID)REFERENCES DEPT_COST_HEAD_MASTER(HEAD_ID),
	FOREIGN KEY (DEPT_ID) REFERENCES DEPT_MASTER (DEPT_ID),
	CONSTRAINT UNIQUE (HEAD_ID, DESG_NAME)
) ENGINE=InnoDB;

CREATE TABLE EMP_MASTER (
	EMP_ID INT,
	EMP_TYPE INT,
	EMP_JOINING_DATE DATE,
	EMP_FNAME VARCHAR(64),
	EMP_MNAME VARCHAR(64),
	EMP_LNAME VARCHAR(64),
	BIRTH_DATE DATE,
	EMP_PAN VARCHAR(30),
	EMP_AADHAR VARCHAR(30),
	EMP_LST_PROMO_DT DATE,
	GENDER VARCHAR(20),
	RETMT_DATE DATE,
	HANDICAP_FLAG TINYINT(1),
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_SAL_MASTER (
	EMP_ID INT,
	YEAR INT,
	BASIC DOUBLE,
	GRD_PAY DOUBLE,
	SCALE_PAY VARCHAR(40),
	SCALE_CODE VARCHAR(10),
	INC_AMT DOUBLE,
	INC_DATE DATE,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	UPD_USER_ID INT,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, STATUS)	
) ENGINE=InnoDB;

CREATE TABLE LEAVE_TYPE (
	LEAVE_TYPE_ID INT,
	LEAVE_NAME VARCHAR(40),
	LEAVE_DESC VARCHAR(60),
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	UPD_USER_ID INT,
	PRIMARY KEY(LEAVE_TYPE_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_LEAVE_MASTER (
	EMP_ID INT,
	LEAVE_ID INT,
	LEAVE_TYPE_ID INT,
	LEAVE_BAL INT,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	FOREIGN KEY (LEAVE_TYPE_ID) REFERENCES    LEAVE_TYPE (LEAVE_TYPE_ID),
	CONSTRAINT UNIQUE(EMP_ID, LEAVE_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_LEAVE_DETAILS (
	LEAVE_REQ_ID INT,
    EMP_ID INT,
	LEAVE_TYPE_ID INT,
	NO_OF_LEAVES DOUBLE,
	FROM_DATE DATETIME,
	TO_DATE DATETIME,
	REASON VARCHAR(128),
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(LEAVE_REQ_ID),
    FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	FOREIGN KEY (LEAVE_TYPE_ID) REFERENCES LEAVE_TYPE (LEAVE_TYPE_ID)
) ENGINE=InnoDB;

CREATE TABLE BANK_MASTER(
	BANK_ID INT,
	BANK_NAME VARCHAR(30),
	IFSC_CODE VARCHAR(30),
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	UPD_USER_ID INT,
	PRIMARY KEY(BANK_ID),
	CONSTRAINT UNIQUE (BANK_NAME)
) ENGINE=InnoDB;

CREATE TABLE EMP_BANK_DETAILS(
	EMP_ID INT,
	BANK_ID INT,
	ACCT_NO VARCHAR(30),
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	FOREIGN KEY (BANK_ID) REFERENCES BANK_MASTER (BANK_ID),
	CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;

CREATE TABLE EMP_DESG_DETAILS(
	DESG_ID INT,
	EMP_ID INT,
	START_DATE DATE,
	LW_DATE DATE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (DESG_ID) REFERENCES DESG_MASTER (DESG_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_DEPT_DETAILS(
	DEPT_ID INT,
	EMP_ID INT,
	START_DATE DATE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (DEPT_ID) REFERENCES DEPT_MASTER (DEPT_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_COST_HEAD_DETAILS(
	HEAD_ID INT,
	EMP_ID INT,
	START_DATE DATE,
	LW_DATE DATE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (HEAD_ID)REFERENCES DEPT_COST_HEAD_MASTER(HEAD_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_ALLOWANCES_MASTER(
 EMP_ID INT,
 CCA DOUBLE,
 WASHING_ALLOWANCE DOUBLE,
 NON_PRACT_ALLOWANCE DOUBLE,
 UNIFORM_ALLOWANCE DOUBLE,
 FAMILY_PLANNING_ALLOWANCE DOUBLE,
 CYCLE_ALLOWANCE DOUBLE,
 T_ALLOWANCE DOUBLE,
 OTHER_ALLOWANCE DOUBLE,
 OTHER_PAY DOUBLE,
 HRA_FLAG TINYINT,
 QTR_FLAG TINYINT,
 AFK_FLAG TINYINT,
 TA_FLAG TINYINT,
 PF_FLAG TINYINT,
 STATUS VARCHAR(2),
 UPD_USER_ID INT,
 ROW_UPD_DATE DATETIME,
 FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
 CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;

CREATE TABLE ADV_MASTER(
	ADV_ID INT,
	EMP_ID INT,
	ADV_NAME VARCHAR(30),	
	ADV_AMT DOUBLE,
	ADV_INSTALL_AMT DOUBLE,
	ADVANCE_DATE DATE,
	INST_START_DATE DATE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(ADV_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_ADV_DETAIL(
	ADV_ID INT,
	EMP_ID INT,
	ADV_BAL_AMT DOUBLE,
	INST_AMT DOUBLE,
	PAYMENT_DATE DATE,
	NXT_INST_AMT DOUBLE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
 	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (ADV_ID) REFERENCES ADV_MASTER (ADV_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_OT_DETAIL(
	OVR_TIM_ID INT,
	EMP_ID INT,
	OTIME_ORDER VARCHAR(30),
	OTIME_DATE DATE,
	OTIME_HRS DOUBLE,
	OTIME_AMT DOUBLE,
	OTIME_DAY_HRS DOUBLE,
	OTIME_PROCESS_DATE DATE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY(OVR_TIM_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, OTIME_DATE, STATUS)
) ENGINE=InnoDB;


CREATE TABLE EMP_LIC_MASTER(
	EMP_ID INT,
	POLICY_NO VARCHAR(30),
	INSTALL_AMT DOUBLE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE EMP_LIC_DETAILS(
	EMP_ID INT,
	POLICY_NO VARCHAR(30),
	PAYMENT_DATE DATE,
	PAYMENT_AMT DOUBLE,
	UPD_USER_ID INT,
	STATUS VARCHAR(2),
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;
-- TODO: Prasad: Needs to check unique constraint below
CREATE TABLE EMP_PF_DETAILS(
		EMP_ID INT,
		PF_DATE DATE,
		PFS_CPF_CNTRB DOUBLE,
		APF_ACPF_CNTRB DOUBLE,
		PF_LON_REC_AMT DOUBLE,
		CF_LON_REC_AMT DOUBLE,
		STATUS VARCHAR(2),
		UPD_USER_ID INT,
		ROW_UPD_DATE DATETIME,
		FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
		CONSTRAINT UNIQUE(EMP_ID, STATUS, PF_DATE)
) ENGINE=InnoDB;


CREATE TABLE EMP_TAX_EXEMPTIONS(
	 EMP_ID INT,
	 SECITION80C_DED DOUBLE,
	 HOME_LOAN_INTEREST_88EE DOUBLE,
	 SELF_DISABLE_80U DOUBLE,
	 CESS DOUBLE,
	 LOAN_PRINCIPAL DOUBLE,
	 SCHOOL_FEE DOUBLE,
	 LIC DOUBLE,
	 MUTUAL_FUND DOUBLE,
	 SEC_80D DOUBLE,
	 SEC_80E DOUBLE,
	 NSC DOUBLE,
	 PPF DOUBLE,
	 DONATION DOUBLE,
	 SEC_80DD DOUBLE,
	 BONUS DOUBLE,
	 ARREARS DOUBLE,
	 OT_AMOUNT DOUBLE,
	 OT_WAGES DOUBLE,
	 STATUS VARCHAR(2),
	 UPD_USER_ID INT,
	 ROW_UPD_DATE DATETIME,
	 FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	 CONSTRAINT UNIQUE(EMP_ID, STATUS)
) ENGINE=InnoDB;


CREATE TABLE EMP_TAXES(
	 EMP_ID INT,
	 INCOME_TAX DOUBLE,
	 SURCHARGE DOUBLE,
	 CESS DOUBLE,
	 UPD_USER_ID INT,
	 ROW_UPD_DATE DATETIME,
	 FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE INCOME_TAX_SLAB_MASTER(
	 SR_NO INT,
	 FIN_YEAR VARCHAR(30),
	 LOWER_SLAB DOUBLE,
	 HIGHER_SLAB DOUBLE,
	 INCOME_TAX_PERCENT DOUBLE,
	 SURCHARGE DOUBLE,
	 EDUCATION_CESS DOUBLE,
	 OTHER_CESS DOUBLE,
	 UPD_EMP_ID INT,
	 STATUS VARCHAR(2),
	 UPD_USER_ID INT,
	 ROW_UPD_DATE DATETIME
) ENGINE=InnoDB;

CREATE TABLE EMP_VAR_DEDUCTIONS(
	EMP_ID INT,
	AFK_RENT DOUBLE,
	SOCITY DOUBLE,
	PF_LOAN_REC DOUBLE,
	OTHER_DEDUCTION DOUBLE,
	MIS_RCVRY DOUBLE,
	MONTH_DATE DATE,
	NOTE VARCHAR(255),
	INCOME_TAX DOUBLE,
	ABS_DED DOUBLE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
    CONSTRAINT UNIQUE(EMP_ID, STATUS, MONTH_DATE)
)ENGINE=InnoDB;	

CREATE TABLE EMP_FIXED_DEDUCTIONS(
	EMP_ID INT,
	KSS_UNION_FEE DOUBLE,
	RENT DOUBLE, /*LFEE*/
	COURT_RCVRY DOUBLE, /* Monthly*/
	UNION_FEE DOUBLE, 
	GIS DOUBLE, /* group insurance */
	ADD_PF DOUBLE,
	STATUS VARCHAR(2),
	UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
 CONSTRAINT UNIQUE(EMP_ID, STATUS)
)ENGINE=InnoDB;	

CREATE TABLE PAY_BILL(
	EMP_ID INT,
	EMP_HEAD DOUBLE,
	BASIC DOUBLE,
	GRADE_PAY DOUBLE,
	DA DOUBLE,
	HRA DOUBLE,
	CCA DOUBLE,
	TA DOUBLE,
	NPA DOUBLE,
	WA DOUBLE,
	CA DOUBLE,
	UFALW DOUBLE,
	FPALW DOUBLE,
	TALW DOUBLE,
	T_ALLOWANCE DOUBLE,
	OT_AMT DOUBLE,
	OTHER_AMT DOUBLE,
	GROSS_PAY DOUBLE,
	D_LFEE DOUBLE,
	D_AFK_RENT DOUBLE,
	D_ABS_AMT DOUBLE,
	D_FA_RECOVERY DOUBLE,
	D_PROF_TAX DOUBLE,
	D_LIC DOUBLE,
	D_SOCIETY DOUBLE,
	D_GRP_INS DOUBLE,
	D_BKLN_REC DOUBLE,
	D_VLN_REC DOUBLE,
	D_PFS_CPF DOUBLE,
	D_APF_ACPF DOUBLE,
	D_PF_LN_REC DOUBLE,
	D_CPF_REC DOUBLE,
	D_INC_TAX DOUBLE,
	D_UNION_FEE DOUBLE,
	D_ELEC_REC DOUBLE,
	D_COURT_REC DOUBLE,
	D_OTHER DOUBLE,
	D_MISC DOUBLE,
	D_UN_FEE_KSS DOUBLE,
	TOT_DED DOUBLE,
	NET_PAY DOUBLE,
	DEPT_NAME VARCHAR(64),
	COST_HEAD_NAME VARCHAR(64),
	DESG_NAME VARCHAR(64),
	BANK_ACCT_NO VARCHAR(30),
	BANK_NAME VARCHAR(30),
	SCALE VARCHAR(40),
	SCALE_PAY VARCHAR(40),
	INC_AMT DOUBLE,
	INC_DATE VARCHAR(30),
	BANK_ID INT,
	MONTH_DATE DATE,
	ROW_UPD_DATE DATETIME,
	FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID),
	CONSTRAINT UNIQUE(EMP_ID, MONTH_DATE)
)ENGINE=InnoDB;


-- 01-2018 Tables realted to USER
CREATE TABLE ROLES(
	  ROLE_ID INT,
	  ROLE_NAME VARCHAR(64),
	  ROLE_DESC VARCHAR(256),
	  STATUS VARCHAR(2),
	  UPD_USER_ID INT,
	  ROW_UPD_DATE DATETIME,
	  PRIMARY KEY (ROLE_ID)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
  CREATE TABLE USER_MASTER(
	  USR_ID INT,
	  EMP_ID INT,
	  USR_NAME VARCHAR(64),
	  USR_PWD VARCHAR(256),
	  DEPT_IDS VARCHAR(64),
	  STATUS varchar(2),
	  UPD_USER_ID INT,
	  ROW_UPD_DATE DATETIME,
	  PRIMARY KEY (USR_ID),
	  FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE USER_ROLES(
	  USR_ROLE_ID INT,
	  USR_ID INT,
	  ROLE_ID INT,
	  STATUS VARCHAR(2),
	  UPD_USER_ID INT,
	  ROW_UPD_DATE DATETIME,
	  FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ROLE_ID),
	  FOREIGN KEY (USR_ID) REFERENCES USER_MASTER (USR_ID)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO ROLES
(`ROLE_ID`, `ROLE_NAME`, `ROLE_DESC`, `STATUS`, `ROW_UPD_DATE`)
VALUES (1, 'ADMIN', 'User can access Master section links', 'A', current_timestamp());

INSERT INTO ROLES
(`ROLE_ID`, `ROLE_NAME`, `ROLE_DESC`, `STATUS`, `ROW_UPD_DATE`)
VALUES (2, 'EMPLOYEE', 'User can access Employee specific links', 'A', current_timestamp());

INSERT INTO ROLES
(`ROLE_ID`, `ROLE_NAME`, `ROLE_DESC`, `STATUS`, `ROW_UPD_DATE`)
VALUES (3, 'REPORT', 'User can access Report specific links', 'A', current_timestamp());

CREATE TABLE EMP_CONTACT (
	EMP_ID INT,
    EMP_CNT_ID INT,
	EMP_EMAIL VARCHAR(64),
	EMP_PHONE VARCHAR(64),
	ADDR_LINE1 VARCHAR(64),
	ADDR_LINE2 VARCHAR(64),
	ADDR_LINE3 VARCHAR(64),
    CITY VARCHAR(64),
	STATE VARCHAR(64),
    PIN VARCHAR(8),
    SEC_EMP_EMAIL VARCHAR(64),
	SEC_EMP_PHONE VARCHAR(64),
	SEC_ADDR_LINE1 VARCHAR(64),
	SEC_ADDR_LINE2 VARCHAR(64),
	SEC_ADDR_LINE3 VARCHAR(64),
    SEC_CITY VARCHAR(64),
	SEC_STATE VARCHAR(64),
    SEC_PIN VARCHAR(8),
    STATUS VARCHAR(2),
    UPD_USER_ID INT,
	ROW_UPD_DATE DATETIME,
	PRIMARY KEY (EMP_CNT_ID),
    FOREIGN KEY (EMP_ID) REFERENCES EMP_MASTER (EMP_ID)
) ENGINE=InnoDB;

CREATE TABLE `EMP_ATTENDANCE_DETAILS` (
  `EMP_ATTENDANCE_ID` int(11) NOT NULL,
  `OFFICE_LOCATION` varchar(60) DEFAULT NULL,
  `DIVISION_UNIT` varchar(60) DEFAULT NULL,
  `AADHAR_NO` varchar(40) DEFAULT NULL,
  `EMPLOYEE_NAME` varchar(60) DEFAULT NULL,
  `DESIGNATION` varchar(60) DEFAULT NULL,
  `INTIME` varchar(10) DEFAULT NULL,
  `OUTTIME` varchar(10) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `ABSENCE_REASON` varchar(80) DEFAULT NULL,
  `ROW_UPDATE_DATE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`EMP_ATTENDANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `EMP_MASTER_ATTENDANCE_DETAILS` (
  `EMPID` int(11) NOT NULL,
  `ATTENDANCEID` int(11) NOT NULL,
  PRIMARY KEY (`EMPID`,`ATTENDANCEID`),
  KEY `ATTENDANCEID` (`ATTENDANCEID`),
  CONSTRAINT `EMP_MASTER_ATTENDANCE_DETAILS_ibfk_1` FOREIGN KEY (`EMPID`) REFERENCES `EMP_MASTER` (`EMP_ID`),
  CONSTRAINT `EMP_MASTER_ATTENDANCE_DETAILS_ibfk_2` FOREIGN KEY (`ATTENDANCEID`) REFERENCES `EMP_ATTENDANCE_DETAILS` (`EMP_ATTENDANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;