-- Create the database.
create database if not exists projback;

-- Use the created database.
use projback;

-- Create User table
create table if not exists User (
    userID int auto_increment primary key,
    fName varchar(20) not null,
    lName varchar(20) not null,
    password varchar(255) not null
);

-- Create Movie Table
create table if not exists Movie (
    movieID int auto_increment primary key,
    title varchar(50) not null,
    genre varchar(30) not null,
    releaseDate date not null
);

-- Create Actor Table
create table if not exists Actor (
    actorID int auto_increment primary key,
    fName varchar(20) not null,
    lname varchar(20) not null,
    age int not null,
);

-- Create Review table
create table if not exists Review (
    reviewID int auto_increment primary key,
    rating varchar(10) not null,
    text varchar(200) not null,
    userID int,
    movieID int,
    foreign key(userID) references User(userID),
    foreign key(movieID) references Movie(movieID)
);

-- Create Award Table
create table if not exists Award (
    awardID int auto_increment primary key,
    awardName varchar(20) not null unique,
    movieID int not null,
    actorID int,
    foreign key(ActorID) references Actor(ActorID),
    foreign key(MovieID) references Movie(MovieID)
);

-- Create ActorMovie Table
create table if not exists ActorMovie (
    actorID int,
    movieID int,
    foreign key(actorID) references Actor(actorID),
    foreign key(movieID) references Movie(movieID)
);

-- TODO
-- Insert 1000 row Data into the Database @Codey help

insert into Movie (title, genre, releaseDate) values
('The Shawshank Redemption', 'Drama', '1994-09-23'),
('The Godfather', 'Crime, Drama', '1972-03-24'),
('The Dark Knight', 'Action, Crime, Drama', '2008-07-18'),
('The Lord of the Rings: The Return of the King', 'Action, Adventure, Drama', '2003-12-17'),
('Pulp Fiction', 'Crime, Drama', '1994-10-14'),
-- blah blah blah
('Inception', 'Action, Adventure, Sci-Fi', '2010-07-16');
