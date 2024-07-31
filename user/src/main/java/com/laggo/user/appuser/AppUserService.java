package com.laggo.user.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    @Autowired
    AppUserRepo appUserRepo;


    // Check if user exists
    public  boolean checkIfUserExists(String email){
        if(appUserRepo.findByEmail(email).isPresent()){
            return true;
        }
        return false;
    }

    // Get User PhoneNumber
    public String getPhoneNumber(String email) throws IllegalAccessException {
        if(checkIfUserExists(email)){
            return getUser(email).getEmail();
        }
        else {
            throw new IllegalAccessException("User not found");
        }
    }


    //Get User
    public AppUser getUser(String email) throws IllegalAccessException {
       if(checkIfUserExists(email)){
           return appUserRepo.findByEmail(email).get();
       }
       else {
           throw new IllegalAccessException("User not found");
       }
    }

    public void addAppUser(AppUser appUser) {
        if(!checkIfUserExists(appUser.getEmail())){
            appUserRepo.save(appUser);
        }
    }
}
