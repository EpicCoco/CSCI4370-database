-- Create the database.
create database if not exists projback;

-- Use the created database.
use projback;

-- Create Award Table
create table if not exists Award (
    AwardID int auto_increment primary key,
    AwardName varchar(20) not null unique,
    MovieID int not null,
    ActorID int,
    foreign key(ActorID) references Actor(ActorID),
    foreign key(MovieID) references Movie(MovieID)
);

-- Create Actor Table
create table if not exists Actor (
    ActorID int auto_increment primary key,
    fName varchar(20) not null,
    lname varchar(20) not null,
    age int not null,
    ActorAwardID int,
    foreign key(ActorAwardID) references ActorAward(ActorAwardID)
);

-- Create Movie Table
create table if not exists Movie (
    MovieID int auto_increment primary key,
    Title varchar(50) not null,
    Genre varchar(30),
    ReleaseDate date,
    AwardID int,
    ReviewID int not null,
    foreign key (AwardID) references Award(AwardID),
    foreign key (ReviewID) references Review(ReviewID)
);

-- Create Review table
create table if not exists Review (
    ReviewID int auto_increment primary key,
    rating varchar(10),
    comment varchar(200)
);

-- Create User table
create table if not exists User (
    UserID int auto_increment primary key,
    fName varchar(20) not null,
    lName varchar(20) not null,
    ReviewID int not null,
    foreign key (ReviewID) references Review(ReviewID)
);


-- TODO
-- Insert 1000 row Data into the Database @Codey help

insert into Movie (Title, Genre, ReleaseDate, AwardID, ReviewID) values
('The Shawshank Redemption', 'Drama', '1994-09-23', 1, 1),
('The Godfather', 'Crime, Drama', '1972-03-24', 2, 2),
('The Dark Knight', 'Action, Crime, Drama', '2008-07-18', 3, 3),
('The Lord of the Rings: The Return of the King', 'Action, Adventure, Drama', '2003-12-17', 4, 4),
('Pulp Fiction', 'Crime, Drama', '1994-10-14', 5, 5),
-- blah blah blah

('Inception', 'Action, Adventure, Sci-Fi', '2010-07-16', 6, 6);
