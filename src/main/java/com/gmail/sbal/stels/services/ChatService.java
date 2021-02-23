package com.gmail.sbal.stels.services;

import com.gmail.sbal.stels.models.*;

import java.util.List;

/**
 * @author Alex Balashov
 */
public interface ChatService {
    Chat getChat(String chatName);
    void addChat(Chat chat);
    void updateChat(Chat chat);
    void deleteChat(Chat chat);

    Message getMessage(long id);
    void addMessage(Message message);
    void updateMessage(Message message);
    void deleteMessage(Message message);
    List<Message> getMessagesFromChat(Chat chat);

    List<User> getUsersFromChat(Chat chat);
}
