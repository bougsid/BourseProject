package com.bougsid.jwt;

import com.bougsid.dao.UserRepository;
import com.bougsid.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login)
            throws ServletException {
        User user = userRepository.findByUsernameAndPassword(login.name, login.password);

        if (user == null) throw new ServletException("Invalid login");

        return new LoginResponse(Jwts.builder().setSubject(login.name)
                                     .claim("roles", "user").setIssuedAt(new Date())
                                     .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;
        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}
