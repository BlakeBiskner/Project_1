
CREATE TABLE EVENT_TYPE(
et_id INTEGER,
reimbursement_coverage NUMBER NOT NULL,
et_desc VARCHAR2(40)NOT NULL,
PRIMARY KEY(et_id)
);

CREATE TABLE EVENT(
e_id INTEGER,
e_type INTEGER NOT NULL,
e_name VARCHAR2(40) NOT NULL,
e_cost NUMBER(10,2) NOT NULL,
e_date TIMESTAMP NOT NULL,
PRIMARY KEY(e_id),
FOREIGN KEY(e_type) REFERENCES EVENT_TYPE(et_id)

);


CREATE TABLE USER_TYPE(
usr_t_id INTEGER,
usr_t_permissions INTEGER,
usr_t_name VARCHAR2(40) UNIQUE NOT NULL,
usr_t_desc VARCHAR2(100),
PRIMARY KEY (usr_t_id)
);


CREATE TABLE DEPARTMENT(
dept_id INTEGER,
dept_name VARCHAR2(40) UNIQUE NOT NULL,
PRIMARY KEY(dept_id)

);

CREATE TABLE USR(
usr_id INTEGER,
usr_firstname VARCHAR2(40) NOT NULL,
usr_lastname VARCHAR2(40) NOT NULL,
usr_username VARCHAR2(40) UNIQUE NOT NULL,
usr_email VARCHAR2(50) UNIQUE NOT NULL,
usr_direct_supervisor INTEGER NOT NULL,
usr_department INTEGER NOT NULL,
usr_type INTEGER NOT NULL,
usr_password VARCHAR2(200) NOT NULL,
usr_salt VARCHAR2(200) NOT NULL,
usr_account_approved VARCHAR2(1) DEFAULT('Y') CHECK (usr_account_approved='Y' OR usr_account_approved='N')NOT NULL,
usr_has_email VARCHAR2(1)DEFAULT('N') CHECK (usr_has_email='Y' OR usr_has_email='N' OR usr_has_email='U')NOT NULL,
PRIMARY KEY(usr_id),
FOREIGN KEY(usr_direct_supervisor) REFERENCES USR(usr_id),
FOREIGN KEY(usr_department) REFERENCES DEPARTMENT(dept_id),
FOREIGN KEY(usr_type) REFERENCES USER_TYPE(usr_t_id)
);

CREATE TABLE APPLICATION(
a_id INTEGER,
user_id INTEGER NOT NULL,
event_id INTEGER NOT NULL,
comments VARCHAR2(90),
a_date TIMESTAMP,
reimbursement_amount NUMBER(10,2),
PRIMARY KEY(a_id),
FOREIGN KEY(user_id) REFERENCES USR(usr_id),
FOREIGN KEY(event_id) REFERENCES EVENT(e_id)
);

CREATE TABLE APPLICATION_APPROVAL_TYPE(
aat_id INTEGER,
type VARCHAR2(40) UNIQUE NOT NULL,
PRIMARY KEY(aat_id)
);

CREATE TABLE APPLICATION_APPROVAL(
aa_id INTEGER,
aat_id INTEGER NOT NULL,
approval_time TIMESTAMP NOT NULL,
approval VARCHAR2(1) CHECK (approval='Y' OR approval='N') NOT NULL,
reasoning VARCHAR2(750),
PRIMARY KEY(aa_id),
FOREIGN KEY(aat_id) REFERENCES APPLICATION_APPROVAL_TYPE(aat_id)
);

CREATE TABLE APPLICATION_MATERIAL(
am_id INTEGER,
am_a_id INTEGER NOT NULL,
am_material BLOB NOT NULL,
am_description VARCHAR2(200),
PRIMARY KEY(am_id),
FOREIGN KEY(am_a_id) REFERENCES APPLICATION(a_id)
);

CREATE TABLE APPLICATION_MATERIAL_REQUEST(
amr_id INTEGER,
amr_a_id INTEGER NOT NULL,
amr_req_usr_id INTEGER NOT NULL,
amr_request VARCHAR2(500) NOT NULL,
amr_am_id INTEGER,
PRIMARY KEY(amr_id),
FOREIGN KEY(amr_a_id) REFERENCES APPLICATION(a_id),
FOREIGN KEY(amr_req_usr_id) REFERENCES USR(usr_id)
);


