package com.wipro.productApi.security.userDetails;

import com.wipro.productApi.context.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;
    private Collection<SimpleGrantedAuthority> roles =
            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(userService.findByLogin(username));
    }

    public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<SimpleGrantedAuthority> roles){
        return roles;
    }
}
