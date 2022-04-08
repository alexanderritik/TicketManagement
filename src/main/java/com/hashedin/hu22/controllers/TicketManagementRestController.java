package com.hashedin.hu22.controllers;

import com.hashedin.hu22.Service.UserFunctionality;
import com.hashedin.hu22.entities.Movie;
import com.hashedin.hu22.entities.Ticket;
import com.hashedin.hu22.entities.User;
import com.hashedin.hu22.repositories.MovieManagementRepository;
import com.hashedin.hu22.repositories.TicketManagementRepository;
import com.hashedin.hu22.repositories.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TicketManagementRestController {

    @Autowired
    private TicketManagementRepository ticketManagementRepository;

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private MovieManagementRepository movieManagementRepository;


    private UserFunctionality userFunctionality = new UserFunctionality();


    @RequestMapping(value = "/{id}/movie/{id1}/ticket" , method = RequestMethod.POST)
    public @ResponseBody Map addticket(@PathVariable("id") int id , @PathVariable("id1") int id1, @RequestBody Ticket ticket){
        System.out.println("Ticket add ");
        try{
            //verify user && verify movie
            User u = userManagementRepository.findById(id).get();
            Movie m = movieManagementRepository.findById(id1).get();
            u.getTickets().add(ticket);
            userManagementRepository.save(u);
            return userFunctionality.sendResposne("Success",200,"Ticket is added succesfully");
        }catch (Exception e){
            return userFunctionality.sendResposne("failed",204,"Invalid user or movie");
        }
     }

    @RequestMapping(value = "/{id}/movie/{id1}/updateticket" , method = RequestMethod.PUT)
    public @ResponseBody Map updateticket(@PathVariable("id") int id , @PathVariable("id1") int id1, @RequestBody Ticket ticket){
        try{
            //verify user && verify movie
            User u = userManagementRepository.findById(id).get();
            Movie m = movieManagementRepository.findById(id1).get();
            ticketManagementRepository.save(ticket);
            return userFunctionality.sendResposne("Success",200,"Ticket is updated successfully");
        }catch (Exception e){
            return userFunctionality.sendResposne("failed",204,"Invalid user or movie");
        }
    }


}