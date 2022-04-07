package com.hashedin.hu22.repositories;

import com.hashedin.hu22.entities.Movie;
import com.hashedin.hu22.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieManagementRepository extends JpaRepository<Movie , Integer> {

    @Query("SELECT md FROM movie md WHERE md.genre LIKE %?1%")
    List<Movie> similarGenre(String genre);

    @Query("SELECT md FROM movie md ORDER BY md.rating ASC")
    List<Movie> sortRating();

    @Query("SELECT md FROM movie md WHERE md.genre LIKE %?1% ORDER BY rating")
    List<Movie> recomendation(String genre);

}
