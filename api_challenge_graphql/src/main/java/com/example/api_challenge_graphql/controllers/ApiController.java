package com.example.api_challenge_graphql.controllers;

import com.example.api_challenge_graphql.entities.Answer;
import com.example.api_challenge_graphql.entities.Picture;
import com.example.api_challenge_graphql.entities.User;
import com.example.api_challenge_graphql.services.PictureService;
import com.example.api_challenge_graphql.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.TreeMap;

@RestController

public class ApiController {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;


    //For welcoming when pointing to http://localhost:8080
    @GetMapping("/")
    public Answer index(){
        return new Answer("Welcome to Dogs API challenge");
    }

    //For listing all dog's pictures grouped by breed
    @GetMapping("/api/list")
    public List<Picture> listAll() {
        return pictureService.listAll();
    }

    //For listing dog's pictures for a specifig breed
    @GetMapping("/api/list/{breed}")
    @ResponseStatus(HttpStatus.OK)
    public List<Picture> listByBreed(@PathVariable String breed) {
        return pictureService.listByBreed(breed);
    }

    //For voting up or down over a specific picture
    @PatchMapping("/api/vote")
    public Answer vote(
            @RequestHeader("url") String url,
            @RequestHeader("vote") String vote,
            @RequestHeader("userId") Long userId){
        // Check if the user has already voted
        return pictureService.vote(url, vote, userId);
    }

    //For denying the access to the enpoint /api/vote when using the Get Method
    @GetMapping("/api/vote")
    public void vote() {
        // Return an error for GET requests to this endpoint
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "GET method not allowed for this endpoint");
    }

    //For getting the additional information of a picture
    @GetMapping("/api/info")
    public Answer getInfo(@RequestHeader("url") String url){
        return pictureService.getInfo(url);
    }

    //For adding the additional information to a picture
    @PatchMapping("/api/info")
    public Answer addInfo(
            @RequestHeader("url") String url,
            @RequestHeader("info") String info){
        return pictureService.addInfo(url,info);
    }

    //For creating a user
    @PutMapping("/api/users")
    public Answer createUser(
            @RequestHeader("id") Long id,
            @RequestHeader("name") String name){
        return userService.createUser(id, name);
    }

    //For listing the users stored in the database
    @GetMapping("api/users")
    public List<User> listUsers(){
        return userService.listUsers();
    }
}