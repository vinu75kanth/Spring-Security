package com.security.Security.Controller;

import com.security.Security.Model.Users;
import com.security.Security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index() {
        return "Hello World";
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.registerUser(user);
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping ("/login")
    public String login(@RequestBody Users user) {
        System.out.println(user);
        return userService.verify(user);
    }
}
