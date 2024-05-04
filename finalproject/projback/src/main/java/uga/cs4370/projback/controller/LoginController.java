package uga.cs4370.projback.controller;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import uga.cs4370.projback.ProjbackApplication;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uga.cs4370.projback.services.UserService;

/**
 * This is the controler that handles /login URL.
 */
@Controller
@RequestMapping("/api/login")
public class LoginController {

    // UserService has user login and registration related functions.
    private final UserService userService;

    /**
     * See notes in AuthInterceptor.java regarding how this works 
     * through dependency injection and inversion of control.
     */
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    /**
     * This handles the /login form submission.
     * See notes in HomeController.java regardig /createpost form submission handler.
     */
    @PostMapping
    public ResponseEntity<String> login(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        boolean isAuthenticated = false;

        try {
            isAuthenticated = userService.authenticate(username, password);
        } catch (SQLException e) {
            // Redirect back to the login page with an error message if authentication fails.
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        if (isAuthenticated) {
            // Redirect to home page if authentication is successful.
            return ResponseEntity.ok("Successfully logged in");
        } else {
            // Redirect back to the login page with an error message if authentication fails.
            return ResponseEntity.badRequest().build();
        }
    }
    
}
