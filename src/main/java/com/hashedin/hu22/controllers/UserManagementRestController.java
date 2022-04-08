package com.hashedin.hu22.controllers;


import com.hashedin.hu22.Service.UserFunctionality;
import com.hashedin.hu22.entities.*;
import com.hashedin.hu22.repositories.MovieManagementRepository;
import com.hashedin.hu22.repositories.RatingManagementRepository;
import com.hashedin.hu22.repositories.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserManagementRestController {

    @Autowired
    private UserManagementRepository userManagementRepository;
    @Autowired
    private MovieManagementRepository movieManagementRepository;
    @Autowired
    private RatingManagementRepository ratingManagementRepository;


    private UserFunctionality userFunctionality = new UserFunctionality();


    @RequestMapping(value = "/user/create" , method = RequestMethod.POST)
    public @ResponseBody Map createUser(@RequestBody User user){

        User u = userManagementRepository.save(user);
        return userFunctionality.sendResposne("Success",200,"The user is success fully added");
    }

    @RequestMapping(value = "/user/all" , method = RequestMethod.GET)
    public @ResponseBody Map getAllUser(){
        return userFunctionality.sendResposne("Success",200,userManagementRepository.findAll());
    }

    @RequestMapping(value = "/user/login" , method = RequestMethod.POST)
    public @ResponseBody Map userLogin(@RequestBody Login login){
        User user = userManagementRepository.loginForUser(login.getMobile() , login.getDob());
        return userFunctionality.sendResposne("Success",200,userFunctionality.LoginUser(user));
    }

    @RequestMapping(value = "/user/{id}" , method = RequestMethod.GET)
    public @ResponseBody Map getUserDetail(@PathVariable("id") int id){
        try {
            User u  =userManagementRepository.findById(id).get();
             return userFunctionality.sendResposne("Success", 200, u);
        }
        catch(Exception e){
            return userFunctionality.sendResposne("Success", 200, "The user ID no found");
        }
    }

    @RequestMapping(value = "/user/update/{id}" , method = RequestMethod.PUT)
    public @ResponseBody Map updateUserDetail(@PathVariable("id") int id, @RequestBody User user){
        try{
            User u = userManagementRepository.findById(id).get();
            if(userFunctionality.verifyUser(u)){
                u.setGenre(user.getGenre());
                userManagementRepository.save(u);
                return  userFunctionality.sendResposne("Success",200,"User field is modified");
            }else{
                return userFunctionality.sendResposne("Failed",204,"Invalid user update");
            }
        }catch (Exception e){
            return userFunctionality.sendResposne("Failed",204,"Invalid user update");
        }

    }


    @RequestMapping(value = "/user/recomendation/{id}" , method = RequestMethod.GET)
    public @ResponseBody Map movieRecomendation(@PathVariable("id") int id){

        try {
            User u = userManagementRepository.findById(id).get();
                ArrayList<String> genre = u.getGenre();
              ArrayList<Movie> movies = new ArrayList<>();
            for (String g:genre) {
                List<Movie> dummyMovie = movieManagementRepository.recomendation(g);
                for(Movie m:dummyMovie){
                    movies.add(m);
                }
            }
            return userFunctionality.sendResposne("Success",200,movies);
        }catch (Exception e){
            return userFunctionality.sendResposne("failed",204,"User id is not found");
        }

    }



    @RequestMapping(value = "/user/{id}/history" , method = RequestMethod.GET)
    public @ResponseBody Map bookingHistory(@PathVariable("id") int id){
        System.out.println("Ticket add ");
        try{
            User u = userManagementRepository.findById(id).get();
            return userFunctionality.sendResposne("Success",200,u.getTickets());
        }catch (Exception e){
            return userFunctionality.sendResposne("failed",204,"Invalid user ");
        }
    }


    @RequestMapping(value = "/{id}/user/{id1}/addRating" , method = RequestMethod.POST)
    public @ResponseBody Map addRating(@PathVariable("id") int id, @PathVariable("id1") int id1 ,@RequestBody Rating rating){
        try{
            Rating checkRating = ratingManagementRepository.checkRating(id,id1);
            if(checkRating!=null){
                return userFunctionality.sendResposne("failed",203,"Rating is already added by user");
            }
            else{
                Movie m = movieManagementRepository.findById(id1).get();
                int tempRating = m.getRating();
                m.setRating(tempRating+rating.getRating());
                m.getRatingList().add(rating);
                movieManagementRepository.save(m);
                return userFunctionality.sendResposne("Success",200,"Rating is successfully added by the user");
            }

        }catch (Exception e){
            return userFunctionality.sendResposne("failed",204,"Invalid user or Movie");
        }
    }





}
