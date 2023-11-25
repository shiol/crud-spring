package br.com.shiol.crud.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Here, you would typically fetch user details from a database
        // For simplicity, let's use a hard-coded user

        if ("user".equals(username)) {
            return new User("user", "$2a$10$siFeZHtmtjPQmZHcd05v8O.9e2kWqBdDHmVxGdq/GvBAM9Yat5OZe", new ArrayList<>());
            // The above password is the bcrypt hash of "password"
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
