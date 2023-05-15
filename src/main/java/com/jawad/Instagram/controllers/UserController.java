package com.jawad.Instagram.controllers;

import com.jawad.Instagram.dto.SignInInput;
import com.jawad.Instagram.dto.SignUpInOutPut;
import com.jawad.Instagram.dto.SignUpInput;
import com.jawad.Instagram.dto.UpdateInput;
import com.jawad.Instagram.model.User;
import com.jawad.Instagram.model.UserAuthenticationToken;
import com.jawad.Instagram.service.UserAuthenticationTokenService;
import com.jawad.Instagram.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationTokenService authenticationTokenService;

    @PostMapping("signup/")
    public ResponseEntity<SignUpInOutPut> signUp(@Valid @RequestBody SignUpInput userSignUpInput){
        SignUpInOutPut response = userService.checkAndRegister(userSignUpInput);
        HttpStatus status;
        if (response.getToken() == null)
            status = HttpStatus.BAD_REQUEST;
        else
            status = HttpStatus.CREATED;

        return new ResponseEntity<>(response, status);
    }

    @PostMapping("signin/")
    public ResponseEntity<SignUpInOutPut> signIn(@Valid @RequestBody SignInInput userSignInInput){
        boolean isPresent = userService.isAuthenticate(userSignInInput.getEmail());
        SignUpInOutPut response;
        HttpStatus status;
        if (!isPresent) {
            status = HttpStatus.NOT_ACCEPTABLE;
            response = new SignUpInOutPut("User is not exits, sign up instead", null);
        }
        else{
            response = userService.loginUser(userSignInInput);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(response, status);
    }

    @PutMapping("update/")
    public ResponseEntity<String> updatedUser(@RequestParam Long id, @RequestParam String token, @RequestBody UpdateInput updateInput){
        User savedUser = userService.getUser(id);
        if (savedUser == null){
            return new ResponseEntity<>("User does not exist!", HttpStatus.BAD_REQUEST);
        }

        UserAuthenticationToken authToken = authenticationTokenService.getAuthTokenByUser(savedUser);
        if (!token.equals(authToken.getToken())){
            return new ResponseEntity<>("User token is invalid!", HttpStatus.BAD_REQUEST);
        }

        userService.updateUser(id, updateInput);
        return new ResponseEntity<>("User updated successfully!", HttpStatus.ACCEPTED);
    }
}
