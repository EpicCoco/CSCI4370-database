package uga.cs4370.projback.controller;

import uga.cs4370.projback.models.Actor;
import uga.cs4370.projback.models.Movie;
import uga.cs4370.projback.models.Award;
import uga.cs4370.projback.services.ActorService;
import uga.cs4370.projback.services.MovieService; // TODO
import uga.cs4370.projback.services.AwardService; // TODO

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
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
     */
    @GetMapping("/actors/{actorId}/movies")
    public ModelAndView getMoviesWithActor(@PathVariable("actorId") int actorId) {
        ModelAndView mv = new ModelAndView("actor_movies");
        Actor actor = actorService.getActorById(actorId);
        List<Movie> moviesWithActor = movieService.getMoviesByActor(actor);
        mv.addObject("actor", actor);
        mv.addObject("movies", moviesWithActor);
        return mv;
    }

    /**
     * 
     * Get all awards for an actor
     */
    @GetMapping("/actors/{actorId}/awards")
    public ModelAndView getAwardsForActor(@PathVariable("actorId") int actorId) {
        ModelAndView mv = new ModelAndView("actor_awards");
        Actor actor = actorService.getActorById(actorId);
        List<Award> awardsForActor = awardService.getAwardsForActor(actor);
        mv.addObject("actor", actor);
        mv.addObject("awards", awardsForActor);
        return mv;
    }

    /**
     * 
     * Get actor’s info (name, age, etc)
     */
    @GetMapping("/actors/{actorId}")
    public ModelAndView getActorInfo(@PathVariable("actorId") int actorId) {
        ModelAndView mv = new ModelAndView("actor_info");
        Actor actor = actorService.getActorById(actorId); // TODO
        mv.addObject("actor", actor);
        return mv;
    }
}
