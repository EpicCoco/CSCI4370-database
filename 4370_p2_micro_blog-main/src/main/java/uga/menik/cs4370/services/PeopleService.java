/**
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

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import uga.menik.cs4370.models.FollowableUser;
import uga.menik.cs4370.utility.Utility;

/**
 * This service contains people related functions.
 */
@Service
public class PeopleService {
    
    private final DataSource dataSource;

    /**
     * UserService constructor with dataSource fed in
     * @param dataSource
     */
    //@Autowired
    public PeopleService(DataSource dataSource) {
        this.dataSource = dataSource;
    } //UserService

    /**
     * This function should query and return all users that 
     * are followable. The list should not contain the user 
     * with id userIdToExclude.
     */
    public List<FollowableUser> getFollowableUsers(String userIdToExclude) throws SQLException {
        // Write an SQL query to find the users that are not the current user.
        final String sql = "select * from user where userId != ?";
        // Run the query with a datasource.
        // See UserService.java to see how to inject DataSource instance and
        // use it to run a query.
        List<FollowableUser> followableUsers = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
                //set placeholder to userIdToExclude
                statement.setString(1, userIdToExclude);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String userId = rs.getString("userId");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    //boolean isFollowed = rs.getBoolean("isFollowed");
                    //String lastActiveDate = rs.getString("lastActiveDate");

                    //create followable user
                    FollowableUser fu = new FollowableUser(userId, firstName, lastName, true, "Mar 07, 2024, 10:57 PM");
                    followableUsers.add(fu);
                } //while
            } //try
        } //try

        // Use the query result to create a list of followable users.
        // See UserService.java to see how to access rows and their attributes
        // from the query result.
        // Check the following createSampleFollowableUserList function to see 
        // how to create a list of FollowableUsers.

        // Replace the following line and return the list you created.
        //return Utility.createSampleFollowableUserList();
        return followableUsers;
    }

}
