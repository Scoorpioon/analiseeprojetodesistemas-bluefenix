package com.bluefenix.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Repositories.AtendenteRepository;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    AtendenteRepository atendenteRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return atendenteRepositorio.findByEmail(email);
    }
}