package com.baf.data.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.User;
import com.baf.data.repositories.UserRepository;

public class UserList extends RepositoryImplList<User> implements UserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public void insert(User user) {
        users.add(user);
    }

    @Override
    public void toggleUser(User user) {
        for (User u : users) {
            if (u.getIdUser() == user.getIdUser()) {
                u.setIsActive(!u.getIsActive());
                return;
            }
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public User getUserById(int userId) {
        for (User u : users) {
            if (u.getIdUser() == userId) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> selectAll() {
        return users;
    }

    @Override
    public User selectByLogin(String login) {
        for (User u : users) {
            if (u.getLogin() == login) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User selectByMail(String mail) {
        for (User u : users) {
            if (u.getEmail() == mail) {
                return u;
            }
        }
        return null;
    }

}
