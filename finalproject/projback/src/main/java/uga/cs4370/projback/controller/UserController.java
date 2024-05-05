package uga.cs4370.projback.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uga.cs4370.projback.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<User> profileOfLoggedInUser() throws SQLException {
        return profileOfSpecificUser(userService.getLoggedInUser().getUserId());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> profileOfSpecificUser(@PathVariable("userId") String userId) throws SQLException {
        User tempUser = userService.getUserById(Integer.parseInt(userId));
        if (tempUser != null) return ResponseEntity.ok(tempUser);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reviews/{userId}")
    public List<Review> userReviews(@PathVariable("userId") String userId) throws SQLException {
        try {
            return userService.reviewList(userId);
        } catch (SQLException exception) {
            System.out.println("Error retrieving review list for user with id: " + userId + ", SQL Exception");
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("userId") String userId) throws SQLException {
        if (userService.deleteUser(Integer.parseInt(userId))) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
