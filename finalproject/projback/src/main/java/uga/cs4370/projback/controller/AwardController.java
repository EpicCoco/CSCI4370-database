package uga.cs4370.projback.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.cs4370.projback.models.Actor;
import uga.cs4370.projback.models.Movie;
import uga.cs4370.projback.models.Review;
import uga.cs4370.projback.models.Award;
import uga.cs4370.projback.services.MovieService;
import uga.cs4370.projback.services.UserService;
import uga.cs4370.projback.services.AwardService;

public class AwardController {
    
    private final AwardService awardService;

    @Autowired
    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    /**
     * 
     * Get the info for a specific award
     */
    @GetMapping("/award/info/{awardId}")
    public Award getAwardInfo(@PathVariable("awardId") String awardId) {
        try {
            return awardService.getAwardInfo(awardId);
        } catch (SQLException exception) {
            System.out.println("Error retrieving info for award with id " + awardId + ", SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/awards/{movieId}")
    public List<Award> getMovieAwards(@PathVariable("movieId") String movieId) {
        try {
            return awardService.getMovieAwards(movieId);
        } catch (SQLException exception) {
            System.out.println("Error retrieving awards for movie with id: " + movieId + ", SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/awards/{actorId}")
    public List<Award> getActorAwards(@PathVariable("actorId") String actorId) {
        try {
            return awardService.getActorAwards(actorId);
        } catch (SQLException exception) {
            System.out.println("Error retrieving reviews for actor with id: " + actorId + ", SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

}
