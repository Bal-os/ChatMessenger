package com.gmail.sbal.stels.data.impl;

import com.gmail.sbal.stels.data.DAO;
import com.gmail.sbal.stels.models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Alex Balashov
 */
@Repository
public class MessageDaoJpaIml implements DAO<Message, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Message get(Long id) {
        return entityManager.getReference(Message.class, id);
    }

    @Override
    public void add(Message message) {
        entityManager.persist(message);
    }

    @Override
    public void update(Message message) {
        entityManager.merge(message);
    }

    @Override
    public void delete(Message message) {
        entityManager.remove(message);
    }
}
