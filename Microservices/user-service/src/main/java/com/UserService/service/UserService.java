package com.UserService.service;

import com.UserService.dto.UserRequest;
import com.UserService.model.Admin;
import com.UserService.model.Customer;
import com.UserService.model.User;
import com.UserService.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveUser(UserRequest userRequest) {
        User user = mapUserRequestToUser(userRequest);
        userRepository.save(user);
    }

    //UserRequest ==> User
    public User mapUserRequestToUser(UserRequest userRequest){
        User user;
        if (userRequest.getUserType() == 1) {
            user = new Admin();
        } else if (userRequest.getUserType() == 2) {
            user = new Customer();
        } else {
            // handle invalid userType or provide default behavior
            throw new IllegalArgumentException("Invalid userType");
        }
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        return user;
    }


}
