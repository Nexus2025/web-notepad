package com.notepad.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public void autoLogin(String userName, String password) {
        UserDetails userDetails = userService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationToken.setDetails(userDetails);
        authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
