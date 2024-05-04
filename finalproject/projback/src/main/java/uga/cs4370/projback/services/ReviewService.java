package uga.cs4370.projback.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import uga.cs4370.projback.models.*;

@Service
public class ReviewService {

    private final DataSource dataSource;
    private final UserService userService;

    public ReviewService(DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }

    public boolean makeReview(String rating, String text, String movieId, String userId) throws SQLException {
        // this SQL statement is used to push new posts into the database, and it is called from the
        // home page: http://localhost:8081/
        final String sql = "insert into review (rating, text, postDate, movieID, userID) values (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String currentDate = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            pstmt.setString(1, rating);
            pstmt.setString(2, text);
            pstmt.setString(3, currentDate);
            pstmt.setString(4, movieId);
            pstmt.setString(5, userId);

            int numChanged = pstmt.executeUpdate();
            if (numChanged > 0) {
                System.out.println("Review creation successful.");
                return true;
            } else {
                System.out.println("Review creation failed.");
                return false;
            }
        }
    }

    public List<Review> getReviewByUser(String userId) throws SQLException{
        final String sql = "select * from review where userID = ? order by postDate desc";
        List<Review> reviewList = new ArrayList<Review>();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
    // Following line replaces the first place holder with username.
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String reviewId = rs.getString("reviewID");
                    String rating = rs.getString("rating");
                    String text = rs.getString("text");
                    String postDate = rs.getTimestamp("postDate").toString();
                    postDate = formatDate(postDate);
                    String movieId = rs.getString("movieID");
                    reviewList.add(new Review(reviewId,rating,text,postDate,movieId,userId));
                }
            }
        }
        return reviewList;   
    }

    public List<Review> getReviewByMovie(String movieId) throws SQLException{
        final String sql = "select * from review where movieID = ? order by postDate desc";
        List<Review> reviewList = new ArrayList<Review>();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
    // Following line replaces the first place holder with username.
            pstmt.setString(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String reviewId = rs.getString("reviewID");
                    String rating = rs.getString("rating");
                    String text = rs.getString("text");
                    String postDate = rs.getTimestamp("postDate").toString();
                    postDate = formatDate(postDate);
                    String userID = rs.getString("userID");
                    reviewList.add(new Review(reviewId,rating,text,postDate,movieId,userID));
                }
            }
        }
        return reviewList;   
    }

    public String formatDate(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Format date into desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy, hh:mm a");
        String outputDateStr;
        try {
            outputDateStr = outputFormat.format(inputFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return "error";
        }
        return outputDateStr;
    }
}
