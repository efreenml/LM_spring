package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.DaoUser;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {

    @Autowired
    private DaoUser daoUser;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String registerUser(@RequestBody User user) {
        User userVerify = daoUser.verifyUserPassowrd(user);

    if (userVerify != null) {
        String token = jwtUtil.create(String.valueOf(userVerify.getId()), userVerify.getEmail());
        return token;

    }

    return "not found 401";

    }


}
