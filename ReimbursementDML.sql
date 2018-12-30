INSERT INTO DEPARTMENT VALUES(null,'Administration');
INSERT INTO USER_TYPE VALUES(null,3,'CEO','The CEO of the company');
INSERT INTO USER_TYPE VALUES(null,0,'Intern','An intern for the company');
INSERT INTO USR VALUES(99999,'Stan','Lee','stanlee','stanlee@mailinator.com',99999,21,21,'stanlee','Y','N');
select * from DEPARTMENT;
SELECT * FROM USER_TYPE;
SELECT * FROM USER_VIEW;
select * from usr;
DELETE FROM usr WHERE usr_firstname != 'Stan';
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

INSERT INTO EVENT VALUES(null,4,'Graphic Design Certification',200,'10-SEP-02 02.10.10.123000000 PM',null,1,'P');
SELECT * FROM EVENT_VIEW;


COMMIT;