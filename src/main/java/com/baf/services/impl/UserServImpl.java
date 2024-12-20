package com.baf.services.impl;

import java.util.List;

import com.baf.data.entities.User;
import com.baf.data.repositories.UserRepository;
import com.baf.services.UserServ;

public class UserServImpl implements UserServ {
    private UserRepository userRepo;

    public UserServImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void insert(User user) {
        userRepo.insert(user);
    }

    public User getUserById(int userId) {
        return userRepo.getUserById(userId);
    }

    public void toggleUser(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            userRepo.toggleUser(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public List<User> selectAll() {
        return userRepo.selectAll();
    }

    @Override
    public User selectByLogin(String login) {
        return userRepo.selectByLogin(login);
    }

    @Override
    public User selectByMail(String mail) {
        return userRepo.selectByMail(mail);
    }

}
