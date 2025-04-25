package com.example.themisproductmanagment.service;

import com.example.themisproductmanagment.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getById(Long id);
    UserDto createUser(UserDto userDto);

}
