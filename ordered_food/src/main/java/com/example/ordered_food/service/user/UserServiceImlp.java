package com.example.ordered_food.service.user;

import com.example.ordered_food.config.JwtProvider;
import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.User;
import com.example.ordered_food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImlp implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public  UserServiceImlp(UserRepository userRepository,JwtProvider jwtProvider){
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return  user.get();
        }
        throw  new UserException("user not found with id : "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw  new UserException("user not found with email"+ email);
        }
        return  user;
    }
}
