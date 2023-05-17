create table movie(
    movietitle VARCHAR2(50) not null,
    theater VARCHAR2(50) not null,
    moviedate TIMESTAMP not null,
    runningtime number(4) not null
);

create table movie2(
    movietitle VARCHAR2(50) not null,
    theater VARCHAR2(50) not null,
    moviedate TIMESTAMP not null,
    runningtime number(4) not null
);

create table movie3(
    movietitle VARCHAR2(50) not null,
    theater VARCHAR2(50) not null,
    moviedate TIMESTAMP not null,
    runningtime number(4) not null
);

create table movie4(
    movietitle VARCHAR2(50) not null,
    theater VARCHAR2(50) not null,
    moviedate TIMESTAMP not null,
    runningtime number(4) not null
);



create table Ticketing(
    tcode int primary key,
    clientid VARCHAR2(50),
    MOVIETITLE VARCHAR2(50),
    MOVIEDATE Timestamp,
    seatnumber VARCHAR2(50),
    foreign key (clientid) REFERENCES client(clientid) on delete cascade
);


create table client(
    clientid VARCHAR2(50) primary key,
    clientpassword varchar2(50) not null,
    clientname VARCHAR2(50) not null,
    birthdate number(10) not null,
    phonenumber VARCHAR2(50) not null,
    join_date DATE
);


Insert into movie values ('아바타','1관','2023-01-28 09:13','192');
Insert into movie values ('아바타','1관','2023-01-28 17:35','192');
Insert into movie values ('아바타','1관','2023-01-29 08:27','192');
Insert into movie values ('아바타','1관','2023-01-29 16:44','192');
Insert into movie values ('아바타','1관','2023-01-30 10:33','192');
Insert into movie values ('아바타','1관','2023-01-30 15:25','192');
Insert into movie values ('아바타','1관','2023-01-31 16:24','192');
Insert into movie values ('아바타','1관','2023-02-01 07:20','192');
Insert into movie values ('아바타','1관','2023-02-02 12:36','192');
Insert into movie values ('아바타','1관','2023-02-03 10:57','192');
Insert into movie values ('아바타','1관','2023-02-03 16:33','192');
Insert into movie values ('아바타','1관','2023-02-04 12:11','192');
Insert into movie values ('아바타','1관','2023-02-04 19:12','192');



Insert into movie2 values ('슬램덩크','2관','2023-01-27 09:22','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-27 14:47','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-28 11:56','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-28 19:33','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-29 07:14','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-29 17:51','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-30 06:22','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-30 17:39','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-31 09:34','124');
Insert into movie2 values ('슬램덩크','2관','2023-01-31 18:23','124');
Insert into movie2 values ('슬램덩크','2관','2023-02-01 07:24','124');
Insert into movie2 values ('슬램덩크','2관','2023-02-01 10:22','124');
Insert into movie2 values ('슬램덩크','2관','2023-02-03 09:33','124');
Insert into movie2 values ('슬램덩크','2관','2023-02-03 16:14','124');



Insert into movie3 values ('교섭','3관','2023-01-29 15:24','108');
Insert into movie3 values ('교섭','3관','2023-01-29 19:02','108');
Insert into movie3 values ('교섭','3관','2023-01-30 07:52','108');
Insert into movie3 values ('교섭','3관','2023-01-30 17:33','108');
Insert into movie3 values ('교섭','3관','2023-01-31 08:46','108');
Insert into movie3 values ('교섭','3관','2023-01-31 21:18','108');
Insert into movie3 values ('교섭','3관','2023-02-01 08:31','108');
Insert into movie3 values ('교섭','3관','2023-02-01 19:23','108');
Insert into movie3 values ('교섭','3관','2023-02-02 07:14','108');
Insert into movie3 values ('교섭','3관','2023-02-02 11:34','108');
Insert into movie3 values ('교섭','3관','2023-02-03 09:12','108');
Insert into movie3 values ('교섭','3관','2023-02-03 18:23','108');
Insert into movie3 values ('교섭','3관','2023-02-04 10:42','108');
Insert into movie3 values ('교섭','3관','2023-02-04 20:21','108');



Insert into movie4 values ('유령','4관','2023-01-30 09:14','133');
Insert into movie4 values ('유령','4관','2023-01-30 13:12','133');
Insert into movie4 values ('유령','4관','2023-01-31 08:53','133');
Insert into movie4 values ('유령','4관','2023-01-31 17:46','133');
Insert into movie4 values ('유령','4관','2023-02-01 09:51','133');
Insert into movie4 values ('유령','4관','2023-02-01 14:22','133');
Insert into movie4 values ('유령','4관','2023-02-02 11:23','133');
Insert into movie4 values ('유령','4관','2023-02-02 17:14','133');
Insert into movie4 values ('유령','4관','2023-02-03 12:32','133');
Insert into movie4 values ('유령','4관','2023-02-03 18:56','133');
Insert into movie4 values ('유령','4관','2023-02-04 14:17','133');
Insert into movie4 values ('유령','4관','2023-02-04 19:22','133');
Insert into movie4 values ('유령','4관','2023-02-05 06:33','133');
Insert into movie4 values ('유령','4관','2023-02-05 12:14','133');


commit;

drop table ticketing;
