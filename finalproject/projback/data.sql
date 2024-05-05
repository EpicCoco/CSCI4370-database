insert into movie (movieID, title, genre, releaseDate) values ('1', 'Pacific Rim', 'Action', '2013-07-13');
insert into movie (movieID, title, genre, releaseDate) values ('2', 'Asteroid City', 'Comedy', '2023-06-15');
insert into movie (movieID, title, genre, releaseDate) values ('3', 'Over The Hedge', 'Comedy', '2006-05-19');
insert into movie (movieID, title, genre, releaseDate) values ('4', 'Ice Age', 'Comedy', '2002-03-15');
insert into movie (movieID, title, genre, releaseDate) values ('5', 'The Others', 'Horror', '2001-08-02');
insert into movie (movieID, title, genre, releaseDate) values ('6', 'Underworld', 'Horror', '2003-09-19');
insert into movie (movieID, title, genre, releaseDate) values ('7', 'Tron', 'Action', '1982-07-09');
insert into movie (movieID, title, genre, releaseDate) values ('8', 'Get Out', 'Horror', '2017-02-24');
insert into movie (movieID, title, genre, releaseDate) values ('9', 'Shazam', 'Action', '2019-04-05');
insert into movie (movieID, title, genre, releaseDate) values ('10', 'Endgame', 'Action', '2019-04-26');

insert into actor (actorID, firstName, lastName, age) values ('1', 'Charlie', 'Day', 32);
insert into actor (actorID, firstName, lastName, age) values ('91', 'codey', 'Bortellini', 41);
insert into actor (actorID, firstName, lastName, age) values ('2', 'Charlie', 'Hunnam', 37);
insert into actor (actorID, firstName, lastName, age) values ('3', 'Scarlett', 'Johansson', 39);
insert into actor (actorID, firstName, lastName, age) values ('4', 'Jason', 'Shwartzman', 45);
insert into actor (actorID, firstName, lastName, age) values ('5', 'Bruce', 'Willis', 50);
insert into actor (actorID, firstName, lastName, age) values ('6', 'Steve', 'Carell', 47);
insert into actor (actorID, firstName, lastName, age) values ('7', 'John', 'Leguizamo', 35);
insert into actor (actorID, firstName, lastName, age) values ('8', 'Chris', 'Wedge', 60);
insert into actor (actorID, firstName, lastName, age) values ('9', 'Nicole', 'Kidman', 51);
insert into actor (actorID, firstName, lastName, age) values ('10', 'Alakina', 'Mann', 28);
insert into actor (actorID, firstName, lastName, age) values ('11', 'Kate', 'Beckinsale', 26);
insert into actor (actorID, firstName, lastName, age) values ('12', 'Theo', 'James', 31);
insert into actor (actorID, firstName, lastName, age) values ('13', 'Jeff', 'Bridges', 62);
insert into actor (actorID, firstName, lastName, age) values ('14', 'Bruce', 'Boxleitner', 65);
insert into actor (actorID, firstName, lastName, age) values ('15', 'Daniel', 'Kaluuya', 34);
insert into actor (actorID, firstName, lastName, age) values ('16', 'Allison', 'Williams', 38);
insert into actor (actorID, firstName, lastName, age) values ('17', 'Zachary', 'Levi', 42);
insert into actor (actorID, firstName, lastName, age) values ('18', 'Asher', 'Angel', 29);
insert into actor (actorID, firstName, lastName, age) values ('19', 'Robert', 'Downey', 49);
insert into actor (actorID, firstName, lastName, age) values ('20', 'Chris', 'Evans', 39);

insert into actorMovie (actorID, movieID) values ('1', '1');
insert into actorMovie (actorID, movieID) values ('91', '1');
insert into actorMovie (actorID, movieID) values ('2', '1');
insert into actorMovie (actorID, movieID) values ('3', '2');
insert into actorMovie (actorID, movieID) values ('4', '2');
insert into actorMovie (actorID, movieID) values ('5', '3');
insert into actorMovie (actorID, movieID) values ('6', '3');
insert into actorMovie (actorID, movieID) values ('7', '4');
insert into actorMovie (actorID, movieID) values ('8', '4');
insert into actorMovie (actorID, movieID) values ('9', '5');
insert into actorMovie (actorID, movieID) values ('10', '5');
insert into actorMovie (actorID, movieID) values ('11', '6');
insert into actorMovie (actorID, movieID) values ('12', '6');
insert into actorMovie (actorID, movieID) values ('13', '7');
insert into actorMovie (actorID, movieID) values ('14', '7');
insert into actorMovie (actorID, movieID) values ('15', '8');
insert into actorMovie (actorID, movieID) values ('16', '8');
insert into actorMovie (actorID, movieID) values ('17', '9');
insert into actorMovie (actorID, movieID) values ('18', '9');
insert into actorMovie (actorID, movieID) values ('19', '10');
insert into actorMovie (actorID, movieID) values ('20', '10');


