package com.example.api_challenge_graphql.services;

import com.example.api_challenge_graphql.entities.Answer;
import com.example.api_challenge_graphql.entities.Picture;
import com.example.api_challenge_graphql.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Map;


@Service
public class PictureService {
    @Autowired PictureRepository pictureRepository;
    @Autowired UserService userService;
    @Transactional
    public void createPicure(String url, String breed){
        Optional<Picture> found = Optional.ofNullable(pictureRepository.searchByUrl(url));
        if(found.isPresent()){
        }else{
            Picture picture = new Picture();
            picture.setUrl(url);
            picture.setBreed(breed);
            pictureRepository.save(picture);
        }
    }
    public List<Picture> listByBreed(String breed){
        List<Picture> pictures = pictureRepository.searchByBreed(breed);
        return pictures;
    }
    public List<Picture> listAll() {
        List<Picture> allPictures = pictureRepository.findAll();
        // Sort pictures by breed and then by votesUp in descending order
        List<Picture> sortedPictures = allPictures.stream()
                .sorted(Comparator.comparing(Picture::getBreed)
                        .thenComparing(Picture::getVotesUp, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return sortedPictures;
    }
    @Transactional
    public Answer vote(String url, String voteDecision, Long userId){
        //Validate proper vote input
        voteDecision.toLowerCase();
        if(!voteDecision.equals("up") && !voteDecision.equals("down")){
            return new Answer("Wrong vote value, please enter \"up\" or \"down\"");
        }
        //Validate existing picture by url
        Optional<Picture> found = Optional.ofNullable(pictureRepository.searchByUrl(url));
        if(found.isPresent()){
            //Validate user existence
            if(userService.exists(userId)){
                Picture picture = found.get();
                Map<Long, String> map = picture.getUserVoteMap();
                //Validate if user has already voted before
                if(map.containsKey(userId)){
                    if(map.get(userId).equals(voteDecision))
                        return new Answer("User already voted " + voteDecision + " for the picture");
                    else{
                        if (voteDecision.equals("up"))
                            picture.decrementVotesDown();
                        else
                            picture.decrementVotesUp();
                    }
                }
                if(voteDecision.equals("up"))
                    picture.incrementVotesUp();
                else
                    picture.incrementVotesDown();
                picture.setUser(userId,voteDecision);
                pictureRepository.save(picture);
                return new Answer("The vote was successfully saved!!!");
            }else
                return new Answer("Does not exists a user for the entered userId");
        }else
            return new Answer("There is not a picture with the entered URL");
    }
    public Answer getInfo(String url){
        Optional<Picture> found = Optional.ofNullable(pictureRepository.searchByUrl(url));
        if(found.isPresent()){
            Picture picture = found.get();
            Optional<String> info = Optional.ofNullable(picture.getAdditional_info());
            if(info.isPresent())
                return new Answer(picture.getAdditional_info());
            else
                return new Answer("The picture does not have additional information");
        }else
            return new Answer("There is not a picture with the entered URL");
    }
    public Answer addInfo(String url, String info){
        Optional<Picture> found = Optional.ofNullable(pictureRepository.searchByUrl(url));
        if(found.isPresent()) {
            Picture picture = found.get();
            picture.setAdditional_info(info);
            pictureRepository.save(picture);
            return new Answer("Additional information successfully updated");
        }else
            return new Answer("There is not a picture with the entered URL");
    }
}