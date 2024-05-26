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
    public UserId authorization(@RequestParam String login, @RequestParam String password){
        User response = userRepository.findUserByLogin(login);
        if (response==null){
            return new UserId(0);
        }else if(response.getPassword().equals(password)){
            return new UserId(response.getId());
        }else {
            return new UserId(0);
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

    @PatchMapping
    public String patchUser(@RequestBody User user){
        userRepository.updateUser(user);
        return "successfully updated";
    }
}
