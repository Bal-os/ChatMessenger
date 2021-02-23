package com.gmail.sbal.stels.data;

import com.gmail.sbal.stels.models.Chat;
import com.gmail.sbal.stels.models.User;

import java.util.List;

/**
 * @author Alex Balashov
 */
public interface UserRepo extends DAO<User, String>{
    List<Chat> getChatsOfUser(User user);
}
