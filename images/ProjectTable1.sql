
use project_db;

create table discussion(
disId integer NOT NUll auto_increment, 	
email varchar(30) not null, /*  Id of student only */
name varchar(25) not null,
title varchar(100) not null,
question varchar(500) not null,
vote int default 0,
disStatus int default 0,
noOfAnswer INT DEFAULT 0,
relatedTo varchar(50),
quesDate datetime default now(),
primary key (disId),

foreign key(email) references student(email) on update cascade
											 on delete cascade
);
ALTER TABLE DISCUSSION auto_increment=3001;

/*
drop table discussion;
describe discussion;

select * from student;
select * from discussionanswer;
delete  from discussion;
select * from discussion;
DROP TABLE discussion;
DROP TABLE discussionanswer;
describe discussion;
*/

INSERT INTO discussion (email,title,question,name,relatedTo)
VALUES ('sushant@gmail.com',' WHAT IS EM Wave?', 'What are the properties of EM Wave and how to represent it! I
search lot of times and not found any good notes. Please describe it!','Hari Gautam','BCT,BEX');

select * from discussion;
select * from discussion limit 0, 10;
alter table discussion 
drop column noOfAnswer;

select * from discussion;
UPDATE discussion
SET noOfAnswer = noOfAnswer+ 1
WHERE disID=3001;

(select t1.* from discussion t1) union (select * from discussionAnswer) ;
create table discussionAnswer
(
	ansId integer not null auto_increment,
    disId integer not null,
    email varchar(30) not null, /* Answer id can be student or teacher */
    name varchar(25) not null,
    answer varchar(800) not null,
    vote int  default 0,
    ansStatus int default 0,
    type varchar(10) not null, /* Place It from the session attribute */
    ansDate datetime default now(),
    Primary KEY(ansId),
    FOREIGN KEY(disId) REFERENCES discussion(disId) on update cascade
													on delete cascade
	
);
Alter TABLE DISCUSSIONANSWER auto_increment=5001;

/*
create table discussionStatus(
disId int,
ansId int,
studentEmail varchar(30),
teacherEmail varchar(30),
status int default 0,
foreign key (studentEmail) references student(email) on update cascade 
														on delete cascade,
foreign key (teacherEmail) references teacher(email) on update cascade 
														on delete cascade,
foreign key (disId) references discussion(disId) on update cascade 
														on delete cascade,
foreign key (ansId) references discussionAnswer(ansId) on update cascade 
														on delete cascade
);*/

/*
		Optimal relaton------  
	create table discussionanswerstatus(
		id int not null,
        email varchar(30) not null,
        status int default 0
    );
*//*
create table discussionAnswerStatus(

	disAnsStatusId int not null check(disAnsStatusId in (
															SELECT disId from discussion )),
    email varchar(30) not null check(email in( 
												SELECT email from student union select email from teacher)),
	
	status int default 0  check (status  in ('0','1', '2'))
);*/
create table discussionAnswerStatus(

	disAnsStatusId int not null ,
    email varchar(30) not null ,
	
	status int default 0 
);



insert into discussionAnswerStatus values(3006,'deepa@yahoo.com',5);

select * from discussionAnswerStatus;

drop table discussionanswerstatus;
drop table discussionanswer;
describe discussionAnswer;
select * from discussion;
				SELECT ID 
				FROM STUDENT 
				UNION
				SELECT ID 
				FROM TEACHER;

select * from discussionanswer;
	SELECT ID 
	FROM STUDENT 
    UNION
    SELECT ID 
    FROM TEACHER;

drop table discussionanswer;

insert into discussionanswer (disId,id,answer)
VALUES (3001,1001,'EM stands for Electromagnetic Wave! It have both current and magnetic field.');

describe discussionanswer;

create table broadCastDetail(
	broadcastId int not null primary key auto_increment,
    roomname varchar(50) not null,
    teacherName varchar(25) not null,
    email varchar(30) not null,
    relatedTo varchar(35) not null,
    title varchar(100) not null,
    description varchar(500) not null,
    status boolean default true,
    liveDatetime datetime not null DEFAULT now(),
	foreign key (email) references teacher(email) on update cascade
											 on delete cascade 
);
alter table broadcastdetail auto_increment=25001;

create table checkdefault
(
	email varchar(30) not null,
    CONSTRAINT chk_checkdefault CHECK (email in (SELECT email from teacher))
);
drop table checkdefault;
select * from checkdefault;

/*  iF THE ID OF ANY TABLE MATCH AS DISCUSSION,STUDENT, TEACHER, DISCUSSIONANSWER etc. then, it creates the problem */
create table uploadmaterials(

upload_id integer not null auto_increment,
email varchar(30) not null ,
extension varchar(10) not null,
name varchar(20) not null,
download_times integer default 0,
title varchar(100) not null,
valid boolean default false,
description varchar(200) not null,
type varchar(10) not null,
vote int default 0,
fileName varchar(50) not null, /* check the name of file should not get Long */
relatedTo varchar(50) not null,
primary key (upload_id)
);
ALTER TABLE uploadmaterials auto_increment=8001;
select * from uploadmaterials;
select count(*) as totalMaterials from uploadmaterials union select count(*) as totalDiscussion from discussion ;
drop table uploadmaterials;
INSERT INTO UPLOADMATERIALS 
(email,extension,category,title,description,path,relatedTo) 
VALUES(?,?,?,?,?,?,?);
drop table uploadmaterials;


create table trainvideos
(
	videoId integer not null primary key auto_increment,
    trainId integer not null,
    name varchar(25) not null,
    email varchar(30) not null,
    title varchar(100) not null,
    description varchar(500) not null,
    relatedTo varchar(25) not null,
    videoLike int4 default 0,
    videoDislike int4 default 0,
    filename varchar(60) not null,
    date datetime not null DEFAULT now(),
    foreign key (trainId) references broadCastDetail(broadcastId) on delete cascade
																  on update cascade,
    foreign key (email) references teacher(email)  on delete cascade
												   on update cascade
                                                   
);
alter table trainvideos  auto_increment= 1501;

create table commentVideo
(
	commentId integer not null primary key auto_increment,
    videoId integer not null,
    name varchar(30) not null, /* name of person who comment */
    type varchar(10) not null, /* can be student,teacher,admin */
    email varchar(30) not null,
    status boolean default true,
    commentDesc varchar(150) not null,
    date datetime not null default now(),
     foreign key (videoId) references trainVideos(videoId) on delete cascade
														   on update cascade
);
alter table commentVideo auto_increment=2501;

