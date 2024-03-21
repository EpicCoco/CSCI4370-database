/**
Copyright (c) 2024 Sami Menik, PhD. All rights reserved.

This is a project developed by Dr. Menik to give the students an opportunity to apply database concepts learned in the class in a real world project. Permission is granted to host a running version of this software and to use images or videos of this work solely for the purpose of demonstrating the work to potential employers. Any form of reproduction, distribution, or transmission of the software's source code, in part or whole, without the prior written consent of the copyright owner, is strictly prohibited.
*/
package uga.menik.cs4370.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import uga.menik.cs4370.models.FollowableUser;
import uga.menik.cs4370.utility.Utility;

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
    public List<FollowableUser> getFollowableUsers() throws SQLException {
        // Write an SQL query to find the users that are not the current user.

        // Run the query with a datasource.
        // See UserService.java to see how to inject DataSource instance and
        // use it to run a query.

        // Use the query result to create a list of followable users.
        // See UserService.java to see how to access rows and their attributes
        // from the query result.
        // Check the following createSampleFollowableUserList function to see 
        // how to create a list of FollowableUsers.

        // Replace the following line and return the list you created.
        List<FollowableUser> followableUsers = new ArrayList<FollowableUser>();

        final String sql = "select * from user";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                // Traverse the result rows one at a time.
                // Note: This specific while loop will only run at most once 
                // since username is unique.
                ArrayList<String> dates = new ArrayList<>();
                dates.add("Mar 07, 2024, 10:54 PM");
                dates.add("Mar 05, 2024, 11:00 AM");
                dates.add("Mar 06, 2024, 09:30 AM");
                dates.add("Mar 02, 2024, 08:15 PM");
                int i = 0;
                System.out.println("PEOPLE TEST");
                while (rs.next()) {
                    // Note: rs.get.. functions access attributes of the current row.
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String date = dates.get(i%4);
                    followableUsers.add(new FollowableUser(Integer.toString(i), firstName, lastName,
                    true, date));
                    //followableUsers.add(new FollowableUser("4", "Papa", "Johns",
                    //false, "Mar 02, 2024, 08:15 PM"));
                    i++;
                }
            }
        }
        return followableUsers;

       // return Utility.createSampleFollowableUserList();
    }

}
