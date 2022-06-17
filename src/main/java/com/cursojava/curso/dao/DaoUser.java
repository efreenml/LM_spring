package com.cursojava.curso.dao;

import com.cursojava.curso.models.User;

import java.util.List;

public interface DaoUser {

    List<User> getUsers();

    void deleteUser(Long id);

    void registerUser(User user);

    User verifyUserPassowrd(User user);
}
