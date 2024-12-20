package com.baf.data.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DebtRequest {
    int id;
    Date date;
    private int totalAmount;
    private String status;
    private int nbre = 0;
    Client client;
    private List<DetailDebtRequest> detailDebts = new ArrayList<>();

    public void setDetailDebts(List<DetailDebtRequest> detailDebts) {
        this.detailDebts = detailDebts;
    }
    public int getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public List<DetailDebtRequest> getDetailDebts() {
        return detailDebts;
    }
    public DebtRequest() {
        id = nbre++;
    }
    public int getId() {
        return id;
    }

    public void addDetailDebt(DetailDebtRequest detailDebt){
        if (this.detailDebts == null) {
            this.detailDebts = new ArrayList<>();
        }
        detailDebts.add(detailDebt);
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public DebtRequest(int id, Date date, Client client, List<Article> articles, String status) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.status = status;
    }
    @Override
    public String toString() {
        return "DebtRequest [Id=" + id + ", date=" + date + ", client=" + client.toString()
                + ", detailDebts=" + detailDebts.toString() + ", status=" + status + ", nbre=" + nbre + ", total=" + totalAmount + "]";
    }
  
    
}
