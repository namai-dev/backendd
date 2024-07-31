package com.laggo.user.appuser;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class AppUserController {
    public final AppUserService appUserService;

    static class EmailRequest{
        String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    @PostMapping(path = "/registerUser")
    public ResponseEntity saveUser(@RequestBody AppUser appUser){
        appUserService.addAppUser(appUser);
        return ResponseEntity.ok("User Saved");
    }


    @GetMapping(path = "/userprofile")
    public ResponseEntity<AppUser> getUserDetails(@RequestBody EmailRequest request) throws IllegalAccessException {
        return ResponseEntity.ok(appUserService.getUser(request.getEmail()));
    }

    public 
}
