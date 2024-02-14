package com.disney.studios.controllers;

import com.disney.studios.entities.Answer;
import com.disney.studios.entities.Picture;
import com.disney.studios.services.PictureService;
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

    @GetMapping("/")
    public Answer index(){
        return new Answer("Welcome to Dogs API challenge");
    }

    @GetMapping("/api/list")
    public TreeMap<String, List<Picture>> listAll() {
        return pictureService.listAll();
    }

    @GetMapping("/api/list/{breed}")
    @ResponseStatus(HttpStatus.OK)
    public List<Picture> listByBreed(@PathVariable String breed) {
        return pictureService.listByBreed(breed);
    }

    @PatchMapping("/api/vote")
    public Answer vote(
            @RequestParam(required = true) String url,
            @RequestParam(required = true) String vote){
        return pictureService.vote(url,vote);
    }

    @GetMapping("/api/vote")
    public void vote() {
        // Return an error for GET requests to this endpoint
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "GET method not allowed for this endpoint");
    }

    @GetMapping("/api/info")
    public Answer getInfo(@RequestParam(required = true) String url) {
        return pictureService.getInfo(url);
    }

    @PatchMapping("/api/info")
    public Answer addInfo(
            @RequestParam(required = true) String url,
            @RequestParam(required = true) String info){
        return pictureService.addInfo(url,info);
    }
}
