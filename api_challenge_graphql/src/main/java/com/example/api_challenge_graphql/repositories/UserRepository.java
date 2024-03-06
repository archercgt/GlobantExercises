package com.example.api_challenge_graphql.repositories;

import com.example.api_challenge_graphql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.fullName = :fullName")
    public User searchByFullName(@Param("fullName") String url);
}