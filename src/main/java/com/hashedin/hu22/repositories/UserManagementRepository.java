package com.hashedin.hu22.repositories;

import  javax.transaction.Transaction;

import com.hashedin.hu22.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserManagementRepository extends JpaRepository<User , Integer> {


    @Query("SELECT ud FROM user ud where ud.mobileNumber=?1 and ud.dob=?2")
    User loginForUser(String mobile , String dob);

}
