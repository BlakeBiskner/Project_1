INSERT INTO DEPARTMENT VALUES(null,'Administration');
INSERT INTO USER_JOB_TYPE VALUES(null,'Department Head');
INSERT INTO USER_JOB_TYPE VALUES(null,'Benco');
INSERT INTO USER_JOB_TYPE VALUES(null,'Benco Supervisor');
INSERT INTO USER_JOB_TYPE VALUES(null,'Employee');
INSERT INTO USER_JOB_TYPE VALUES(null,'Intern');
SELECT * FROM USER_JOB_TYPE;
INSERT INTO USER_JOB VALUES(null,'CEO','The CEO of the company',1);
INSERT INTO USER_JOB VALUES(null,'Writing Intern','An intern for the company that works as a writer.',5);

INSERT INTO USR 
(usr_id,usr_firstname,usr_lastname,usr_username,usr_email,usr_direct_supervisor,usr_department, usr_job,usr_password)
VALUES(99999,'Stan','Lee','stanlee','stanlee@mailinator.com',99999,41,1,'$2a$10$5Nm7Khp8bTZgip78u80Zv.OMUwN1lzbsx52tMyouizSKvY0tN2Mem');

SELECT * FROM USER_VIEW;
SELECT * FROM DEPARTMENT;
SELECT * FROM USER_JOB;

DELETE FROM usr WHERE usr_firstname != 'Stan';
DELETE FROM APPLICATION;
SELECT * FROM APPLICATION_VIEW;
SELECT * FROM APPLICATION;
commit;



INSERT INTO EVENT_TYPE VALUES(null,80,'University Course');
INSERT INTO EVENT_TYPE VALUES(null,60,'Seminar');
INSERT INTO EVENT_TYPE VALUES(null,75,'Certification Preparation Class');
INSERT INTO EVENT_TYPE VALUES(null,100,'Certification');
INSERT INTO EVENT_TYPE VALUES(null,90,'Technical Training');
INSERT INTO EVENT_TYPE VALUES(null,30,'Other');

INSERT INTO EVENT_GRADE_FORMAT VALUES(null,'PASS/FAIL',null);
INSERT INTO EVENT_GRADE_FORMAT VALUES(null,'Letter Grade','Participants are assigned a letter grade of "A","B","C","D", or "F"');
INSERT INTO EVENT_GRADE_FORMAT VALUES(null,'Percentage','Participants are assigned a numerical grade of 0-100');
UPDATE EVENT_GRADE_FORMAT SET egf_description = 'A Grade of PASS or FAIL is assigned' WHERE egf_format='PASS/FAIL';
SELECT * FROM EVENT_TYPE;

INSERT INTO EVENT(e_id,e_type,e_name,e_cost,e_date,e_enddate,e_egf_id,e_passing_grade)
VALUES(null,24,'Graphic Design Certification',200,'10-SEP-13 02.10.10.123000000 PM',null,21,'P');


SELECT * FROM EVENT_VIEW;

INSERT INTO APPLICATION_STATUS VALUES(1,'Denied');

INSERT INTO APPLICATION_STATUS VALUES(2,'Pending');


INSERT INTO APPLICATION_STATUS VALUES(3,'Approved');

SELECT * FROM APPLICATION INNER JOIN APPLICATION_STATUS ON status=as_id;
SELECT * FROM APPLICATION_VIEW;
SELECT * FROM EVENT_GRADE_FORMAT;

COMMIT;

