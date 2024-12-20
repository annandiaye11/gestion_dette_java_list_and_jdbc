package com.baf.data.entities;

import lombok.Data;
@Data
public class DetailDebtRequest {
    private int id;
    private Article article;
    private DebtRequest debtRequest;
    private int qte;
    private int prix;
    private static int nbre = 0;

    public DetailDebtRequest() {
        id = nbre++;
    }

    public DetailDebtRequest(Article article, int qte, int prix) {
        this.article = article;
        this.qte = qte;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "DetailDebt [id=" + id +  ", article=" + article.toString() + 
                 ", qte=" + qte + ", prix=" + prix + "]";
    }

    
}
