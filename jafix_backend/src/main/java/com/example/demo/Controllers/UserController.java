package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    @GetMapping
    public User findUser(@RequestParam String login, @RequestParam String password){
        User response = null;
        for(User user : userRepository.findUsersByLogin(login)){
            if (user.getPassword().equals(password)){
                response = user;
            }
        }
        return response;
    }
    @PostMapping
    public boolean authorization(@RequestParam String login, @RequestParam String password, @RequestParam String name, @RequestParam String surname){
            userRepository.addUser(login,password,name,surname);
            return true;
    }
}
