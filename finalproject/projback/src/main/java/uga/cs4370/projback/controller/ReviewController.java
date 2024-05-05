package uga.cs4370.projback.controller;

import uga.cs4370.projback.models.Actor;
import uga.cs4370.projback.models.Movie;
import uga.cs4370.projback.models.Review;
import uga.cs4370.projback.models.Award;
import uga.cs4370.projback.services.ActorService;
import uga.cs4370.projback.services.MovieService; 
import uga.cs4370.projback.services.AwardService;
import uga.cs4370.projback.services.UserService;
import uga.cs4370.projback.services.ReviewService;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/review")
public class ReviewController {
    private final UserService userService;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<String> makeReviewPage(@RequestParam("userID") String userID,
            @RequestParam("movieID") String movieID, @RequestParam("rating") String rating,
            @RequestParam("text") String text) throws SQLException {
        try {
            if (reviewService.makeReview(rating, text, movieID, userID)) {
                return ResponseEntity.ok("Successfully created review");
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}

