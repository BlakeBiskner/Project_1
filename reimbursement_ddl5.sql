
DROP TABLE APPLICATION_MATERIAL_REQUEST;
DROP TABLE APPLICATION_MATERIAL;
DROP TABLE APPLICATION_APPROVAL;
DROP TABLE APPLICATION;
DROP TABLE APPLICATION_STATUS;
DROP TABLE USR;
DROP TABLE USER_JOB;
DROP TABLE USER_JOB_TYPE;
DROP TABLE EVENT_PARTICIPATION;
DROP TABLE EVENT;
DROP TABLE EVENT_LOCATION;
DROP TABLE EVENT_TYPE;
DROP TABLE DEPARTMENT;
DROP TABLE EVENT_GRADE_FORMAT;



CREATE TABLE EVENT_TYPE(
et_id INTEGER,
reimbursement_coverage NUMBER NOT NULL,
et_desc VARCHAR2(40)NOT NULL,
PRIMARY KEY(et_id)
);

CREATE TABLE EVENT_GRADE_FORMAT(
egf_id INTEGER,
egf_format VARCHAR2(30) UNIQUE NOT NULL,
egf_description VARCHAR2(200),
PRIMARY KEY(egf_id)
);

CREATE TABLE EVENT_LOCATION(
el_id INTEGER,
el_state VARCHAR2(20) NOT NULL,
el_city VARCHAR2(60) NOT NULL,
el_zipcode INTEGER NOT NULL,
el_address_line1 VARCHAR2(100) NOT NULL,
el_address_line2 VARCHAR2(100),
PRIMARY KEY(el_id)
);


CREATE TABLE EVENT(
e_id INTEGER,
e_type INTEGER NOT NULL,
e_name VARCHAR2(60) NOT NULL,
e_date TIMESTAMP NOT NULL,
e_enddate TIMESTAMP,
e_egf_id INTEGER NOT NULL,
e_passing_grade VARCHAR2(10) NOT NULL,
e_location INTEGER,
PRIMARY KEY(e_id),
FOREIGN KEY(e_type) REFERENCES EVENT_TYPE(et_id),
FOREIGN KEY(e_egf_id) REFERENCES EVENT_GRADE_FORMAT(egf_id),
FOREIGN KEY(e_location) REFERENCES EVENT_LOCATION(el_id)
);

CREATE TABLE USER_JOB_TYPE(
ujt_id INTEGER,
ujt_type VARCHAR2(20)NOT NULL,
PRIMARY KEY(ujt_id)
);

CREATE TABLE USER_JOB(
usr_j_id INTEGER,
usr_j_name VARCHAR2(40) UNIQUE NOT NULL,
usr_j_desc VARCHAR2(100),
usr_j_type INTEGER NOT NULL,
PRIMARY KEY (usr_j_id),
FOREIGN KEY(usr_j_type) REFERENCES USER_JOB_TYPE(ujt_id)
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
usr_job INTEGER NOT NULL,
usr_password VARCHAR2(200) NOT NULL,
usr_account_approved VARCHAR2(1) DEFAULT('Y') CHECK (usr_account_approved='Y' OR usr_account_approved='N')NOT NULL,
usr_has_email VARCHAR2(1)DEFAULT('N') CHECK (usr_has_email='Y' OR usr_has_email='N' OR usr_has_email='U')NOT NULL,
PRIMARY KEY(usr_id),
FOREIGN KEY(usr_direct_supervisor) REFERENCES USR(usr_id),
FOREIGN KEY(usr_department) REFERENCES DEPARTMENT(dept_id),
FOREIGN KEY(usr_job) REFERENCES USER_JOB(usr_J_id)
);

CREATE TABLE APPLICATION_STATUS(
as_id INTEGER,
as_status VARCHAR2(20),
PRIMARY KEY(as_id)
);

CREATE TABLE EVENT_PARTICIPATION(
ep_id INTEGER,
ep_cost NUMBER(36,2) NOT NULL,
ep_grade VARCHAR2(10),
ep_desc VARCHAR2(150),
passed VARCHAR2(1) CHECK (passed='Y' OR passed='N'),
event_id INTEGER NOT NULL,
PRIMARY KEY(ep_id),
FOREIGN KEY(event_id) REFERENCES EVENT(e_id)
);

