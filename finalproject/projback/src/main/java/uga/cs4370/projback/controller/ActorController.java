package uga.cs4370.projback.controller;

import uga.cs4370.projback.models.Actor;
import uga.cs4370.projback.models.Movie;
import uga.cs4370.projback.models.Award;
import uga.cs4370.projback.services.ActorService;
import uga.cs4370.projback.services.MovieService; 
import uga.cs4370.projback.services.AwardService;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actor/")
@CrossOrigin
public class ActorController {

    private final ActorService actorService;
    private final MovieService movieService;
    private final AwardService awardService;

    @Autowired
    public ActorController(ActorService actorService, MovieService movieService, AwardService awardService) {
        this.actorService = actorService;
        this.movieService = movieService;
        this.awardService = awardService;
    }

    /**
     * 
     * Get all movies with a certain actor in it
     * @throws SQLException 
     */
    @GetMapping("{actorId}/movies")
    public List<Movie> getMoviesWithActor(@PathVariable("actorId") String actorId) throws SQLException {
        
        Actor actor = actorService.getActorById(actorId);
        List<Movie> moviesWithActor = movieService.getMoviesByActor(actor);

        return moviesWithActor;
        //return null;
    }

    /**
     * Get all actors of a movie
     * 
     */
    @GetMapping("{movieId}/actors")
    public List<Actor> getActorsOfMovie(@PathVariable("movieId") String movieId) throws SQLException {
        Movie movie = movieService.getMovieById(movieId);
        List<Actor> actorsInMovie = actorService.getActorsByMovie(movie);
        return actorsInMovie;
    }

    /**
     * 
     * Get all awards for an actor
     * @throws SQLException 
     */
    @GetMapping("{actorId}/awards")
    public List<Award> getActorAwards(@PathVariable("actorId") String actorId) throws SQLException {
        
        Actor actor = actorService.getActorById(actorId);
        List<Award> awardsForActor = awardService.getActorAwards(actorId); 

        return awardsForActor;
        
        // return null;
    }

    /**
     * 
     * Get actor’s info (name, age, etc)
     */
    @GetMapping("{actorId}")
    public Actor getActorInfo(@PathVariable("actorId") String actorId) {
        try {
            Actor actor = actorService.getActorById(actorId);
            return actor;
        } catch (Exception e) {
            System.err.println("Failed to get actor");
        }
        return null;        
    }
}
