package com.example.demo;

import com.example.demo.security.FirebaseIdTokenVerifier;
import com.example.demo.security.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    @Autowired
    FirebaseIdTokenVerifier firebaseIdTokenVerifier;

    @PostMapping("/verify/")
    public ResponseEntity idTokenVerify(@RequestBody TokenRequest request)  {
      return firebaseIdTokenVerifier.verifyToken(request.getToken());
    }


}
