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

    public void makeReview(String reviewId, String rating, String text, String movieId, String userId) throws SQLException {
        // this SQL statement is used to push new posts into the database, and it is called from the
        // home page: http://localhost:8081/
        final String sql = "insert into review (reviewId, rating, text, postDate, movieId, userId) values (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String currentDate = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            pstmt.setString(1, reviewId);
            pstmt.setString(2, rating);
            pstmt.setString(3, text);
            pstmt.setString(4, currentDate);
            pstmt.setString(5, movieId);
            pstmt.setString(6, userId);

            int numChanged = pstmt.executeUpdate();
            if (numChanged > 0) {
                System.out.println("Review creation successful.");
            } else {
                System.out.println("Review creation failed.");
            }
        }
    }

    public List<Review> getReviewByUser(String userId) throws SQLException{
        final String sql = "select * from review where userId = ? order by postDate desc";
        List<Review> reviewList = new ArrayList<Review>();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
    // Following line replaces the first place holder with username.
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String reviewId = rs.getString("reviewId");
                    String rating = rs.getString("rating");
                    String text = rs.getString("text");
                    String postDate = rs.getTimestamp("postDate").toString();
                    postDate = formatDate(postDate);
                    String movieId = rs.getString("movieId");
                    reviewList.add(new Review(reviewId,rating,text,postDate,movieId,userId));
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
