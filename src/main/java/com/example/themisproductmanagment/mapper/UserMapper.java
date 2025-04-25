package com.example.themisproductmanagment.mapper;


import com.example.themisproductmanagment.dto.UserDto;
import com.example.themisproductmanagment.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface UserMapper {
     User toUser(UserDto userDto);
     UserDto toUserDto(User user);
     List<UserDto> toUserDtoList(List<User> userList);
     List<User> toUserList(List<UserDto> userDtoList);
}
