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
)

-- Create post table 
create table if not exists post (
    postId int auto_increment,
    userId int not null,
    postDate date,
    postText varchar(225) not null,

    primary key (postId),
    foreign key (userId)
)

-- Create comment table
create table if not exists comment (
    commentId int auto_increment,
    postId int not null,
    userId int not null,
    commentDate date,
    commentText varchar(500),

    foreign key(userId),
    foreign key(postId),
    primary key(commentId)
)

-- Create heart table
create table if not exists heart (
    postId int not null,
    userId int not null,

    primary key (postId, userId),
    foreign key(userId),
    foreign key(postId)
)

-- Create bookmark table
create table if not exists bookmark (
    postId int not null,
    userId int not null,

    primary key (postId, userId),
    foreign key(postId),
    foreign key(userId),
)

-- Create hashtag table ***
create table if not exists hashtag (
    hashTag varchar(225),
    postId int not null,

    primary key(hashTag, postId),
    foreign key(postId)
)

-- Create follow
create table if not exists follow (
    followerUserId int,
    followeeUserId int,

    primary key(followerUserId,followeeUserId),
    foreign key(followeeUserId),
    foreign key(followerUserId)
);
