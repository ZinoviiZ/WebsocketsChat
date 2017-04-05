package com.risingapp.likeit.service;

//import com.risingapp.likeit.entity.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zinoviy on 9/27/16.
 */

@Service
public class UserDetailsServiceImpl {//implements UserDetailsService {
//
//    @Autowired
//    private SessionService sessionService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = sessionService.getUserByEmail(username);
//        if(user == null) {
//            throw new UsernameNotFoundException(username + " not found :(((");
//        }
//        Set<GrantedAuthority> roles = new HashSet<>();
//        roles.add(new SimpleGrantedAuthority(user.getUserRole().toString()));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
//    }
}
