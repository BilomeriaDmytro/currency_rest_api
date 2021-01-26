package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.exceptionHandler.exception.UserNotFoundException;
import java.util.List;

public interface UserService {

    User findUserByUsername(String username) throws UserNotFoundException;

    User registerNewUser(UserDTO userDTO);

    List<User> getAllUsers();
}
