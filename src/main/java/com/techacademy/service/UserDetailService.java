package com.techacademy.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class UserDetailService implements UserDetailsService {
    private final AuthenticationRepository authenticationRepository;

    public UserDetailService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Authentication> authentication = authenticationRepository.findById(username);
        if (!authentication.isPresent()) {
            throw new UsernameNotFoundException("Exception: UserName Not Found");
        }
        return new UserDetail(authentication.get().getEmployee());
    }
}
