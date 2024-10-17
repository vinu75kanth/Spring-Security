package com.security.Security.Service;

import com.security.Security.Config.JwtService;
import com.security.Security.Model.Users;
import com.security.Security.Repo.DummyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private DummyRepo repo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public void setRepo(DummyRepo repo) {
        this.repo = repo;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    public Users registerUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public List<Users> getUsers() {
        return repo.findAll();
    }

    public String verify(Users user) {
        try {
//            System.out.println("in");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
            if(authentication.isAuthenticated()) {
                return jwtService.getJwtToken(user.getUserName());
            }
        }
        catch (AuthenticationException e) {
            return "Invalid username or password";
        }
        return "fail";
    }


}
