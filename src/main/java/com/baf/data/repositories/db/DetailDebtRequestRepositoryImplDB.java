package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.entities.DetailDebtRequest;
import com.baf.data.repositories.DetailDebtRequestRepository;

public class DetailDebtRequestRepositoryImplDB extends DatabaseImpl implements DetailDebtRequestRepository {

    @Override
    public void insert(DetailDebtRequest data) {
        String req = String.format(
                "Insert into detailDebtRequest (id, article_id, debt_request_id, qte, prix) values (%d, %d, %f)",
                data.getId(), data.getArticle().getId(), data.getDebtRequest().getIdDebtRequest(), data.getQte(),
                data.getPrix());
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetailDebtRequest> selectAll() {
        String req = "SELECT ddr.id, ddr.qte, ddr.prix, " +
                "a.id AS article_id, a.libelle AS article_libelle, a.qteStock AS article_qteStock, a.prix AS article_prix, "
                +
                "dr.idDebtRequest AS debtRequest_id, dr.date AS debtRequest_date, dr.totalAmount AS debtRequest_totalAmount, "
                +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, c.adresse AS client_adresse, c.createAt AS client_createAt "
                +
                "FROM DetailDebtRequest ddr " +
                "JOIN Article a ON ddr.article_id = a.id " +
                "JOIN DebtRequest dr ON ddr.debtRequest_id = dr.idDebtRequest " +
                "JOIN Client c ON dr.client_id = c.id";

        List<DetailDebtRequest> list = new ArrayList<>();
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

    @Override
    public DetailDebtRequest convertToObject(ResultSet rs) {
        DetailDebtRequest detailDebtRequest = new DetailDebtRequest();
        try {
            detailDebtRequest.setId(rs.getInt("id"));
            detailDebtRequest.setQte(rs.getInt("qte"));
            detailDebtRequest.setPrix(rs.getDouble("prix"));

            // Mapping de l'article
            int articleId = rs.getInt("article_id");
            if (articleId != 0) {
                Article article = new Article();
                article.setId(articleId);
                article.setLibelle(rs.getString("article_libelle"));
                article.setQteStock(rs.getInt("article_qteStock"));
                article.setPrix(rs.getInt("article_prix"));
                detailDebtRequest.setArticle(article);
            }

            // Mapping de la demande de dette
            int debtRequestId = rs.getInt("debtRequest_id");
            if (debtRequestId != 0) {
                DebtRequest debtRequest = new DebtRequest();
                debtRequest.setIdDebtRequest(debtRequestId);
                debtRequest.setDate(rs.getDate("debtRequest_date"));
                debtRequest.setTotalAmount(rs.getDouble("debtRequest_totalAmount"));

                // Mapping du client dans la demande de dette
                int clientId = rs.getInt("client_id");
                if (clientId != 0) {
                    Client client = new Client();
                    client.setId(clientId);
                    client.setSurname(rs.getString("client_surname"));
                    client.setTelephone(rs.getString("client_telephone"));
                    client.setAdresse(rs.getString("client_adresse"));
                    client.setCreateAt(rs.getTimestamp("client_createAt"));
                    debtRequest.setClient(client);
                }

                detailDebtRequest.setDebtRequest(debtRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailDebtRequest;
    }

    @Override
    public DetailDebtRequest getDetailDebtById(int idDetailDebt) {
        String req = String.format("SELECT ddr.id, ddr.qte, ddr.prix, " +
             "a.id AS article_id, a.libelle AS article_libelle, a.qteStock AS article_qteStock, a.prix AS article_prix, " +
             "dr.idDebtRequest AS debtRequest_id, dr.date AS debtRequest_date, dr.totalAmount AS debtRequest_totalAmount, " +
             "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, c.adresse AS client_adresse, c.createAt AS client_createAt " +
             "FROM DetailDebtRequest ddr " +
             "JOIN Article a ON ddr.article_id = a.id " +
             "JOIN DebtRequest dr ON ddr.debtRequest_id = dr.idDebtRequest " +
             "JOIN Client c ON dr.client_id = c.id"+
             "WHERE id = %d", idDetailDebt);
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
    public List<DetailDebtRequest> selectAllByArticleId(int idArticle) {
        String req = String.format("SELECT ddr.id, ddr.qte, ddr.prix, " +
             "a.id AS article_id, a.libelle AS article_libelle, a.qteStock AS article_qteStock, a.prix AS article_prix, " +
             "dr.idDebtRequest AS debtRequest_id, dr.date AS debtRequest_date, dr.totalAmount AS debtRequest_totalAmount, " +
             "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, c.adresse AS client_adresse, c.createAt AS client_createAt " +
             "FROM DetailDebtRequest ddr " +
             "JOIN Article a ON ddr.article_id = a.id " +
             "JOIN DebtRequest dr ON ddr.debtRequest_id = dr.idDebtRequest " +
             "JOIN Client c ON dr.client_id = c.id" + 
             " WHERE article_id = %d", idArticle);
        List<DetailDebtRequest> list = new ArrayList<>();
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

    @Override
    public List<DetailDebtRequest> selectAllByDebtRequestId(int idDebtRequest) {
        String req = String.format("SELECT ddr.id, ddr.qte, ddr.prix, " +
             "a.id AS article_id, a.libelle AS article_libelle, a.qteStock AS article_qteStock, a.prix AS article_prix, " +
             "dr.idDebtRequest AS debtRequest_id, dr.date AS debtRequest_date, dr.totalAmount AS debtRequest_totalAmount, " +
             "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, c.adresse AS client_adresse, c.createAt AS client_createAt " +
             "FROM DetailDebtRequest ddr " +
             "JOIN Article a ON ddr.article_id = a.id " +
             "JOIN DebtRequest dr ON ddr.debtRequest_id = dr.idDebtRequest " +
             "JOIN Client c ON dr.client_id = c.id" + 
             "WHERE debt_request_id = %d", idDebtRequest);
        List<DetailDebtRequest> list = new ArrayList<>();
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