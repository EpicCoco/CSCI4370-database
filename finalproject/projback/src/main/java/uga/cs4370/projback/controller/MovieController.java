package uga.cs4370.projback.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import uga.cs4370.projback.models.Actor;
import uga.cs4370.projback.models.Movie;
import uga.cs4370.projback.models.Review;
import uga.cs4370.projback.models.Award;
import uga.cs4370.projback.services.MovieService;
import uga.cs4370.projback.services.UserService;

/**
 * Initialization for controller archetype and movie route
 */
@RestController
@RequestMapping("/api/movie")
@CrossOrigin
public class MovieController {
    
    // private fields for necessary services
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Get all movies
     */
    @GetMapping
    public List<Movie> getMovies() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            return movies;
        } catch (SQLException exception) {
            System.out.println("Error retrieving all movies: SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/reviews/{movieId}")
    public List<Review> getMovieReviews(@PathVariable("movieId") String movieId) {
        try {
            List<Review> reviews = movieService.getMovieReviews(movieId);
            return reviews;
        } catch (SQLException exception) {
            System.out.println("Error retrieving reviews for movie with id: " + movieId + ", SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/info/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        try {
            return movieService.getMovieInfo(movieId);
        } catch (SQLException exception) {
            System.out.println("Error retrieving info for movie with id: " + movieId + ", SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/avg/{movieId}")
    public String getMovieAvgRating(@PathVariable("movieId") String movieId) {
        try {
            return movieService.getMovieAvgRating(movieId);
        } catch (SQLException exception) {
            System.out.println("Error calculating avg rating for movie with id: " + movieId + ", SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/genrecount")
    public String getReviewCountByGenre(@RequestParam("genre") String genre) {
        try {
            System.out.println("ATTEMPTING ROUTE CALL");
            return movieService.getCountByGenre(genre);
        } catch (SQLException exception) {
            System.out.println("Error calculating review count for genre with name: " + genre + ", SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/getpass")
    public String generateHash(@RequestParam("password") String password) throws UnsupportedEncodingException {
        // Passwords should have at least 3 chars.
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}