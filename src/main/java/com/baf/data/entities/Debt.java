package com.baf.data.entities;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Debt {
    private int idDebt;
    private Double mount; 
    private Date date = new Date();
    private Double paidMount; 
    private double remainingMount;
    private boolean isAchieved ;
    private Client client;
    private List<Payment> payments;
    private List<DetailDebt> detailDebts;

 
   public void addPayment(Payment payment){
       payments.add(payment);
   }
    public void addDetailDebt(DetailDebt detailDebt){
         detailDebts.add(detailDebt);
    }
}
