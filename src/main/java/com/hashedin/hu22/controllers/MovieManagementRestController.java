package com.hashedin.hu22.controllers;

import com.hashedin.hu22.Service.UserFunctionality;
import com.hashedin.hu22.entities.Movie;
import com.hashedin.hu22.entities.Threatre;
import com.hashedin.hu22.entities.User;
import com.hashedin.hu22.repositories.MovieManagementRepository;
import com.hashedin.hu22.repositories.ThreatreManagementRepository;
import com.hashedin.hu22.repositories.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MovieManagementRestController {

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private MovieManagementRepository movieManagementRepository;

    @Autowired
    private ThreatreManagementRepository threatreManagementRepository;

    private UserFunctionality userFunctionality = new UserFunctionality();


    @RequestMapping(value = "/{id}/movie/add" , method = RequestMethod.POST)
    public @ResponseBody Map addMovie(@PathVariable("id") int id,@RequestBody Movie movie){
        User u = userManagementRepository.findById(id).get();
        if(userFunctionality.checkadmin(u)){
            Movie m = movieManagementRepository.save(movie);
            return userFunctionality.sendResposne("Success",200,"Movie is added sucessfullly");
        }else{
            return userFunctionality.sendResposne("failed",204,"Invalid admin");
        }

    }

    @RequestMapping(value = "/movie/all" , method = RequestMethod.GET)
    public @ResponseBody Map getAllMovie(){
        return userFunctionality.sendResposne("Success",200,movieManagementRepository.findAll());
    }

    @RequestMapping(value = "/movie/{id}" , method = RequestMethod.POST)
    public @ResponseBody Map getMovieIdDetail(@PathVariable("id") int id){

        try{
            return userFunctionality.sendResposne("Success",200,movieManagementRepository.findById(id).get());
        }catch(Exception e){
            return userFunctionality.sendResposne("Success",200,"Movie id is Invalid");
        }


    }

    @RequestMapping(value = "/movie/{genre}" , method = RequestMethod.GET)
    public @ResponseBody Map similarGenre(@PathVariable("genre") String genre){
        return userFunctionality.sendResposne("Success",200,movieManagementRepository.similarGenre(genre));
    }

    @RequestMapping(value = "/movie/rating" , method = RequestMethod.GET)
    public @ResponseBody Map sortedRating(){
        return userFunctionality.sendResposne("Success",200,movieManagementRepository.sortRating());
    }

    @RequestMapping(value = "/movie/{id}/threatre" , method = RequestMethod.POST)
    public @ResponseBody Map addMovieThreatre(@PathVariable("id") int id , @RequestBody Threatre threatre){
        try{
            Movie movie = movieManagementRepository.findById(id).get();
            movie.getThreatres().add(threatre);
            System.out.println((movie.toString()));
            return userFunctionality.sendResposne("Success",200,movieManagementRepository.save(movie));

        }catch (Exception e){
            return userFunctionality.sendResposne("failed",204,"Movie id is not found");
        }
     }


    @RequestMapping(value = "/movie/update/{id}" , method = RequestMethod.PUT)
    public @ResponseBody Map updateMovieDetail(@PathVariable("id") int id , @RequestBody Movie movie){
        try{
            movieManagementRepository.save(movie);
            return userFunctionality.sendResposne("Success",200,"Movie updated sucessfully");
        }catch (Exception e){
            return userFunctionality.sendResposne("failed",204,"Movie id is not found");
        }
    }


    @RequestMapping(value = "/movie/update/threatre" , method = RequestMethod.PUT)
    public @ResponseBody Map updateMovieThreatreDetail(@RequestBody Threatre threatre){
        try{
            threatreManagementRepository.save(threatre);
            return userFunctionality.sendResposne("Success",200,"Threatre added successfully");
        }catch (Exception e){
            return userFunctionality.sendResposne("failed",204,"Movie id is not found");
        }
    }





}
