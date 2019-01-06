INSERT INTO DEPARTMENT VALUES(21,'Administration');
INSERT INTO USER_JOB_TYPE VALUES(1,'Department Head');
INSERT INTO USER_JOB_TYPE VALUES(2,'Benco');
INSERT INTO USER_JOB_TYPE VALUES(3,'Benco Supervisor');
INSERT INTO USER_JOB_TYPE VALUES(4,'Employee');
INSERT INTO USER_JOB_TYPE VALUES(5,'Intern');
INSERT INTO USER_JOB VALUES(1,'CEO','The CEO of the company',1);
INSERT INTO USER_JOB VALUES(2,'Writing Intern','An intern for the company that works as a writer.',5);
INSERT INTO USER_JOB VALUES(3,'Writing Department Head','The head of the writing department.',1);


INSERT INTO USER_JOB VALUES(null,'Head of Editing','The head of the editing department.',1);
INSERT INTO USER_JOB VALUES(null,'Head of Illustration','The head of the Illustration department.',1);
INSERT INTO USER_JOB VALUES(null,'Head of Production','The head of the Production department.',1);
INSERT INTO USER_JOB VALUES(null,'Head of Editing','The head of the editing department.',1);
INSERT INTO USER_JOB VALUES(null,'Head of Marketing','The head of the marketing department.',1);
INSERT INTO USER_JOB VALUES(null,'Head of Benefits','The head of the benefits department.',1);

INSERT INTO USER_JOB VALUES(null,'Benefits Coordinator','A member of the benefits department',2);


INSERT INTO USER_JOB VALUES(null,'Writing Team Leader','The leader of a team in the writing department.',4);
INSERT INTO USER_JOB VALUES(null,'Editing Team Leader','The leader of a team in the editing department',4);
INSERT INTO USER_JOB VALUES(null,'Illustration Team Leader','The leader of a team in the illustration department',4);
INSERT INTO USER_JOB VALUES(null,'Marketing Team Leader','The leader of a team in the marketing department',4);
INSERT INTO USER_JOB VALUES(null,'Production Team Leader','The leader of a team in the production department',4);
INSERT INTO USER_JOB VALUES(null,'Writer','A writer for a team',4);
INSERT INTO USER_JOB VALUES(null,'Editor','An editor for a team',4);
INSERT INTO USER_JOB VALUES(null,'Advertisor','An advertisor',4);

INSERT INTO USER_JOB VALUES(null,'Illustrator','An illustrator for a team',4);
INSERT INTO USER_JOB VALUES(null,'Shift Manager','A manger of a shift',4);

INSERT INTO USER_JOB VALUES(null,'Editing Intern','Editing Intern',5);
INSERT INTO USER_JOB VALUES(null,'Illustration Intern','An intern illustrator',5);
INSERT INTO USER_JOB VALUES(null,'Advertising Intern','An intern advertisor',5);


SELECT * FROM USER_VIEW;
COMMIT;

INSERT INTO USR 
(usr_id,usr_firstname,usr_lastname,usr_username,usr_email,usr_direct_supervisor,usr_department, usr_job,usr_password)
VALUES(99999,'Stan','Lee','stanlee','stanlee@mailinator.com',99999,21,1,'$2a$10$5Nm7Khp8bTZgip78u80Zv.OMUwN1lzbsx52tMyouizSKvY0tN2Mem');

INSERT INTO USR 
(usr_id,usr_firstname,usr_lastname,usr_username,usr_email,usr_direct_supervisor,usr_department, usr_job,usr_password)
VALUES(99998,'Jack','Kirby','jackkirby','jackkirby@mailinator.com',99999,21,3,'$2a$10$5Nm7Khp8bTZgip78u80Zv.OMUwN1lzbsx52tMyouizSKvY0tN2Mem');

DELETE FROM USR WHERE usr_username = 'jackkirby';
SELECT * FROM USER_VIEW;
SELECT * FROM USR;

SELECT * FROM user_view;

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

INSERT INTO APPLICATION_STATUS VALUES(0,'Denied');

INSERT INTO APPLICATION_STATUS VALUES(1,'Submitted');

INSERT INTO APPLICATION_STATUS VALUES(2,'Approved by Direct Supervisor');

INSERT INTO APPLICATION_STATUS VALUES(3,'Approved by Department Head');

INSERT INTO APPLICATION_STATUS VALUES(4,'Approved');



COMMIT;

