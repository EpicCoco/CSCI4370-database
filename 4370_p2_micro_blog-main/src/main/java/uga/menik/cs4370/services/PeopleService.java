**
Copyright (c) 2024 Sami Menik, PhD. All rights reserved.

This is a project developed by Dr. Menik to give the students an opportunity to apply database concepts learned in the class in a real world project. Permission is granted to host a running version of this software and to use images or videos of this work solely for the purpose of demonstrating the work to potential employers. Any form of reproduction, distribution, or transmission of the software's source code, in part or whole, without the prior written consent of the copyright owner, is strictly prohibited.
*/
package uga.menik.cs4370.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicBoolean;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import org.springframework.web.context.annotation.SessionScope;

import uga.menik.cs4370.models.FollowableUser;
import uga.menik.cs4370.models.BasicPost;
import uga.menik.cs4370.models.Post;
import uga.menik.cs4370.models.User;
import uga.menik.cs4370.utility.Utility;
import uga.menik.cs4370.services.UserService;

/**
 * This service contains people related functions.
 */
@Service
@SessionScope
public class PeopleService {
    
    private final DataSource dataSource;

    public PeopleService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * This function should query and return all users that 
     * are followable. The list should not contain the user 
     * with id userIdToExclude.
     */
    public List<FollowableUser> getFollowableUsers(String userIdToExclude) throws SQLException{
        // Write an SQL query to find the users that are not the current user.

        // Run the query with a datasource.
        // See UserService.java to see how to inject DataSource instance and
        // use it to run a query.
        List<FollowableUser> followableUsers = new ArrayList<>();
        // Use the query result to create a list of followable users.
        // See UserService.java to see how to access rows and their attributes
        // from the query result.

        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Following line replaces the first place holder with username.
        pstmt.setString(1, userIdToExclude);

            try (ResultSet rs = pstmt.executeQuery()) {
            // Traverse the result rows one at a time.
            // Note: This specific while loop will only run at most once 
            // since username is unique.
                while (rs.next()) {
                    String userId = rs.getString("userId");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    boolean followed = followedInTable(userIdToExclude, rs.getString("userId"));
                    //String lastDate = "";
                    String lastDate = lastPostDate(userId);
                    if (lastDate == null) lastDate = "No posts";
                    FollowableUser userToAdd = new FollowableUser(userId, firstName, lastName, followed, lastDate);
                    followableUsers.add(userToAdd);
                } //while
            } //try (#2)
        } //try (#1)
        // Replace the following line and return the list you created.
        return followableUsers;

       // return Utility.createSampleFollowableUserList();
    }

    public Boolean changeFollowing(String followerId, String followeeId, Boolean isFollow) throws SQLException{
        String sql ="";
        if (isFollow) {
            sql = "DELETE FROM follow WHERE followerUserId = ? AND followeeUserId = ?";

        } else {
            sql = "INSERT INTO follow (followerUserId, followeeUserId) VALUES (?, ?)";
        }

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        // Following line replaces the first place holder with username.
            pstmt.setString(1, followerId);
            pstmt.setString(2, followeeId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }   
    }
    
    public Boolean followedInTable(String followerId, String followeeId) throws SQLException{
        String sql = "select count(followerUserId) from follow where followerUserId = ? AND followeeUserId = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, followerId);
            pstmt.setString(2, followeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }   
            }
        }
        return false;
    }
    
    public String lastPostDate(String userId) { //catch no exceptions here, flow out to controller
        String sql = "select max(postDate) from post where userId = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp(1);
                    if (timestamp != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy, hh:mm a");
                        return dateFormat.format(new Date(timestamp.getTime()));
                    }
                }
            }
        } catch (SQLException e) {
            // Handle exception
            System.out.println("error with lastPostDate");
            e.printStackTrace();
        }
        return null;
    }

}

