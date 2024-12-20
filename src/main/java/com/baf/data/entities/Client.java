package com.baf.data.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Client {
    private int id;
    private String surname;
    private String telephone;
    private String adresse;
    private User user;
    private Date createAt = new Date();
    private List<Debt> debts;
    public void addDebt(Debt debt){
        debts.add(debt);
    }
    private User userAccount;
    @Override
    public String toString() {
        return "Client [id=" + id + ", surname=" + surname + ", telephone=" + telephone + ", createAt=" + createAt
                + "]";
    }

    // private String 
}
