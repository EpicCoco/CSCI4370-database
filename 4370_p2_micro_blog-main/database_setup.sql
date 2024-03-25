-- Create the database.
create database if not exists cs4370_mb_platform;

-- Use the created database.
use cs4370_mb_platform;

-- Create the user table.
create table if not exists user (
    userId int auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    primary key (userId),
    unique (username),
    constraint userName_min_length check (char_length(trim(userName)) >= 2),
    constraint firstName_min_length check (char_length(trim(firstName)) >= 2),
    constraint lastName_min_length check (char_length(trim(lastName)) >= 2)
);

-- Create post table 
create table if not exists post (
    postId int not null auto_increment,
    userId int not null,
    postDate datetime,
    postText varchar(225) not null,

    primary key (postId),
    foreign key (userId) references user(userId)
);

-- Create comment table
create table if not exists comment (
    commentId int not null auto_increment,
    postId int not null,
    userId int not null,
    commentDate datetime,
    commentText varchar(500),

    primary key(commentId),
    foreign key(userId) references user(userId),
    foreign key(postId) references post(postId)
);

-- Create heart table
create table if not exists heart (
    postId int not null,
    userId int not null,

    primary key (postId, userId),
    foreign key(userId) references user(userId),
    foreign key(postId) references post(postId)
);

-- Create bookmark table
create table if not exists bookmark (
    postId int not null,
    userId int not null,

    primary key (postId, userId),
    foreign key(postId) references post(postId),
    foreign key(userId) references user(userId)
);

-- Create hashtag table ***
create table if not exists hashtag (
    hashTag varchar(225),
    postId int not null,

    primary key(hashTag, postId),
    constraint hashTag_check check (hashTag like'%#%'),
    foreign key(postId) references post(postId)
);

-- Create follow
create table if not exists follow (
    followerUserId int,
    followeeUserId int,

    primary key(followerUserId,followeeUserId),
    foreign key(followeeUserId) references user(userId),
    foreign key(followerUserId) references user(userId)
);


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

insert into comment values (1, 3, 'So true girl', 2020-12-25 03:03:03);
insert into comment values (3, 1, 'Woah. You are old.', 1999-03-03 03:01:02);

insert into bookmark values (1,3);
insert into bookmark values (2,2);
insert into bookmark values (2,4);

insert into heart values (1,3);
insert into heart values (1,1);
insert into heart values (3,3);

insert into hashtag values ('#cray', 3);
insert into hashtag values ('cocoLoco', 2);

