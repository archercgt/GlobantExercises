package com.disney.studios.repositories;

import com.disney.studios.entities.VotedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VotedUserRepository extends JpaRepository<VotedUser, Long> {

    @Query("SELECT u FROM VotedUser u WHERE u.userId = :userId")
    public VotedUser findByUserId(@Param("userId") Long userId);
}
