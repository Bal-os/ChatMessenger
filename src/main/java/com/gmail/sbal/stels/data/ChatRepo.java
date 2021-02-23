package com.gmail.sbal.stels.data;

import com.gmail.sbal.stels.models.*;

import java.util.List;

/**
 * @author Alex Balashov
 */
public interface ChatRepo extends DAO<Chat, String>{
    List<User> getUsersFromChat(Chat chat);
    List<Message> getMessagesFromChat(Chat chat);
}
