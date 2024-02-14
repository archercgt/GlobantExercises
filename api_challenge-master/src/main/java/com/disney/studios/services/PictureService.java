package com.disney.studios.services;

import com.disney.studios.entities.Answer;
import com.disney.studios.entities.Picture;
import com.disney.studios.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class PictureService {
    @Autowired PictureRepository pictureRepository;

    @Transactional
    public void createPicure(String url, String breed){
        Picture picture = new Picture();
        picture.setUrl(url);
        picture.setBreed(breed);
        picture.setVotes(0);
        pictureRepository.save(picture);
    }

    public List<Picture> listByBreed(String breed){
        List<Picture> pictures = new ArrayList();
        pictures = pictureRepository.searchByBreed(breed);
        return pictures;
    }

    public TreeMap<String, List<Picture>> listAll() {
        List<Picture> allPictures = pictureRepository.findAll();

        // Group pictures by breed
        TreeMap<String, List<Picture>> picturesByBreed = allPictures.stream()
                .collect(Collectors.groupingBy(Picture::getBreed, TreeMap::new, Collectors.toList()));

        // Sort pictures within each breed by descending votes
        picturesByBreed.forEach((breed, pictures) -> {
            pictures.sort(Comparator.comparingInt(Picture::getVotes).reversed());
        });

        return picturesByBreed;
    }

    @Transactional
    public Answer vote(String url, String vote){
        vote = vote.toLowerCase();
        if(!vote.equals("up") && !vote.equals("down")){
            return new Answer("Wrong vote value, please enter \"up\" or \"down\"");
        }
        Optional<Picture> found = Optional.ofNullable(pictureRepository.searchByUrl(url));

        if(found.isPresent()){
            Picture picture = found.get();
            if(vote.equals("up"))
                picture.incrementVotes();
            else
                picture.decrementVotes();
            pictureRepository.save(picture);
            return new Answer("The vote was successfully saved!!!");
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
