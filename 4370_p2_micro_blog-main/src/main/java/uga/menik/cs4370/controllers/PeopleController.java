/**
Copyright (c) 2024 Sami Menik, PhD. All rights reserved.

This is a project developed by Dr. Menik to give the students an opportunity to apply database concepts learned in the class in a real world project. Permission is granted to host a running version of this software and to use images or videos of this work solely for the purpose of demonstrating the work to potential employers. Any form of reproduction, distribution, or transmission of the software's source code, in part or whole, without the prior written consent of the copyright owner, is strictly prohibited.
*/
package uga.menik.cs4370.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.menik.cs4370.models.FollowableUser;
import uga.menik.cs4370.services.PeopleService;
import uga.menik.cs4370.services.UserService;
import uga.menik.cs4370.utility.Utility;

/**
 * Handles /people URL and its sub URL paths.
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final UserService userService;
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    /**
     * Serves the /people web page.
     * 
     * Note that this accepts a URL parameter called error.
     * The value to this parameter can be shown to the user as an error message.
     * See notes in HashtagSearchController.java regarding URL parameters.
     */
    @GetMapping
    public ModelAndView webpage(@RequestParam(name = "error", required = false) String error) {
        // See notes on ModelAndView in BookmarksController.java.
        ModelAndView mv = new ModelAndView("people_page");

        // Following line populates sample data.
        // Use UserService to access logged in userId to exclude.
        //List<FollowableUser> followableUsers = Utility.createSampleFollowableUserList();
        try {
            String userId = userService.getLoggedInUser().getUserId();
            List<FollowableUser> followableUsers = peopleService.getFollowableUsers(userId);
            for (FollowableUser fl: followableUsers) {
                System.out.println("last Active date: " + fl.isLastActiveDate());
            }
            //System.out.println(followableUser

            mv.addObject("users", followableUsers);
        } catch(SQLException exception) {
            System.out.println("Error");
        }
        

        // If an error occured, you can set the following property with the
        // error message to show the error message to the user.
        // An error message can be optionally specified with a url query parameter too.
        String errorMessage = error;
        mv.addObject("errorMessage", errorMessage);

        // Enable the following line if you want to show no content message.
        // Do that if your content list is empty.
        // mv.addObject("isNoContent", true);
        
        return mv;
    }

    /**
     * This function handles user follow and unfollow.
     * Note the URL has parameters defined as variables ie: {userId} and {isFollow}.
     * Follow and unfollow is handled by submitting a get type form to this URL 
     * by specifing the userId and the isFollow variables.
     * Learn more here: https://www.w3schools.com/tags/att_form_method.asp
     * An example URL that is handled by this function looks like below:
     * http://localhost:8081/people/1/follow/false
     * The above URL assigns 1 to userId and false to isFollow.
     */
    @GetMapping("{userId}/follow/{isFollow}")
    public String followUnfollowUser(@PathVariable("userId") String userId,
            @PathVariable("isFollow") Boolean isFollow) throws SQLException{
        System.out.println("User is attempting to follow/unfollow a user:");
        System.out.println("\tuserId: " + userId);
        System.out.println("\tisFollow: " + isFollow);

        try {
            String currentUser = userService.getLoggedInUser().getUserId();
            Boolean worked = peopleService.changeFollowing(currentUser, userId, peopleService.followedInTable(currentUser, userId));
            System.out.println("\tDatabase Changed: " + worked);
            return "redirect:/people";
        } catch (Error e) {
            System.out.println("Follow Database Not Changed Due To Error");
            String message = URLEncoder.encode("Failed to (un)follow the user. Please try again.",
                StandardCharsets.UTF_8);
        return "redirect:/people?error=" + message;
        }
        
        // Redirect the user if the comment adding is a success.

        
        // Redirect the user with an error message if there was an error.
        
    }


}

