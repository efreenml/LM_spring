package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.DaoUser;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private DaoUser daoUser;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "test")
    public List<String> test() {
        return List.of("Test1", "test2", "Test3");
    }

    @RequestMapping(value = "api/deleteUser/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id, @RequestHeader(value="Authorization") String token) {
        if (!validateToken(token)) {
            return;
        }

        daoUser.deleteUser(id);
    }

    @RequestMapping(value = "api/registerUser", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        String hashedPassword = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hashedPassword);

        daoUser.registerUser(user);
    }

    @RequestMapping(value = "user")
    public User user() {
        User user = new User();
        user.setPassword("pass1");
        user.setEmail("email@mail.com");
        user.setName("name1");
        user.setPhone("55-22-11-33-22-11");
        return user;
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setPassword("pass1");
        user.setEmail("email@mail.com");
        user.setName("name1");
        user.setPhone("55-22-11-33-22-11");
        return user;
    }

    @RequestMapping(value = "api/users")
    public List<User> getUsers(@RequestHeader(value="Authorization") String token) {
        if (!validateToken(token)) {
            return null;
        }
        List<User> users = daoUser.getUsers();

        return users;
    }

    private boolean validateToken (String token) {
        String idUser = jwtUtil.getKey(token);
        return idUser != null;
    }
}
