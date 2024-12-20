package com.baf.data.repositories;

import com.baf.core.Repository;
import com.baf.data.entities.User;

public interface UserRepository extends Repository<User> {
    void toggleUser(User user);
    User getUserById(int userId);
    User selectByLogin(String login);
    User selectByMail(String mail);
}
