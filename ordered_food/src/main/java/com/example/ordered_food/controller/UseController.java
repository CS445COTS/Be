package com.example.ordered_food.controller;


import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.User;
import com.example.ordered_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UseController {
    @Autowired
    private UserService userService;


    @GetMapping("/users/profile")
    public ResponseEntity<User> findUserProfileByjwt(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        return  new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
}
