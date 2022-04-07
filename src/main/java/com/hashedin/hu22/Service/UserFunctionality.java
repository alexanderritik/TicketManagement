package com.hashedin.hu22.Service;

import com.hashedin.hu22.entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserFunctionality {

    public String LoginUser(User user){
        if(user==null){
            return "User is Invalid";
        }else {
            System.out.println(user.getRole());
            if(user.getRole().equalsIgnoreCase("Admin")) {
                return "The User is valid Admin";
            }
            else {
                return "The User is valid Customer";
            }
        }
    }

    public boolean checkadmin(User user){
        if(user==null){
            return false;
        }else {
            if(user.getRole().equalsIgnoreCase("Admin")) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public Boolean verifyUser(User user){
        if(user==null){
            return false;
        }
        return true;
    }

    public Map sendResposne(String message, Integer statusCode , Object obj)
    {
        Map map = new HashMap();
        map.put("data",obj);
        Map mapA = new HashMap();
        mapA.put("statusCode",statusCode);
        mapA.put("message",message);
        mapA.put("status",message);

        map.put("status" , mapA);
        return  map;
    }

}
