package com.example.themisproductmanagment.controller;

import com.example.themisproductmanagment.dto.UserDto;
import com.example.themisproductmanagment.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/app/v1")
public class UserController {


   private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/get-all-users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/get-user")
    public UserDto getUserById(@RequestParam Long id) {
        return userService.getById(id);
    }

    @PostMapping("/create-user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/update-user")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/delete-user")
    public String deleteUserById(@RequestParam Long id) {
        return userService.deleteUser(id);
    }
}
