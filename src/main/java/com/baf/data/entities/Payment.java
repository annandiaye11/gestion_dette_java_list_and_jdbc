package com.baf.data.entities;

import java.util.Date;

import lombok.Data;

@Data
public class Payment {
    private int id;
    private Date date;
    private double amount;
    private Debt debt;
}
