package com.gmail.sbal.stels.data.impl;

import com.gmail.sbal.stels.data.UserRepo;
import com.gmail.sbal.stels.models.Chat;
import com.gmail.sbal.stels.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Alex Balashov
 */
@Repository
public class UserRepoJpaImpl implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User get(String nickName) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.nickName = :nickName",
                User.class
        );
        query.setParameter("nickName", nickName);
        return query.getSingleResult();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public List<Chat> getChatsOfUser(User user) {
        Query query = entityManager.createQuery(
                "SELECT c " +
                        "FROM Chat c JOIN c.users uc" +
                        " WHERE uc.id = :userId",
                Chat.class
        );
        query.setParameter("userId", user.getId());
        return query.getResultList();
    }
}
