--All queries used in the web app:

-- Query description:
-- This query gets the actors using the movie, in other words, list all actors associated
-- with a given movie
-- URL path: "http://localhost:8080/api/actor/{movieId}/actors"
select a.* from actor a join actorMovie am on a.actorID = am.actorID where am.movieID = ?;

-- Query description:
-- This query deletes a user from the user table
-- URL path: "http://localhost:8080/api/user/{userId}"
delete from user where userID = ?

-- Query description:
-- This query gets a specific actor given their actor ID, used to search for specific actors
-- URL path: "http://localhost:8080/api/actor/{actorId}/movies"
select * from actor where actorID = ?;

-- Query description:
-- This query lists all or any awards that an actor may have recieved for a given movie
-- URL path: "http://localhost:8080/api/actor/{actorId}/awards
select * from award where actorID = ?;

-- Query description:
-- This query lists the average rating from a given movie located within the review table
-- URL path: http://localhost:8080/api/movie/avg/{movieId}
select avg(rating) from review where movieID = ?;


-- Query description:
-- This query gets all information about a given movie 
-- URL path: http://localhost:8080/api/actor/{movieId}/actors
select * from movie where movieID = ?;


-- Query description:
-- This query retrieves all movies that have a certain actor in it. In other words,
-- this retrieves movies that have the same actor in it
-- URL path: http://localhost:8080/api/{actorId}/movies
select m.* from movie m join actorMovie mc on m.movieID = mc.movieID where mc.actorID = ?;

-- Query description:
-- This query gets all information from a given movie
-- URL path: http://localhost:8080/api/movie/info/{movieId}
select * from movie where movieID = ?;

-- Query description:
-- This query retrieves information from a review about a certain movie, limiting the 
-- number of seen reviews by 20
-- URL path: http://localhost:8080/api/movie/reviews/{movieId}
select * from review where movieID = ? order by postDate desc limit 20; 

-- Query description:
-- This query gets all information from all given movie
-- URL path: http://localhost:8080/api/movie/
select * from movie;

-- Query description:
-- This query counts the number of awards within the given awards
-- URL path: http://localhost:8080/api/award/count
select count(awardID) from award where awardName = ?;

-- Query description:
-- This query gets all information from an award that was given to a certain actor
-- URL path: http://localhost:8080/api/award/actor/{actorId}
select * from award where actorID = ?;

-- Query description:
-- This query gets all award information from a specific movie
-- URL path: http://localhost:8080/api/award/movie/{movieId}
select * from award where movieID = ?;

-- Query description:
-- This query gets all award information from a specific award
-- URL path: http://localhost:8080/api/award/info/{awardId}
select * from award where awardID = ?;

-- Query description:
-- This query gets all information from a review given by a specific user, it 
-- shows the information from most recent to oldest reviews
-- URL path: http://localhost:8080/api/user/reviews/{userId}
select * from review where userId = ? order by postDate desc;

-- Query description:
-- This query gets all information from a user
-- URL path: http://localhost:8080/api/user/reviews/{userId}
select * from user where userId = ?;
