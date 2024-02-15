package com.disney.studios.repositories;

import com.disney.studios.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, String> {

    @Query("SELECT p FROM Picture p WHERE p.breed = :breed ORDER BY p.votes DESC")
    public List<Picture> searchByBreed(@Param("breed") String breed);

    @Query("SELECT p FROM Picture p WHERE p.url = :url")
    public Picture searchByUrl(@Param("url") String url);

}
