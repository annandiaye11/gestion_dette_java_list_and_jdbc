package com.baf.services;

import com.baf.core.Service;
import com.baf.data.entities.Client;
import com.baf.data.entities.User;


public interface ClientService extends Service<Client>{
    Client selectByTel(String tel);
    Client selectBySurname(String surname);
    void    createAccount(int id, User user);
    
}