package com.acko.demo.controller;

import com.acko.demo.entity.User;
import com.acko.demo.exception.ResourceNotFoundException;
import com.acko.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/list-users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/register-user")
    public User addNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping( "/update-user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> user1 = userRepository.findById(id);

        if(user1.isEmpty()) {
            throw new ResourceNotFoundException();
        }else {
            User temp = user1.get();
            temp.setUsername(user.getUsername());
            temp.setPassword(user.getPassword());
            temp.setTasks(user.getTasks());
            return userRepository.save(user);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
