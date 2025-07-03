package com.project.Farmer.Support.System.Controller;


import com.project.Farmer.Support.System.Entity.UserAuthEntity;
import com.project.Farmer.Support.System.Service.UserAuthEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private static final Logger logger= LoggerFactory.getLogger(UserAuthController.class);
    @Autowired
     private UserAuthEntityService userAuthEntityService;
    @Autowired
     private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserAuthEntity userAuthEntity){
        logger.info("Printing all data before saving it {}", userAuthEntity);
        //Before saving HashCode the password
        userAuthEntity.setPassword(passwordEncoder.encode(userAuthEntity.getPassword()));
        //save user
        userAuthEntityService.save(userAuthEntity);
        logger.info("Printing all data  after saving it {}", userAuthEntity);
        return  ResponseEntity.ok("User saved successfully");
    }
}
