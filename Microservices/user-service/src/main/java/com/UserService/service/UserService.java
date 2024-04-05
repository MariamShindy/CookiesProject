package com.UserService.service;

import com.UserService.dto.UserRequest;
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
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setUserType(1); // should handle this due to user's role
        return user;
    }

}
