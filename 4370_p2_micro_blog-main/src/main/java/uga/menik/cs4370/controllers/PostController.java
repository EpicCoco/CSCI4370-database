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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.menik.cs4370.services.PeopleService;
// My imports
import uga.menik.cs4370.services.UserService;
import uga.menik.cs4370.services.PostService;
import uga.menik.cs4370.models.User;

import uga.menik.cs4370.models.ExpandedPost;
import uga.menik.cs4370.utility.Utility;

/**
 * Handles /post URL and its sub urls.
 */
@Controller
@RequestMapping("/post")
public class PostController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    /**
     * This function handles the /post/{postId} URL.
     * This handlers serves the web page for a specific post.
     * Note there is a path variable {postId}.
     * An example URL handled by this function looks like below:
     * http://localhost:8081/post/1
     * The above URL assigns 1 to postId.
     * 
     * See notes from HomeController.java regardig error URL parameter.
     */
    @GetMapping("/{postId}")
    public ModelAndView webpage(@PathVariable("postId") String postId,
            @RequestParam(name = "error", required = false) String error) {
        System.out.println("The user is attempting to view post with id: " + postId);
        // See notes on ModelAndView in BookmarksController.java.
        ModelAndView mv = new ModelAndView("posts_page");

        // Following line populates sample data.
        // You should replace it with actual data from the database.
        //List<ExpandedPost> posts = Utility.createSampleExpandedPostWithComments();
        List<ExpandedPost> posts = List.of(postService.getPostWithComments(postId));
        mv.addObject("posts", posts);

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
     * Handles comments added on posts.
     * See comments on webpage function to see how path variables work here.
     * This function handles form posts.
     * See comments in HomeController.java regarding form submissions.
     */
    @PostMapping("/{postId}/comment")
    public String postComment(@PathVariable("postId") String postId,
            @RequestParam(name = "comment") String comment) {
        System.out.println("The user is attempting add a comment:");
        System.out.println("\tpostId: " + postId);
        System.out.println("\tcomment: " + comment);

        User currentUser = userService.getLoggedInUser();
        String userId = currentUser.getUserId();
        try {
            postService.makeComment(postId, userId, comment);
            return "redirect:/post/" + postId;
        } catch (SQLException exception) {
            System.out.println("Comment creation error: SQL Exception");
            System.out.println(exception.getMessage());
        }

        // Redirect the user if the comment adding is a success.
        // return "redirect:/post/" + postId;

        // Redirect the user with an error message if there was an error.
        String message = URLEncoder.encode("Failed to post the comment. Please try again.",
                StandardCharsets.UTF_8);
        return "redirect:/post/" + postId + "?error=" + message;
    }

    /**
     * Handles likes added on posts.
     * See comments on webpage function to see how path variables work here.
     * See comments in PeopleController.java in followUnfollowUser function regarding 
     * get type form submissions and how path variables work.
     */
    @GetMapping("/{postId}/heart/{isAdd}")
    public String addOrRemoveHeart(@PathVariable("postId") String postId,
            @PathVariable("isAdd") Boolean isAdd) {
        System.out.println("The user is attempting add or remove a heart:");
        System.out.println("\tpostId: " + postId);
        System.out.println("\tisAdd: " + isAdd);

        // BEGINNING OF MY CODE
        User currentUser = userService.getLoggedInUser();
        String userId = currentUser.getUserId();
        System.out.println("CURRENT USER ID:"+ userId);
        try {
            if (isAdd) {
                // attempt to add a like if the post is not currently liked by currentUser
                postService.addLike(postId, userId);
                return "redirect:/post/" + postId;
            } else {
                // attempt to remove the like if the post is currenty liked
                postService.removeLike(postId, userId);
                return "redirect:/post/" + postId;
            }
        } catch (SQLException exception) {
            System.out.println("SQL ERROR");
            System.out.println(exception.getMessage());
            // Redirect the user with an error message if there was an error.
            String message = URLEncoder.encode("Failed to (un)like the post. Please try again.",
                StandardCharsets.UTF_8);
            return "redirect:/post/" + postId + "?error=" + message;
        }
        
        // Redirect the user if the comment adding is a success.
        // return "redirect:/post/" + postId;
        
        // END OF MY CODE
    }

    /**
     * Handles bookmarking posts.
     * See comments on webpage function to see how path variables work here.
     * See comments in PeopleController.java in followUnfollowUser function regarding 
     * get type form submissions.
     */
    @GetMapping("/{postId}/bookmark/{isAdd}")
    public String addOrRemoveBookmark(@PathVariable("postId") String postId,
            @PathVariable("isAdd") Boolean isAdd) {
        System.out.println("The user is attempting add or remove a bookmark:");
        System.out.println("\tpostId: " + postId);
        System.out.println("\tisAdd: " + isAdd);

        // BEGINNING OF MY CODE
        User currentUser = userService.getLoggedInUser();
        String userId = currentUser.getUserId();
        try {
            if (isAdd) {
                // attempt to add a like if the post is not currently bookmarked by currentUser
                postService.addBookmark(postId, userId);
                return "redirect:/post/" + postId;
            } else {
                // attempt to remove the like if the post is currenty bookmarked
                postService.removeBookmark(postId, userId);
                return "redirect:/post/" + postId;
            }
        } catch (SQLException exception) {
            System.out.println("SQL ERROR");
            System.out.println(exception.getMessage());
            // Redirect the user with an error message if there was an error.
            String message = URLEncoder.encode("Failed to (un)bookmark the post. Please try again.",
                StandardCharsets.UTF_8);
            return "redirect:/post/" + postId + "?error=" + message;
        }
    }

}
