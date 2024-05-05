package uga.cs4370.projback.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import uga.cs4370.projback.models.*;

@Service
public class UserService {
    private final DataSource dataSource;

    private final BCryptPasswordEncoder passwordEncoder;

    private User loggedInUser = null;


    public UserService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> getAllUsers() throws SQLException {
        final String sql = "select * from user";
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String userId = rs.getString("userID");
                    String username = rs.getString("username");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    users.add(new User(userId, username, firstName, lastName));
                }
            }
            return users;
        }
    }


public boolean authenticate(String username, String password) throws SQLException {
        final String sql = "select * from user where username = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Following line replaces the first place holder with username.
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Traverse the result rows one at a time.
                // Note: This specific while loop will only run at most once 
                // since username is unique.
                while (rs.next()) {
                    // Note: rs.get.. functions access attributes of the current row.
                    String storedPasswordHash = rs.getString("password");
                    boolean isPassMatch = passwordEncoder.matches(password, storedPasswordHash);
                    // Note: 
                    if (isPassMatch) {
                        String userId = rs.getString("userId");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");

                        // Initialize and retain the logged in user.
                        loggedInUser = new User(userId, username, firstName, lastName);
                    }
                    return isPassMatch;
                }
            }
        }
        return false;
    }

/**
     * Logs out the user.
     */
    public void unAuthenticate() {
        loggedInUser = null;
    }

    /**
     * Checks if a user is currently authenticated.
     */
    public boolean isAuthenticated() {
        return loggedInUser != null;
    }

    /**
     * Retrieves the currently logged-in user.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean registerUser(String username, String password, String firstName, String lastName) throws SQLException {
        final String registerSql = "insert into user (username, password, firstName, lastName) values (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
                PreparedStatement registerStmt = conn.prepareStatement(registerSql)) {
            // Following lines replace the placeholders 1-4 with values.
            registerStmt.setString(1, username);
            registerStmt.setString(2, passwordEncoder.encode(password));
            registerStmt.setString(3, firstName);
            registerStmt.setString(4, lastName);

            // Execute the statement and check if rows are affected.
            int rowsAffected = registerStmt.executeUpdate();
            return rowsAffected > 0;
        }
    }


    public List<Review> reviewList(String userId) throws SQLException{
        List<Review> reviewList = new ArrayList<>();

        String sql = "select * from review where userId = ? order by postDate desc limit 20";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String reviewId = rs.getString("reviewId");
                    String rating = rs.getString("rating");
                    String text = rs.getString("text");
                    String postDate = rs.getString("postDate");
                    String movieId = rs.getString("movieId");
                    Review newReview = new Review(reviewId, rating, text, postDate, movieId, userId);
                    reviewList.add(newReview);
                }
            }
        return reviewList;
        }
    }

    public User getUserById(int userId) throws SQLException {
        final String sql = "select * from user where userId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following lines replace the placeholders 1-4 with values.
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                String userIdStr = Integer.toString(userId);
                String username = rs.getString("username");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return new User(userIdStr, username, firstName, lastName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUser(int userId) throws SQLException {
        final String sql = "delete from user where userID = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                int numChanged = pstmt.executeUpdate();
                if (numChanged > 0) return true;
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(String firstName, String lastName, String userId) throws SQLException {
        final String sql = "update user set firstName = ?, lastName = ? where userID = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, userId);
                int numChanged = pstmt.executeUpdate();
                if (numChanged > 0) return true;
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
}
