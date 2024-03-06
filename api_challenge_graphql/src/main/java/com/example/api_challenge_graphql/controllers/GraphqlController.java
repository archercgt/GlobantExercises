package com.example.api_challenge_graphql.controllers;

import com.example.api_challenge_graphql.entities.Answer;
import com.example.api_challenge_graphql.entities.Picture;
import com.example.api_challenge_graphql.entities.User;
import com.example.api_challenge_graphql.services.PictureService;
import com.example.api_challenge_graphql.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.TreeMap;

@Controller
public class GraphqlController {
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;
    @QueryMapping
    public List<User> listUsers() {
        return userService.listUsers();
    }
    @QueryMapping
    public List<Picture> listPictures() {
        return pictureService.listAll();
    }
    @QueryMapping
    public List<Picture> listByBreed(@Argument String breed) {
        return pictureService.listByBreed(breed);
    }
    @QueryMapping
    public Answer getInfo(@Argument String url) {
        return pictureService.getInfo(url);
    }
    @MutationMapping
    public Answer createUser(@Argument Long id, @Argument String fullName) {
        return userService.createUser(id, fullName);
    }
    @MutationMapping
    public Answer addInfo(@Argument String url, @Argument String info) {
        return pictureService.addInfo(url, info);
    }
    @MutationMapping
    public Answer vote(@Argument String url, @Argument String vote, @Argument Long userId) {
        return pictureService.vote(url, vote, userId);
    }
}