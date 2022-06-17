package com.cursojava.curso.dao;

import com.cursojava.curso.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class DaoUserImp implements DaoUser{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getUsers() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);

        entityManager.remove(user);
    }

    @Override
    public void registerUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User verifyUserPassowrd(User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String query = "FROM User WHERE email = :email";
        List<User> foundUsers =  entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();
        if (foundUsers.isEmpty()) {
            return null;
        }
        if (argon2.verify(foundUsers.get(0).getPassword(), user.getPassword())) {
            return foundUsers.get(0);
        } else {
            return null;
        }
    }
}
