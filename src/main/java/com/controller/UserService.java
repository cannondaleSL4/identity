package com.controller;

import com.identity.TokenUser;
import com.model.user.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by dima on 21.01.18.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    String getLoggedUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null){
            return "nosession";
        }
        return auth.getName();
    }

    public User getLoggedUser(){
        String loggedUserId = getLoggedUserId();
        User user = getUserInfoUserId(loggedUserId);
        return  user;
    }

    public User getUserInfoUserId(String userId){
        return userRepository.findOneById(userId).orElseGet( () -> new User());
    }

    @Override
    public TokenUser loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findOneUserByName(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        TokenUser currentUser;
        if (user.isActive() == true){
            currentUser = new TokenUser(user);
        } else {
            throw new DisabledException("User is not activated (Disabled User)");
        }
        detailsChecker.check(currentUser);
        return currentUser;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