CREATE TABLE EVENT_GRADE_FORMAT(
egf_id INTEGER,
egf_format VARCHAR2(30) UNIQUE NOT NULL,
egf_description VARCHAR2(200),
PRIMARY KEY(egf_id)
);

CREATE TABLE EVENT_GRADE(
eg_id INTEGER,
eg_e_id INTEGER NOT NULL,
eg_a_id INTEGER NOT NULL,
eg_grade VARCHAR2(10),
eg_desc VARCHAR2(150),
eg_egf_id INTEGER NOT NULL,
PRIMARY KEY(eg_id),
FOREIGN KEY(eg_e_id) REFERENCES EVENT(e_id),
FOREIGN KEY(eg_a_id) REFERENCES APPLICATION(a_id)
);

--DROP TABLE APPLICATION_MATERIAL_REQUEST;
--DROP TABLE APPLICATION_MATERIAL;
--DROP TABLE APPLICATION_APPROVAL;
--DROP TABLE APPLICATION_APPROVAL_TYPE;
--DROP TABLE EVENT_GRADE;
--DROP TABLE EVENT_GRADE_FORMAT;
--DROP TABLE APPLICATION;
--DROP TABLE USR;
--DROP TABLE USER_TYPE;
--DROP TABLE EVENT;
--DROP TABLE EVENT_TYPE;
--DROP TABLE DEPARTMENT;






CREATE SEQUENCE dept_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  dept_id_trigger
  before insert on DEPARTMENT              
  for each row  
begin   
  if :new.dept_id is null then 
    select dept_id_seq.nextval into :new.dept_id from dual; 
  end if; 
end; 
/




CREATE SEQUENCE usr_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  usr_id_trigger
  before insert on USR              
  for each row  
begin   
  if :new.usr_id is null then 
    select usr_id_seq.nextval into :new.usr_id from dual; 
  end if; 
end; 
/


CREATE SEQUENCE et_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  et_id_trigger
  before insert on EVENT_TYPE              
  for each row  
begin   
  if :new.et_id is null then 
    select et_id_seq.nextval into :new.et_id from dual; 
  end if; 
end; 
/

CREATE SEQUENCE egf_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  egf_id_trigger
  before insert on EVENT_GRADE_FORMAT              
  for each row  
begin   
  if :new.egf_id is null then 
    select egf_id_seq.nextval into :new.egf_id from dual; 
  end if; 
end; 
/

CREATE SEQUENCE eg_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  eg_id_trigger
  before insert on EVENT_GRADE              
  for each row  
begin   
  if :new.eg_id is null then 
    select eg_id_seq.nextval into :new.eg_id from dual; 
  end if; 
end; 
/

CREATE SEQUENCE e_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  e_id_trigger
  before insert on EVENT              
  for each row  
begin   
  if :new.e_id is null then 
    select e_id_seq.nextval into :new.e_id from dual; 
  end if; 
end; 
/

CREATE SEQUENCE usr_t_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  usr_t_id_trigger
  before insert on USER_TYPE              
  for each row  
begin   
  if :new.usr_t_id is null then 
    select usr_t_id_seq.nextval into :new.usr_t_id from dual; 
  end if; 
end; 
/


CREATE SEQUENCE a_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  a_id_trigger
  before insert on APPLICATION              
  for each row  
begin   
  if :new.a_id is null then 
    select a_id_seq.nextval into :new.a_id from dual; 
  end if; 
end; 
/


CREATE SEQUENCE aat_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  aat_id_trigger
  before insert on APPLICATION_APPROVAL_TYPE              
  for each row  
begin   
  if :new.aat_id is null then 
    select aat_id_seq.nextval into :new.aat_id from dual; 
  end if; 
end; 
/

CREATE SEQUENCE aa_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  aa_id_trigger
  before insert on APPLICATION_APPROVAL              
  for each row  
begin   
  if :new.aa_id is null then 
    select aa_id_seq.nextval into :new.aa_id from dual; 
  end if; 
end; 
/

CREATE SEQUENCE am_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  am_id_trigger
  before insert on APPLICATION_MATERIAL              
  for each row  
begin   
  if :new.am_id is null then 
    select am_id_seq.nextval into :new.am_id from dual; 
  end if; 
end; 
/


CREATE SEQUENCE amr_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  amr_id_trigger
  before insert on APPLICATION_MATERIAL_REQUEST              
  for each row  
begin   
  if :new.amr_id is null then 
    select amr_id_seq.nextval into :new.amr_id from dual; 
  end if; 
end; 
/
