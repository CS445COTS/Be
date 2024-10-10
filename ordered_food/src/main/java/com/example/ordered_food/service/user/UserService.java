package com.example.ordered_food.service.user;


import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public  User findUserProfileByJwt(String jwt) throws  UserException;
}
