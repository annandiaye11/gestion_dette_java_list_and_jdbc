package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Article;
import com.baf.data.repositories.ArticleRepository;

public class ArticleRepositoryImplDB extends DatabaseImpl implements ArticleRepository {


    @Override
    public Article selectById(int id) {
        String req = String.format("SELECT * FROM \"public\".\"Article\" WHERE id = %d", id);
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                return this.convertToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void insert(Article article) {
        String req = String.format(
                "INSERT INTO \"public\".\"Article\" (libelle, qte_stock, prix ) values ('%s', %d, %d)",
                 article.getLibelle(), article.getQte_stock(), article.getPrix());
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> selectAll() {
        List<Article> articles = new ArrayList<>();
        String req = "SELECT * from \"public\".\"Article\"";
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                articles.add(this.convertToObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }


        return articles;
    }

    @Override
    public Article convertToObject(ResultSet rs) {
        Article article = new Article();
        try {
            article.setId(rs.getInt("id"));
            article.setLibelle(rs.getString("libelle"));
            article.setQte_stock(rs.getInt("qte_stock"));
            article.setPrix(rs.getInt("prix"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return article;
    }

    @Override
    public Article selectByLibelle(String libelle) {
        String req = String.format("SELECT * from \"public\".\"Article\" where libelle = '%s'", libelle);
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                return this.convertToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
}
