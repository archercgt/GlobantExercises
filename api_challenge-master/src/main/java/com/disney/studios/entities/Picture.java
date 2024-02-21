package com.disney.studios.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.CollectionTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
public class Picture {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String url, additional_info, breed;
    private int votesUp, votesDown;
    @ElementCollection
    @CollectionTable(name="picture_user_info")
    @MapKeyColumn(name="user_id")
    @Column(name="user_vote")
    private Map<Long, String> userVoteMap;


    //Constructor Method
    public Picture() {
        votesUp = 0;
        votesDown = 0;
        userVoteMap = new HashMap<>();
    }

    //Method for incrementing the votesUp one unit.
    public void incrementVotesUp() {
        votesUp ++;
    }
    //Method for decrementing the votesUp one unit.
    public void decrementVotesUp() {
        votesUp --;
    }
    //Method for incrementing the votesDown one unit.
    public void incrementVotesDown() {
        votesDown ++;
    }
    //Method for decrementing the votesDown one unit.
    public void decrementVotesDown() {
        votesDown --;
    }

    //Method for set a user in the map
    public void setUser(Long userId, String vote){
        userVoteMap.put(userId, vote);
    }

    //Getter and Setter Methods
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getVotesUp() {
        return votesUp;
    }

    public void setVotesUp(int votesUp) {
        this.votesUp = votesUp;
    }

    public int getVotesDown() {
        return votesDown;
    }

    public void setVotesDown(int votesDown) {
        this.votesDown = votesDown;
    }

    public Map<Long, String> getUserVoteMap() {
        return userVoteMap;
    }

    public void setUserVoteMap(Map<Long, String> userVoteMap) {
        this.userVoteMap = userVoteMap;
    }

    //To string Method
    @Override
    public String toString() {
        return "Picture{" +
                "url='" + url + '\'' +
                ", additional_info='" + additional_info + '\'' +
                ", breed='" + breed + '\'' +
                ", votesUp=" + votesUp +
                ", votesDown=" + votesDown +
                '}';
    }
}
