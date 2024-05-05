package uga.cs4370.projback.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uga.cs4370.projback.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uga.cs4370.projback.services.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

    @GetMapping("/logged-in")
    public ResponseEntity<String> profileOfLoggedInUser() throws SQLException {
        System.out.println("User is attempting to view profile of the logged in user.");
        return profileOfSpecificUser(userService.getLoggedInUser().getUserId());
    }

     @GetMapping("/{userId}")
    public ResponseEntity<String> profileOfSpecificUser(@PathVariable("userId") String userId) throws SQLException {
        System.out.println("User is attempting to view profile: " + userId);
        // See notes on ModelAndView in BookmarksController.java.
        if (!userService.reviewList(userId).equals(null)) {
            return ResponseEntity.ok("posts_page");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
