package com.example.demo.service.implementation;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import com.example.demo.repositrory.RoleRepository;
import com.example.demo.repositrory.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.exceptionHandler.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.warn("User with username - {} not found", username);
            throw new UserNotFoundException(username);
        }
        log.info("User with username - {} successfully found", username);
        return user;
    }

    @Override
    public User registerNewUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        if(userRepository.findByUsername(username) != null){
            log.warn("User with username - {} already exists", username);
            return null;
        }

        Role role = roleRepository.findByRole("ROLE_USER");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setRoles(new ArrayList<Role>());
        newUser.getRoles().add(role);
        newUser.setStatus(Status.ACTIVE);
        newUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userRepository.save(newUser);
        log.info("User with username - {} successfully registered", username);
        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        log.info("Users found: {}",allUsers.size());
        return allUsers;
    }
}
