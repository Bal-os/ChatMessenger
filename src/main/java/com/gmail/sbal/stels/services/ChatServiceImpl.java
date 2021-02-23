package com.gmail.sbal.stels.services;

import com.gmail.sbal.stels.data.ChatRepo;
import com.gmail.sbal.stels.data.DAO;
import com.gmail.sbal.stels.models.Chat;
import com.gmail.sbal.stels.models.Message;
import com.gmail.sbal.stels.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alex Balashov
 */
@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private DAO<Message, Long> messageDAO;

    @Transactional(readOnly=true)
    @Override
    public Chat getChat(String chatName) {
        return chatRepo.get(chatName);
    }

    @Transactional
    @Override
    public void addChat(Chat chat) {
        chatRepo.add(chat);
    }

    @Transactional
    @Override
    public void updateChat(Chat chat) {
        chatRepo.update(chat);
    }

    @Transactional
    @Override
    public void deleteChat(Chat chat) {
        chatRepo.delete(chat);
    }

    @Transactional(readOnly=true)
    @Override
    public Message getMessage(long id) {
        return messageDAO.get(id);
    }

    @Transactional
    @Override
    public void addMessage(Message message) {
        messageDAO.add(message);
    }

    @Transactional
    @Override
    public void updateMessage(Message message) {
        messageDAO.update(message);
    }

    @Transactional
    @Override
    public void deleteMessage(Message message) {
        messageDAO.delete(message);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Message> getMessagesFromChat(Chat chat) {
        return chatRepo.getMessagesFromChat(chat);
    }

    @Transactional(readOnly=true)
    @Override
    public List<User> getUsersFromChat(Chat chat) {
        return chatRepo.getUsersFromChat(chat);
    }
}