insert into award (awardID, awardName, movieID, actorID) values ('1', 'Canadian Screen Award for Best Actor', '3', '6');
insert into award (awardID, awardName, movieID) values ('2', 'Best Screenplay', '1');
insert into award (awardID, awardName, movieID) values ('3', 'Most Intense Film', '8');
insert into award (awardID, awardName, movieID, actorID) values ('4', 'Golden Boot Actor Award', '7', '13');
insert into award (awardID, awardName, movieID, actorID) values ('5', 'European Film Award for Best Supporting Performance', '2', '4');
insert into award (awardID, awardName, movieID, actorID) values ('6', 'Doris Duke Performing Artist Award', '3', '6');
insert into award (awardID, awardName, movieID, actorID) values ('7', 'Empire Award for Best Newcomer', '6', '11');
insert into award (awardID, awardName, movieID, actorID) values ('8', 'IIFA Award for Star Debut of the Year', '10', '20');
insert into award (awardID, awardName, movieID, actorID) values ('9', 'Golden Horse Award for Best Performer', '4', '8');
insert into award (awardID, awardName, movieID, actorID) values ('10', 'Golden Lotus Actor Award', '10', '19');
insert into award (awardID, awardName, movieID, actorID) values ('11', 'Golden Boot Actor Award', '7', '14');
insert into award (awardID, awardName, movieID, actorID) values ('12', 'Golden Boot Actor Award', '2', '4');
insert into award (awardID, awardName, movieID, actorID) values ('13', 'Doris Duke Performing Artist Award', '3', '5');
insert into award (awardID, awardName, movieID, actorID) values ('14', 'Doris Duke Performing Artist Award', '10', '19');
insert into award (awardID, awardName, movieID, actorID) values ('15', 'Doris Duke Performing Artist Award', '9', '18');
insert into award (awardID, awardName, movieID, actorID) values ('16', 'Doris Duke Performing Artist Award', '4', '8');
insert into award (awardID, awardName, movieID, actorID) values ('17', 'Doris Duke Performing Artist Award', '1', '1');

insert into user (userID, username, firstName, lastName, password) values ('1', 'filmer5000', 'Stanley', 'Brown', 'stanpass');
insert into user (userID, username, firstName, lastName, password) values ('2', 'cinematic28', 'John', 'Johnson', 'johnpass');
insert into user (userID, username, firstName, lastName, password) values ('3', 'cinophile15', 'Shawn', 'Riley', 'shawnpass');
insert into user (userID, username, firstName, lastName, password) values ('4', 'pulpfictionlover', 'Shawnathan', 'Shawnson', 'secretpass16');
insert into user (userID, username, firstName, lastName, password) values ('5', 'goodfellasbest', 'Ryan', 'Williams', 'rw526');
insert into user (userID, username, firstName, lastName, password) values ('6', 'filmfanatic234', 'Spike', 'Spiegel', 'spacecowboy');
insert into user (userID, username, firstName, lastName, password) values ('7', 'lovecinema', 'Fae', 'Valentine', 'bebop');
insert into user (userID, username, firstName, lastName, password) values ('8', 'cinneman', 'Shinji', 'Ikari', 'getintherobot');
insert into user (userID, username, firstName, lastName, password) values ('9', '657films', 'Scotty', 'Smalls', 'sandlotbest');
insert into user (userID, username, firstName, lastName, password) values ('10', '3000tron', 'Timmy', 'Timmons', 'yourkillingmesmalls');

DELIMITER $$

CREATE PROCEDURE generate_reviews()
BEGIN
    DECLARE i INT DEFAULT 0;
    WHILE i < 100000 DO
        INSERT INTO review (reviewID, rating, text, userID, movieID, postDate)
        VALUES (i + 1,
                FLOOR(RAND() * 5) + 1,
                CASE FLOOR(RAND() * 9)
                    WHEN 0 THEN 'This was bad!'
                    WHEN 1 THEN 'I like how the main character had lots of emotion!'
                    WHEN 2 THEN 'Some things were good, some were bad, some were meh'
                    WHEN 3 THEN 'Really a sucker for this movie! Hope to see more from this director!'
                    WHEN 4 THEN 'I think the lighting in this movie was terrible!'
                    WHEN 5 THEN 'Booo! So bad! Rather would read a book'
                    WHEN 6 THEN 'This movie was awesome!'
                    WHEN 7 THEN 'Awkward movie, bad actors, and terrible director. Really a downfall for this type of genre'
                    WHEN 8 THEN 'Something is wrong with the movie'
                    ELSE 'Move wus bad. Plese write bettre script next ime…'
                END,
                FLOOR(RAND() * 10) + 1,
                FLOOR(RAND() * 10) + 1,
                DATE_ADD('2020-01-01', INTERVAL FLOOR(RAND() * 1826) DAY));
        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

CALL generate_reviews();