-- Create the database.
create database if not exists cs4370_mb_platform;

-- Use the created database.
use cs4370_mb_platform;


-- Create Award Table
create table if not exists MovieAward (
    AwardID int auto_increment not null unique,
    AwardName varchar(20) not null unique
)

create table if not exists ActorAward (
    ActorAwardID int auto_increment unique,
    ActorAwardName varchar(20) unique
)

-- Create Actor Table
create table if not exists Actor (
    ActorID int auto_increment not null unique,
    fName varchar(20) not null,
    lname varchar(20) not null,
    age int not null,
    ActorAwardID int auto_increment not null unique,


    foreign key(ActorAwardID) references ActorAward(ActorAwardID)
)

-- Create Movie Table
create table if not exists Movie (
    MovieID int auto_increment not null,
    Title varchar(50) not null,
    Genre varchar(30),
    ReleaseDate date,
    AwardID int,
    ReviewID int not null,

    foreign key (AwardID) references Award(AwardID),
    foreign key (ReviewID) references Review(ReviewID)
)

-- Create Review table
create table if not exists Review (
    ReviewID int auto_increment not null,
    rating varchar(10),
    comment varchar(200)
)

-- Create User table
create table if not exists User (
    UserID int auto_increment not null,
    fName varchar(20) not null,
    lName varchar(20) not null,
    ReviewID int not null,

    foreign key (ReviewID) references Review(ReviewID)
)


-- TODO
-- Insert Data into the Database

insert into user (username, password, firstName, lastName) values ('johnnyBoy', 'johnnyBoy1', 'Jon', 'Bon Jovi');
insert into user (username, password, firstName, lastName) values ('mitski12', 'mitski121', 'Mitsuki', 'Laycock');
insert into user (username, password, firstName, lastName) values ('cocoMel0n', 'c0c0', 'DadFrom', 'Cocomelon');
insert into user (username, password, firstName, lastName) values ('danTheDancer', 'dance420', 'Dan', 'Dannison');

insert into post (userId, postText, postDate) values (2, 'There is a bug like an angel stuck to the bottom of my glass with a little bit left', '2020-12-24 10:07:05');
insert into post (userId, postText, postDate) values (3, 'Sup. Life is crazy. #cocoLoco', '1997-07-16 11:11:11');
insert into post (userId, postText, postDate) values (4, 'UGH. *Caveman Noises* #cray', '0001-01-01 01:00:01');

insert into follow values (1,2);
insert into follow values (2,1);
insert into follow values (3,2);
insert into follow values (1,4);
insert into follow values (4,3);

insert into comment (postId, userId, commentText, commentDate) values (1, 3, 'So true girl', '2020-12-25 03:03:03');
insert into comment (postId, userId, commentText, commentDate) values (3, 1, 'Woah. You are old.', '1999-03-03 03:01:02');

insert into bookmark values (1,3);
insert into bookmark values (2,2);
insert into bookmark values (2,4);

insert into heart values (1,3);
insert into heart values (1,1);
insert into heart values (3,3);

insert into hashtag values ('#cray', 3);
insert into hashtag values ('#cocoLoco', 2);