CREATE TABLE APPLICATION(
a_id INTEGER,
user_id INTEGER NOT NULL,
comments VARCHAR2(300) NOT NULL,
time_missed NUMBER,
a_date TIMESTAMP,
reimbursement_amount NUMBER(10,2),
next_approver INTEGER,
status INTEGER NOT NULL,
event_participation INTEGER NOT NULL,
PRIMARY KEY(a_id),
FOREIGN KEY(user_id) REFERENCES USR(usr_id),
FOREIGN KEY(next_approver) REFERENCES USR(usr_id),
FOREIGN KEY(status) REFERENCES APPLICATION_STATUS(as_id)
);

CREATE TABLE APPLICATION_APPROVAL(
aa_id INTEGER,
aa_application INTEGER NOT NULL,
aa_approver INTEGER NOT NULL,
approval_time TIMESTAMP,
approval VARCHAR2(1) CHECK (approval='Y' OR approval='N'),
reasoning VARCHAR2(750),
PRIMARY KEY(aa_id),
FOREIGN KEY(aa_application) REFERENCES APPLICATION(a_id),
FOREIGN KEY(aa_approver) REFERENCES USR(usr_id)
);

CREATE TABLE APPLICATION_MATERIAL(
am_id INTEGER,
am_a_id INTEGER NOT NULL,
am_material BLOB NOT NULL,
am_filename VARCHAR2(50) NOT NULL,
am_description VARCHAR2(200),
PRIMARY KEY(am_id),
FOREIGN KEY(am_a_id) REFERENCES APPLICATION(a_id)
);

CREATE TABLE APPLICATION_MATERIAL_REQUEST(
amr_id INTEGER,
amr_a_id INTEGER NOT NULL,
amr_requester_id INTEGER NOT NULL,
amr_requestee_id INTEGER NOT NULL,
amr_request VARCHAR2(500) NOT NULL,
amr_am_id INTEGER,
PRIMARY KEY(amr_id),
FOREIGN KEY(amr_a_id) REFERENCES APPLICATION(a_id),
FOREIGN KEY(amr_requester_id) REFERENCES USR(usr_id),
FOREIGN KEY(amr_requestee_id) REFERENCES USR(usr_id)
);



CREATE OR REPLACE FUNCTION get_available_reimbursement (usr_id IN INT)
RETURN NUMBER
IS
yearly_available_reimbursement NUMBER(36,2);
BEGIN
SELECT SUM(reimbursement_amount)INTO yearly_available_reimbursement FROM
(SELECT extract(year from SYSTIMESTAMP)cur_year from dual) a
left join
(SELECT reimbursement_amount,user_id,comments, extract(year from a_date) app_year FROM APPLICATION WHERE user_id = usr_id)b
ON a.cur_year = b.app_year GROUP BY user_id;
yearly_available_reimbursement := 1000 - yearly_available_reimbursement;
IF yearly_available_reimbursement IS NULL THEN
yearly_available_reimbursement := 1000;
ELSIF yearly_available_reimbursement < 0 THEN
yearly_available_reimbursement := 0;
END IF;
RETURN yearly_available_reimbursement;
END;
/




CREATE OR REPLACE VIEW user_view AS
SELECT usr.usr_id,usr.usr_firstname,usr.usr_lastname,usr.usr_username,usr.usr_email,usr.usr_password,
usr.usr_account_approved,usr.usr_has_email,usr.dept_name,usr.usr_j_name job,
usr.usr_j_desc job_desc,ds.usr_id ds_id, ds.usr_firstname ds_firstname, ds.usr_lastname ds_lastname, 
ds.usr_username ds_username, ds.usr_email ds_email, ds.dept_name ds_dept_name, 
ds.usr_j_name ds_job, ds.usr_j_desc ds_job_desc, ds.usr_job ds_job_id,
usr.usr_job usr_job_id, usr.usr_department usr_department_id,
usr.ujt_id ujt_id, usr.ujt_type job_type, ds.ujt_id ds_jt_id, ds.ujt_type ds_ujt_type,
get_available_reimbursement(usr.usr_id) available_reimbursement
FROM(
(SELECT * FROM (USR INNER JOIN DEPARTMENT ON usr_department = dept_id) 
INNER JOIN USER_JOB ON usr_job = usr_j_id
INNER JOIN USER_JOB_TYPE ON usr_j_type=ujt_id)usr
LEFT JOIN
(SELECT * FROM (USR INNER JOIN DEPARTMENT ON usr_department = dept_id) 
INNER JOIN USER_JOB ON usr_job = usr_j_id
INNER JOIN USER_JOB_TYPE ON usr_j_type=ujt_id)ds
ON usr.usr_id = ds.usr_id
);


