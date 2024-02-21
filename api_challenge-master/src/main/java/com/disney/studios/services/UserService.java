package com.disney.studios.services;

import com.disney.studios.entities.Answer;
import com.disney.studios.entities.User;
import com.disney.studios.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired UserRepository userRepository;
    @Transactional
    public Answer createUser(Long id, String fullName){
        fullName.toLowerCase();
        Optional<User> found = Optional.ofNullable(userRepository.searchByFullName(fullName));
        if(found.isPresent())
            return new Answer("User already exists");
        else{
            User user = new User(id, fullName);
            userRepository.save(user);
            return  new Answer("User successfully created");
        }
    }
    public List<User> listUsers(){
        return  userRepository.findAll();
    }
    public boolean exists(Long userId) {
        Optional<User> found = userRepository.findById(userId);
        return found.isPresent();
    }
}
