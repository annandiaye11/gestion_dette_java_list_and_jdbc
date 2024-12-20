package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Article;
import com.baf.data.entities.Debt;
import com.baf.data.entities.DetailDebt;
import com.baf.data.repositories.DetailDebtRepository;

public class DetailDebtRepositoryImplDB extends DatabaseImpl implements DetailDebtRepository {

    @Override
    public void insert(DetailDebt data) {
        String req = String.format(
                "Insert into \"public\".\"DetailDebt\" (quantity, prix, article_id, debt_id) values ('%d', '%d', '%d', '%d')",
                data.getQte(), data.getPrix(), data.getArticle().getId(), data.getDebt().getIdDebt());
        try {
            this.initPreparedStatement(req);
            this.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetailDebt> selectAll() {
        List<DetailDebt> list = new ArrayList<>();
        String req = "SELECT dd.id, dd.quantity, dd.prix, " +
                "a.id AS article_id, a.libelle AS article_libelle, a.qte_stock AS article_qte_stock, a.prix AS article_prix, "
                +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.amount_paid AS debt_paidMount, d.remaining_amount AS debt_remainingMount, d.is_achievied AS debt_isAchieved "
                +
                "FROM \"public\".\"DetailDebt\"  dd " +
                "JOIN \"public\".\"Article\"  a ON dd.article_id = a.id " +
                "JOIN \"public\".\"Debt\"  d ON dd.debt_id = d.id";

        ;
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public DetailDebt convertToObject(ResultSet rs) {
        DetailDebt detailDebt = new DetailDebt();
        try {
            detailDebt.setId(rs.getInt("id"));
            detailDebt.setQte(rs.getInt("quantity"));
            detailDebt.setPrix(rs.getInt("prix"));

            // Mapping de l'article
            int articleId = rs.getInt("article_id");
            if (articleId != 0) {
                Article article = new Article();
                article.setId(articleId);
                article.setLibelle(rs.getString("article_libelle"));
                article.setQte_stock(rs.getInt("article_qte_stock"));
                article.setPrix(rs.getInt("article_prix"));
                detailDebt.setArticle(article);
            }

            // Mapping de la dette
            int debtId = rs.getInt("debt_id");
            if (debtId != 0) {
                Debt debt = new Debt();
                debt.setIdDebt(debtId);
                debt.setMount(rs.getDouble("debt_mount"));
                debt.setDate(rs.getDate("debt_date"));
                debt.setPaidMount(rs.getDouble("debt_paidMount"));
                debt.setRemainingMount(rs.getDouble("debt_remainingMount"));
                debt.setAchieved(rs.getBoolean("debt_isAchieved"));
                detailDebt.setDebt(debt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailDebt;
    }

    @Override
    public DetailDebt getDetailDebtById(int idDetailDebt) {
        String req = String.format("SELECT dd.id, dd.quantity, dd.prix, " +
                "a.id AS article_id, a.libelle AS article_libelle, a.qte_stock AS article_qte_stock, a.prix AS article_prix, "
                +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.amount_paid AS debt_paidMount, d.remaining_amount AS debt_remainingMount, d.is_achievied AS debt_isAchieved "
                +
                "FROM \"public\".\"DetailDebt\"  dd " +
                "JOIN \"public\".\"Article\"  a ON dd.article_id = a.id " +
                "JOIN \"public\".\"Debt\"  d ON dd.debt_id = d.id" + 
                " WHERE dd.id = %d", idDetailDebt);
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                return this.convertToObject(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;

    }

    @Override
    public List<DetailDebt> getAllDetailDebtByArticleId(int idArticle) {
        String req = String.format("SELECT dd.id, dd.quantity, dd.prix, " +
                "a.id AS article_id, a.libelle AS article_libelle, a.qte_stock AS article_qte_stock, a.prix AS article_prix, "
                +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.amount_paid AS debt_paidMount, d.remaining_amount AS debt_remainingMount, d.is_achievied AS debt_isAchieved "
                +
                "FROM \"public\".\"DetailDebt\"  dd " +
                "JOIN \"public\".\"Article\"  a ON dd.article_id = a.id " +
                "JOIN \"public\".\"Debt\"  d ON dd.debt_id = d.id"+
                " where id = %d", idArticle);
        List<DetailDebt> list = new ArrayList<>();
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DetailDebt> selectAllByDebtId(int idDebt) {
        String req = String.format("SELECT dd.id, dd.quantity, dd.prix, " +
                "a.id AS article_id, a.libelle AS article_libelle, a.qte_stock AS article_qte_stock, a.prix AS article_prix, "
                +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.amount_paid AS debt_paidMount, d.remaining_amount AS debt_remainingMount, d.is_achievied AS debt_isAchieved "
                +
                "FROM \"public\".\"DetailDebt\"  dd " +
                "JOIN \"public\".\"Article\"  a ON dd.article_id = a.id " +
                "JOIN \"public\".\"Debt\"  d ON dd.debt_id = d.id"+
                "where id = %d", idDebt);
        List<DetailDebt> list = new ArrayList<>();
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
