package com.gmail.sbal.stels.services;

import com.gmail.sbal.stels.data.UserRepo;
import com.gmail.sbal.stels.models.Chat;
import com.gmail.sbal.stels.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alex Balashov
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Transactional(readOnly=true)
    @Override
    public User get(String nickName) {
        return userRepo.get(nickName);
    }

    @Transactional
    @Override
    public void add(User user) {
        userRepo.add(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userRepo.update(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Chat> getChatsOfUser(User user) {
        return userRepo.getChatsOfUser(user);
    }
}
