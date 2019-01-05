INSERT INTO DEPARTMENT VALUES(21,'Administration');
INSERT INTO USER_JOB_TYPE VALUES(1,'Department Head');
INSERT INTO USER_JOB_TYPE VALUES(2,'Benco');
INSERT INTO USER_JOB_TYPE VALUES(3,'Benco Supervisor');
INSERT INTO USER_JOB_TYPE VALUES(4,'Employee');
INSERT INTO USER_JOB_TYPE VALUES(5,'Intern');
INSERT INTO USER_JOB VALUES(1,'CEO','The CEO of the company',1);
INSERT INTO USER_JOB VALUES(2,'Writing Intern','An intern for the company that works as a writer.',5);
INSERT INTO USER_JOB VALUES(3,'Head of Writing','The head of the writing department.',1);

INSERT INTO USR 
(usr_id,usr_firstname,usr_lastname,usr_username,usr_email,usr_direct_supervisor,usr_department, usr_job,usr_password)
VALUES(99999,'Stan','Lee','stanlee','stanlee@mailinator.com',99999,21,1,'$2a$10$5Nm7Khp8bTZgip78u80Zv.OMUwN1lzbsx52tMyouizSKvY0tN2Mem');


INSERT INTO EVENT_TYPE VALUES(1,80,'University Course');
INSERT INTO EVENT_TYPE VALUES(2,60,'Seminar');
INSERT INTO EVENT_TYPE VALUES(3,75,'Certification Preparation Class');
INSERT INTO EVENT_TYPE VALUES(4,100,'Certification');
INSERT INTO EVENT_TYPE VALUES(5,90,'Technical Training');
INSERT INTO EVENT_TYPE VALUES(6,30,'Other');

INSERT INTO EVENT_GRADE_FORMAT VALUES(1,'PASS/FAIL',null);
INSERT INTO EVENT_GRADE_FORMAT VALUES(2,'Letter Grade','Participants are assigned a letter grade of "A","B","C","D", or "F"');
INSERT INTO EVENT_GRADE_FORMAT VALUES(3,'Percentage','Participants are assigned a numerical grade of 0-100');
UPDATE EVENT_GRADE_FORMAT SET egf_description = 'A Grade of PASS or FAIL is assigned' WHERE egf_format='PASS/FAIL';

INSERT INTO EVENT(e_id,e_type,e_name,e_date,e_enddate,e_egf_id,e_passing_grade)
VALUES(null,4,'Graphic Design Certification','10-SEP-13 02.10.10.123000000 PM',null,1,'P');

INSERT INTO APPLICATION_STATUS VALUES(1,'Denied');

INSERT INTO APPLICATION_STATUS VALUES(2,'Pending');


INSERT INTO APPLICATION_STATUS VALUES(3,'Approved');


COMMIT;

