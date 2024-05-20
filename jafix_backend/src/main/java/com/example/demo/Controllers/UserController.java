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
    public User authorization(@RequestBody User user){
        User response = userRepository.findUserByLogin(user.getLogin());
        if (response==null){
            return new User();
        }else if(response.getPassword().equals(user.getPassword())){
            response.setAccess(true);
            return response;
        }else {
            response.setPassword("");
            return response;
        }
    }
    @PostMapping
    public String registration(@RequestBody User user){
        if(userRepository.findUserByLogin(user.getLogin()) != null){
            return "User already exists";
        }
            userRepository.addUser(user.getLogin(),user.getPassword(),user.getName(),user.getSurname());
            return "You have registered";
    }
}
