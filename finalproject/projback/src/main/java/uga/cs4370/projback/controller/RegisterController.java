package uga.cs4370.projback.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uga.cs4370.projback.services.UserService;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/api/register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) throws UnsupportedEncodingException {
        // Passwords should have at least 3 chars.
        if (password.trim().length() < 3) {
            return ResponseEntity.badRequest().build();
        }
        try {
            boolean registrationSuccess = userService.registerUser(username,
                    password, firstName, lastName);
            if (registrationSuccess) {
                // If the registration worked redirect to the login page.
                return ResponseEntity.ok("successfully registered");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            System.out.println("Error registering user: ");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
