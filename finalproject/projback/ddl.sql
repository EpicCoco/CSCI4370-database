-- Create the database.
create database if not exists projback;

-- Use the created database.
use projback;

-- Create User table
create table if not exists user (
    userID int auto_increment primary key,
    username varchar(55) not null,
    firstName varchar(20) not null,
    lastName varchar(20) not null,
    password varchar(255) not null
);

-- Create Movie Table
create table if not exists movie (
    movieID int auto_increment primary key,
    title varchar(50) not null,
    genre varchar(30) not null,
    releaseDate date not null
);

-- Create Actor Table
create table if not exists actor (
    actorID int auto_increment primary key,
    firstName varchar(20) not null,
    lastName varchar(20) not null,
    age int not null
);

-- Create Review table
create table if not exists review (
    reviewID int auto_increment primary key,
    rating varchar(10) not null,
    text varchar(200) not null,
    userID int,
    movieID int,
    postDate date,
    foreign key(userID) references user(userID) on delete cascade,
    foreign key(movieID) references movie(movieID)
);

-- Create Award Table
create table if not exists award (
    awardID int auto_increment primary key,
    awardName varchar(50) not null unique,
    movieID int not null,
    actorID int,
    foreign key(actorID) references actor(actorID),
    foreign key(movieID) references movie(MovieID)
);

-- Create ActorMovie Table
create table if not exists actorMovie (
    actorID int,
    movieID int,
    foreign key(actorID) references actor(actorID),
    foreign key(movieID) references movie(movieID)
);

-- created indexes
create index col1_index_01 on review(rating); 
create index col1_index_02 on review(movieID); 