CREATE OR REPLACE VIEW event_view AS
SELECT e_id, e_name,e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,
reimbursement_coverage,et_desc,el_id,el_state,el_city,el_zipcode,el_address_line1,el_address_line2
FROM
EVENT INNER JOIN EVENT_TYPE ON e_type = et_id INNER JOIN EVENT_GRADE_FORMAT ON e_egf_id=egf_id
LEFT JOIN EVENT_LOCATION ON e_location=el_id;

--
--CREATE OR REPLACE VIEW application_view AS
--SELECT e_id, e_name, e_cost, e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,
--reimbursement_coverage,et_desc,
--a_id,user_id,comments,a_date,reimbursement_amount,eg_id,eg_a_id,eg_grade,eg_desc
--FROM
--EVENT INNER JOIN EVENT_TYPE ON e_type = et_id INNER JOIN EVENT_GRADE_FORMAT ON e_egf_id=egf_id
--INNER JOIN APPLICATION ON event_id=e_id INNER JOIN EVENT_GRADE ON a_id = eg_a_id;



CREATE OR REPLACE VIEW application_view AS
SELECT e_id, e_name, ep_cost, e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,
reimbursement_coverage,et_desc,
a_id,user_id,comments,a_date,reimbursement_amount,ep_id,ep_grade,ep_desc,
as_status status,as_id status_id, next_approver, passed
FROM
EVENT INNER JOIN EVENT_TYPE ON e_type = et_id INNER JOIN EVENT_GRADE_FORMAT ON e_egf_id=egf_id
INNER JOIN EVENT_PARTICIPATION ON event_id=e_id INNER JOIN APPLICATION ON ep_id = event_participation
INNER JOIN APPLICATION_STATUS ON status=as_id;

CREATE OR REPLACE PROCEDURE update_application 
(new_a_id IN INTEGER, 
new_ep_id IN INTEGER,
new_reimbursement_amount IN NUMBER, 
new_next_approver IN INTEGER, 
new_status IN INTEGER, 
new_ep_cost IN NUMBER,
new_ep_grade IN VARCHAR2,
new_ep_desc IN VARCHAR2,
new_passed IN VARCHAR2) 
AS
BEGIN
  UPDATE EVENT_PARTICIPATION SET 
  ep_cost = new_ep_cost, ep_grade = new_ep_grade, ep_desc = new_ep_desc, passed = new_passed
  WHERE ep_id = new_ep_id;
  UPDATE APPLICATION SET
  reimbursement_amount = new_reimbursement_amount, next_approver = new_next_approver, status = new_status
  WHERE a_id = new_a_id;
  COMMIT;
END update_application;
/


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

CREATE SEQUENCE ep_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  ep_id_trigger
  before insert on EVENT_PARTICIPATION              
  for each row  
begin   
  if :new.ep_id is null then 
    select ep_id_seq.nextval into :new.ep_id from dual; 
  end if; 
end; 
/

CREATE SEQUENCE el_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  el_id_trigger
  before insert on EVENT_LOCATION              
  for each row  
begin   
  if :new.el_id is null then 
    select el_id_seq.nextval into :new.el_id from dual; 
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

CREATE SEQUENCE usr_j_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  usr_j_id_trigger
  before insert on USER_JOB              
  for each row  
begin   
  if :new.usr_j_id is null then 
    select usr_j_id_seq.nextval into :new.usr_j_id from dual; 
  end if; 
end; 
/


CREATE SEQUENCE ujt_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  ujt_id_trigger
  before insert on USER_JOB_TYPE           
  for each row  
begin   
  if :new.ujt_id is null then 
    select ujt_id_seq.nextval into :new.ujt_id from dual; 
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


CREATE SEQUENCE as_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE OR REPLACE TRIGGER  as_id_trigger
  before insert on APPLICATION_STATUS              
  for each row  
begin   
  if :new.as_id is null then 
    select as_id_seq.nextval into :new.as_id from dual; 
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
