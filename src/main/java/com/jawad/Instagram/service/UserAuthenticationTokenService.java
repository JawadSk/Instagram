package com.jawad.Instagram.service;
import com.jawad.Instagram.model.User;
import com.jawad.Instagram.model.UserAuthenticationToken;
import com.jawad.Instagram.repository.IUserAuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationTokenService {

    @Autowired
    IUserAuthenticationTokenRepo userAuthenticationTokenRepository;

    public void saveToken(UserAuthenticationToken token) {
        userAuthenticationTokenRepository.save(token);
    }

    public UserAuthenticationToken getAuthTokenByUser(User user) {
        return userAuthenticationTokenRepository.findByUser(user);
    }
}
