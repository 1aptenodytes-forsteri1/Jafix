package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Models.UserId;
import com.example.demo.Repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    @GetMapping
    public User authorization(@RequestParam(required = false) String login, @RequestParam(required = false) String password, @RequestParam(required = false) Integer userId){
        User response = userRepository.findUserByLogin(login);
        if(userId != null){
            return userRepository.findUserById(userId);
        }
        if (response==null){
            return new User(0);
        }else if(response.getPassword().equals(password)){
            return response;
        }else {
            return new User(0);
        }
    }
    public User authorization(@RequestParam Integer id){
        return userRepository.findUserById(id);
    }
    @PostMapping
    public String registration(@RequestBody User user){
        if(userRepository.findUserByLogin(user.getLogin()) != null){
            return "User already exists";
        }
            userRepository.addUser(user.getLogin(),user.getPassword(),user.getName(),user.getSurname());
            return "You have registered";
    }

    @PatchMapping
    public String patchUser(@RequestBody User user){
        userRepository.updateUser(user);
        return "successfully updated";
    }
}
