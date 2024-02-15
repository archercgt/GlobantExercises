package com.disney.studios.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Picture {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String url, additional_info, breed;
    private int votes;

    //Constructor Method
    public Picture() {
    }

    //Method for incrementing the votes one unit.
    public void incrementVotes() {
        votes++;
    }
    //Method for decrementing the votes one unit.
    public void decrementVotes() {
        votes--;
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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    //To string Method
    @Override
    public String toString() {
        return "Picture{" +
                "url='" + url + '\'' +
                ", additional_info='" + additional_info + '\'' +
                ", breed='" + breed + '\'' +
                ", votes=" + votes +
                '}';
    }
}
