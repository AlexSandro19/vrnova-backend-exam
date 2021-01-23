package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        System.out.println("findbyid");
        return optionalUser.orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(long userID) {userRepository.deleteById(userID);}
}
