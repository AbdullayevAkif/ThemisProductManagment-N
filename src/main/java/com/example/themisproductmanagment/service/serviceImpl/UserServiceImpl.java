package com.example.themisproductmanagment.service.serviceImpl;

import com.example.themisproductmanagment.dto.UserDto;
import com.example.themisproductmanagment.entity.User;
import com.example.themisproductmanagment.mapper.UserMapper;
import com.example.themisproductmanagment.repository.UserRepository;
import com.example.themisproductmanagment.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDto> getUsers() {
       List<User> users = userRepository.findAll();
       return userMapper.toUserDtoList(users);
    }

    @Override
    public UserDto getById(Long id) {
        User user  = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = new User(userDto.getId(),userDto.getName(),new ArrayList<>());
        userRepository.save(user);
       User userSave =  userRepository.save(user);
       return userMapper.toUserDto(userSave);

    }



}
