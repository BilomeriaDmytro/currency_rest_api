package com.example.demo.controller;

import com.example.demo.dto.AuthenticationResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.security.jwt.TokenProvider;
import com.example.demo.service.UserService;
import com.example.demo.service.exceptionHandler.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AuthenticationController {

    AuthenticationManager authenticationManager;
    TokenProvider tokenProvider;
    UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("api/login")
    public AuthenticationResponseDTO login(@RequestBody UserDTO userDTO) {

        try{
            String username = userDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,userDTO.getPassword()));
            User user = userService.findUserByUsername(username);
            if(user == null){
                throw new UserNotFoundException(username);
            }
            String token = tokenProvider.createToken(username);
            return new AuthenticationResponseDTO(username,token);

        }catch (AuthenticationException | UserNotFoundException e){
            log.warn("Wrong credentials");
            throw new BadCredentialsException("Invalid username or password");
        }
    }


    @GetMapping("api/u/get/allUsers")
    public List<User> gerAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("api/a/save/addUser")
    public User addNewUser(@RequestBody UserDTO userDTO) {
        return userService.registerNewUser(userDTO);
    }
}
