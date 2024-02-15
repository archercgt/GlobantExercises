package com.disney.studios.services;

import com.disney.studios.entities.User;
import com.disney.studios.entities.VotedUser;
import com.disney.studios.repositories.VotedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VotedService {

    @Autowired
    private VotedUserRepository votedUserRepository;
    public boolean hasUserVoted(Long userId) {
        Optional<VotedUser> found = Optional.ofNullable(votedUserRepository.findByUserId(userId));
        return found.isPresent();
    }
    public void setUserVoted(Long userId) {
        VotedUser votedUser = new VotedUser();
        votedUser.setUserId(userId);
        votedUserRepository.save(votedUser);
    }
}
