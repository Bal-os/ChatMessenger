package com.gmail.sbal.stels.services;

import com.gmail.sbal.stels.models.Chat;
import com.gmail.sbal.stels.models.User;

import java.util.List;

/**
 * @author Alex Balashov
 */
public interface UserService {
    User get(String nickName);
    void add(User user);
    void update(User user);
    void delete(User user);

    List<Chat> getChatsOfUser(User user);
}
