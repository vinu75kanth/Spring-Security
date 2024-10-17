package com.security.Security.Service;

import com.security.Security.Model.UserPrincipal;
import com.security.Security.Model.Users;
import com.security.Security.Repo.DummyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private DummyRepo repo;

    @Autowired
    public void setRepo(DummyRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByUserName(username);
//        System.out.println(user);

        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username + " not found");
        }
        return new UserPrincipal(user);
    }
}
