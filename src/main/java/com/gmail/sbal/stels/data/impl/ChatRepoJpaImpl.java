package com.gmail.sbal.stels.data.impl;

import com.gmail.sbal.stels.data.ChatRepo;
import com.gmail.sbal.stels.models.Chat;
import com.gmail.sbal.stels.models.Message;
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
public class ChatRepoJpaImpl implements ChatRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Chat get(String chatName) {
        TypedQuery<Chat> query = entityManager.createQuery(
                "SELECT c FROM Chat c WHERE c.chatName = :chatName",
                Chat.class
        );
        query.setParameter("chatName", chatName);
        return query.getSingleResult();
    }

    @Override
    public void add(Chat chat) {
        entityManager.persist(chat);
    }

    @Override
    public void update(Chat chat) {
        entityManager.merge(chat);
    }

    @Override
    public void delete(Chat chat) {
        entityManager.remove(chat);
    }

    @Override
    public List<User> getUsersFromChat(Chat chat) {
        Query query = entityManager.createQuery(
                "SELECT u " +
                        "FROM User u JOIN u.chats uc" +
                        " WHERE uc.id = :chatId",
                User.class
        );
        query.setParameter("chatId", chat.getId());
        return query.getResultList();
    }

    @Override
    public List<Message> getMessagesFromChat(Chat chat) {
        TypedQuery<Message> query = entityManager.createQuery("SELECT m FROM Message m WHERE m.chat = :chat", Message.class);
        query.setParameter("chat", chat);

        return query.getResultList();
    }
}
