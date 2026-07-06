package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;



    @PostMapping
    public void createUser(@RequestBody User user){

        userService.saveNewUser(user);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        //you only reached here because authentication is alr done
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<User> userInDb = Optional.ofNullable(userService.findByUserName(userName));

        if(userInDb.isPresent()){
            User existingUser = userInDb.get();

            existingUser.setPassword(user.getPassword());
            userService.saveNewUser(existingUser);

            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

