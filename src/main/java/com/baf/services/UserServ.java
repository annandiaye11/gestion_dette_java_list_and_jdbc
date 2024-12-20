package com.baf.services;

import com.baf.core.Service;
import com.baf.data.entities.User;

public interface UserServ extends Service<User> {
    User getUserById(int userId);
    void toggleUser(int userId);
    User selectByLogin(String login);
    User selectByMail(String mail);
}
