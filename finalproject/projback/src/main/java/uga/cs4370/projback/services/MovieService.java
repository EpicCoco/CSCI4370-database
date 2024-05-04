package uga.cs4370.projback.services;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.Timestamp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.cs4370.projback.models.Movie;
import uga.cs4370.projback.models.Review;

@Service
public class MovieService {
    
    private final DataSource dataSource;

    public MovieService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Movie> getAllMovies() throws SQLException {
        final String sql = "select * from movie";
        List<Movie> movies = new ArrayList<Movie>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // MovieID Title Genre ReleaseDate
                    String movieId = rs.getString("movieId");
                    String title = rs.getString("title");
                    String genre = rs.getString("genre");
                    String releaseDate = rs.getTimestamp("releaseDate").toString();
                    movies.add(new Movie(movieId, title, genre, releaseDate));
                }
            }
            //System.out.println("Num movies:");
            //System.out.println(movies.size());
            return movies;
        }
    }

    public List<Review> getMovieReviews(String movieId) throws SQLException {
        final String sql = "select * from review where movieId = ?";
        List<Review> reviews = new ArrayList<Review>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // reviewId rating text postDate movieId userId
                    String reviewId = rs.getString("reviewId");
                    String rating = rs.getString("rating");
                    String text = rs.getString("text");
                    String postDate = rs.getTimestamp("postDate").toString();
                    String userId = rs.getString("userId");
                    reviews.add(new Review(reviewId, rating, text, postDate, movieId, userId));
                }
            }
            //System.out.println("Num reviews:");
            //System.out.println(reviews.size());
            return reviews;
        }
    }

    public Movie getMovieInfo(String movieId) throws SQLException {
        final String sql = "select * from movie where movieId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // MovieID Title Genre ReleaseDate
                    String title = rs.getString("title");
                    String genre = rs.getString("genre");
                    String releaseDate = rs.getTimestamp("releaseDate").toString();
                    return new Movie(movieId, title, genre, releaseDate);
                }
            }
            //System.out.println("Num reviews:");
            //System.out.println(reviews.size());
            return null;
        }
    }

}
