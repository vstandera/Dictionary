package com.sentence.dictionary.services;

import com.sentence.dictionary.converters.UserToUserDetails;
import com.sentence.dictionary.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private UserToUserDetails userUserDetailsConverter;

    public UserDetailServiceImpl(UserRepository userRepository, UserToUserDetails userUserDetailsConverter) {
        this.userRepository = userRepository;
        this.userUserDetailsConverter = userUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(userRepository.findByUsername(s));
    }
}
