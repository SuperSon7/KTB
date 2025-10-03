package com.vani.week4.coummnity.user.repository;

import com.vani.week4.coummnity.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    EntityManager em;
    Optional<User> findByUsername(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE email = :useremail", User.class);
        query.setParameter("useremail", email);

        List<User> users = query.getResultList();

        return Optional.empty();
    }
}
