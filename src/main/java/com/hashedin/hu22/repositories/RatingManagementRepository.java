package com.hashedin.hu22.repositories;

import com.hashedin.hu22.entities.Rating;
import com.hashedin.hu22.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingManagementRepository extends JpaRepository<Rating , Integer> {

    @Query("SELECT rd FROM rating rd WHERE rd.movieId=?1 and rd.userId=?2")
    Rating checkRating(Integer movieId , Integer userID);

}
