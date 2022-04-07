package com.hashedin.hu22.controllers;


import com.hashedin.hu22.Service.UserFunctionality;
import com.hashedin.hu22.entities.Login;
import com.hashedin.hu22.entities.Movie;
import com.hashedin.hu22.entities.User;
import com.hashedin.hu22.repositories.MovieManagementRepository;
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


    private UserFunctionality userFunctionality = new UserFunctionality();


    @RequestMapping(value = "/user/create" , method = RequestMethod.POST)
    public @ResponseBody Map createUser(@RequestBody User user){

        User u = userManagementRepository.save(user);
        return userFunctionality.sendResposne("Success",200,u);
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
        return userFunctionality.sendResposne("Success",200,userManagementRepository.findById(id).get());
    }

    @RequestMapping(value = "/user/update/{id}" , method = RequestMethod.PATCH)
    public @ResponseBody Map updateUserDetail(@PathVariable("id") int id, @RequestBody User user){
        User u = userManagementRepository.findById(id).get();
        if(userFunctionality.verifyUser(u)){
            u.setGenre(user.getGenre());
            return  userFunctionality.sendResposne("Success",200,userManagementRepository.save(u));
        }else{
            return userFunctionality.sendResposne("Failed",204,null);
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



}
