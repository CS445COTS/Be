package com.example.ordered_food.controller;


import com.example.ordered_food.config.JwtProvider;
import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.User;
import com.example.ordered_food.repository.UserRepository;
import com.example.ordered_food.request.LoginUser;
import com.example.ordered_food.response.AuthResponse;
import com.example.ordered_food.service.user.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserServiceImpl customUserService;

    public AuthController(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder, CustomUserServiceImpl customUserService) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.customUserService = customUserService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createuserHandle(@RequestBody User user) throws UserException {
        AuthResponse authResponse = new AuthResponse();
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();


        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist != null){
            authResponse.setMessage("email này đã có người đăng kí");
            throw  new UserException("Email is Already Used With Another Account");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User saveUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getEmail(),saveUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);


        authResponse.setJwt(token);
        authResponse.setMessage("đăng kí thành công");

        return  new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);


    }

    @PostMapping("/signin")
    public  ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginUser loginRequest){
        AuthResponse authResponse = new AuthResponse();
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        if(token == null){
            authResponse.setMessage("đăng nhập thất bại");
        }
        authResponse.setJwt(token);
        authResponse.setMessage("đăng nhập thành công");
        return  new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);

    }

    private  Authentication authenticate(String username,String password){
        UserDetails userDetails = customUserService.loadUserByUsername(username);
        if(userDetails == null){
            throw  new BadCredentialsException("invalid  UserName");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("invalid Password...");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }



}
