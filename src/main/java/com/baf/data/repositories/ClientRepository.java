package com.baf.data.repositories;

import com.baf.core.Repository;
import com.baf.data.entities.Client;
import com.baf.data.entities.User;

public interface ClientRepository extends Repository<Client> {
    Client selectByTel(String tel);
    Client selectBySurname(String surname);
    Client selectById(int id);
    void   createAccount(int id, User user);
}