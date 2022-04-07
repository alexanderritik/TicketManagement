package com.hashedin.hu22.controllers;

import com.hashedin.hu22.Service.UserFunctionality;
import com.hashedin.hu22.entities.Movie;
import com.hashedin.hu22.repositories.MovieManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MovieManagementRestController {

    @Autowired
    private MovieManagementRepository movieManagementRepository;

    private UserFunctionality userFunctionality = new UserFunctionality();


    @RequestMapping(value = "/movie/add" , method = RequestMethod.POST)
    public @ResponseBody Map addMovie(@RequestBody Movie movie){
        Movie m = movieManagementRepository.save(movie);
        return userFunctionality.sendResposne("Success",200,m);
    }

    @RequestMapping(value = "/movie/all" , method = RequestMethod.GET)
    public @ResponseBody Map getAllMovie(){
        return userFunctionality.sendResposne("Success",200,movieManagementRepository.findAll());
    }

    @RequestMapping(value = "/movie/{id}" , method = RequestMethod.POST)
    public @ResponseBody Map getMovieIdDetail(@PathVariable("id") int id){
        return userFunctionality.sendResposne("Success",200,movieManagementRepository.findById(id).get());
    }

    @RequestMapping(value = "/movie/{genre}" , method = RequestMethod.GET)
    public @ResponseBody Map similarGenre(@PathVariable("genre") String genre){
        return userFunctionality.sendResposne("Success",200,movieManagementRepository.similarGenre(genre));
    }

    @RequestMapping(value = "/movie/rating" , method = RequestMethod.GET)
    public @ResponseBody Map sortedRating(){
        return userFunctionality.sendResposne("Success",200,movieManagementRepository.sortRating());
    }





}
