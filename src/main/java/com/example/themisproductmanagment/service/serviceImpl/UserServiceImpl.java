package com.example.themisproductmanagment.service.serviceImpl;

import com.example.themisproductmanagment.dto.UserDto;
import com.example.themisproductmanagment.entity.User;
import com.example.themisproductmanagment.mapper.EntityMapper;
import com.example.themisproductmanagment.repository.UserRepository;
import com.example.themisproductmanagment.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;
    private final static String USER_CACHE = "User";

    public UserServiceImpl(UserRepository userRepository, EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    @Cacheable(value = USER_CACHE , key = "#result.getId()")
    public List<UserDto> getUsers() {
        return entityMapper.toUserDtoList(userRepository.findAll());
    }

    @Override
    @Cacheable(value = USER_CACHE , key = "#id")
    public UserDto getById(Long id) {
        User user  = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        return entityMapper.toUserDto(user);
    }

    @Override
    @CachePut(value = USER_CACHE , key = "#result.getId()")
    public UserDto createUser(UserDto userDto) {
        User user = entityMapper.toUser(userDto);
        userRepository.save(user);
        return entityMapper.toUserDto(user);
    }

    @Override
    @CachePut(value = USER_CACHE , key = "#result.getId()")
    public UserDto updateUser(UserDto userDto) {
       Long id   = userDto.getId();
       User user  = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
       entityMapper.updateUser(userDto,user);
       userRepository.save(user);
       return entityMapper.toUserDto(user);
    }

    @Override
    @CacheEvict(value = USER_CACHE , key = "#id")
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        userRepository.delete(user);
        return "User deleted";
    }
}
