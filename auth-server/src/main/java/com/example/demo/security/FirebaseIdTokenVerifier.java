package com.example.demo.security;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FirebaseIdTokenVerifier {

    public ResponseEntity verifyToken(String token){
        System.out.println(token);
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            return ResponseEntity.ok("success");
        }
        catch (FirebaseException e){
           throw new RuntimeException(e.getMessage());
        }

    }

}
