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

insert into user (userID, username, firstName, lastName, password) values ('1', 'filmer5000', 'Stanley', 'Brown', '$2a$10$wIpF61JIwd9j4gJ6hIb/PO51JVrgKKBFqiLtTfOdTacUff9jRwHYy');
insert into user (userID, username, firstName, lastName, password) values ('2', 'cinematic28', 'John', 'Johnson', '$2a$10$j2m0k/zH2iJPFXOU6.5AtuQJvJKhu1yMgLHqETO768JWh/uQ0uaku');
insert into user (userID, username, firstName, lastName, password) values ('3', 'cinophile15', 'Shawn', 'Riley', '$2a$10$BF/pxUa4p/IKwiLH6Yw66ub7v1yl4wrwOyN4wPq48brvoCE0jls8W');
insert into user (userID, username, firstName, lastName, password) values ('4', 'pulpfictionlover', 'Shawnathan', 'Shawnson', '$2a$10$.gT8HnLjJvJ0j8zWwsU42OAOPR3sNhMNH4LKOG/LfvLuk0GF1gaKm');
insert into user (userID, username, firstName, lastName, password) values ('5', 'goodfellasbest', 'Ryan', 'Williams', '$2a$10$pWzQmCiw3c9.0.hZL0To/.Sh096GTT6k7Dor9PxNC4lh5XuVY9wvy');
insert into user (userID, username, firstName, lastName, password) values ('6', 'filmfanatic234', 'Spike', 'Spiegel', '$2a$10$lBV0mjqCwkwB4LW7YMtwOO7xW3B5xcu2mTuyur0BMQmzF74003YBa');
insert into user (userID, username, firstName, lastName, password) values ('7', 'lovecinema', 'Fae', 'Valentine', '$2a$10$boMqcQm.Y7m1jMexcomKRe2bakX9sN8rmTWcaeX08sVZgWplc04bK');
insert into user (userID, username, firstName, lastName, password) values ('8', 'cinneman', 'Shinji', 'Ikari', '$2a$10$Y7MsNOJ5X5gohFXvef2J4uRXJS82RL7lSMKp0NnpdfQ4Fn8PIogWO');
insert into user (userID, username, firstName, lastName, password) values ('9', '657films', 'Scotty', 'Smalls', '$2a$10$cCD8mfYDcgh6ZCxlKy8Ed.rKgaonDhUxqoqkEg3tttEPaaW9vgB/O');
insert into user (userID, username, firstName, lastName, password) values ('10', '3000tron', 'Timmy', 'Timmons', '$2a$10$D4gxE8eFMwp1d0wS9eaLI.9h23sgCIrgTYaxrS0XO0lJYYcBvhN92');

-- passwords for each user
-- filmer5000: stanpass
-- cinematic28: johnpass
-- cinophile15: shawnpass
-- pulpfictionlover: secretpass16
-- goodfellasbest: rw526
-- filmfanatic234: spacecowboy
-- lovecinema: bebop
-- cinneman: getintherobot
-- 657films: sandlotbest
-- 3000tron: yourkillingmesmalls

DELIMITER $$

CREATE PROCEDURE generate_reviews11()
BEGIN
    DECLARE i INT DEFAULT 0;
    WHILE i < 10000 DO
        INSERT INTO review (reviewID, rating, text, userID, movieID, postDate)
        VALUES (i + 1,
                FLOOR(RAND() * 5) + 1,
                CASE FLOOR(RAND() * 20) -- Generate random index for different review texts
                    WHEN 0 THEN 'This was bad!'
                    WHEN 1 THEN 'I like how the main character had lots of emotion!'
                    WHEN 2 THEN 'Some things were good, some were bad, some were meh'
                    WHEN 3 THEN 'Really a sucker for this movie! Hope to see more from this director!'
                    WHEN 4 THEN 'I think the lighting in this movie was terrible!'
                    WHEN 5 THEN 'Booo! So bad! Rather would read a book'
                    WHEN 6 THEN 'This movie was awesome!'
                    WHEN 7 THEN 'Awkward movie, bad actors, and terrible director. Really a downfall for this type of genre'
                    WHEN 8 THEN 'Something is wrong with the movie'
                    WHEN 9 THEN 'I loved the special effects but the plot was weak'
                    WHEN 10 THEN 'The ending was unexpected and left me wanting more'
                    WHEN 11 THEN 'Great performances from the cast, especially the lead actor'
                    WHEN 12 THEN 'The soundtrack really added to the atmosphere of the movie'
                    WHEN 13 THEN 'It felt like a waste of time'
                    WHEN 14 THEN 'Amazing cinematography, but the story fell flat'
                    WHEN 15 THEN 'I could not stop laughing throughout the entire movie!'
                    WHEN 16 THEN 'The plot twists kept me on the edge of my seat'
                    WHEN 17 THEN 'Worst movie I have ever seen!'
                    WHEN 18 THEN 'The characters were so relatable'
                    WHEN 19 THEN 'I was disappointed by the lack of originality'
                    WHEN 20 THEN 'This movie was not good at all'
                    WHEN 21 THEN 'The dialogue was so cheesy'
                    WHEN 22 THEN 'I fell asleep halfway through the movie'
                    WHEN 23 THEN 'This movie dragged on for too long'
                    WHEN 24 THEN 'The special effects were impressive'
                    WHEN 25 THEN 'The story was captivating from start to finish'
                    WHEN 26 THEN 'I could not connect with any of the characters'
                    WHEN 27 THEN 'The ending left me with more questions than answers'
                    WHEN 28 THEN 'The acting was subpar'
                    WHEN 29 THEN 'The plot was predictable'
                    ELSE 'This movie was not my cup of tea'
                END,
                FLOOR(RAND() * 10) + 1,
                FLOOR(RAND() * 10) + 1,
                DATE_ADD('2020-01-01', INTERVAL FLOOR(RAND() * 730) DAY));
        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

CALL generate_reviews11();