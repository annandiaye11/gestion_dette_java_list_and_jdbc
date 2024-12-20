package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.DebtRequestRepository;

public class DebtRequestRepositoryImplDB extends DatabaseImpl implements DebtRequestRepository {

    @Override
    public void insert(DebtRequest data) {
        String req = String.format(
                "Insert into \"public\".\"DebtRequest\" (totalamount, date, status, client_id) values ('%d', '%s', '%s', '%d')",
                data.getTotalAmount(), data.getDate(), data.getStatus(), data.getClient().getId());
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertWithId(DebtRequest data, int index) {
        String req = "INSERT INTO \"public\".\"DebtRequest\" (totalamount, date, status, client_id) VALUES (?, ?, ?, ?) RETURNING id";
        int generatedId = -1;
        try {
            this.initPreparedStatement(req);
            this.ps.setDouble(1, data.getTotalAmount());
            // Conversion de la date en format SQL
            java.sql.Date sqlDate = new java.sql.Date(data.getDate().getTime());
            this.ps.setInt(1, data.getTotalAmount());
            this.ps.setDate(2, sqlDate);
            this.ps.setString(3, data.getStatus());
            this.ps.setInt(4, data.getClient().getId());
            // Exécuter la requête et récupérer le ResultSet
            this.ps.executeUpdate();

            ResultSet rs = this.ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                data.setId(generatedId);
            }
            // Récupérer l'ID généré
            System.out.println("mmmmmmmmm:" + generatedId);
            // Mettre à jour l'ID dans l'objet

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    @Override
    public List<DebtRequest> selectAll() {
        List<DebtRequest> list = new ArrayList<>();
        String req = "SELECT dr.id, dr.date, dr.totalAmount, dr.status, " +
                "c.id AS client_id, c.surname AS client_surname, " +
                "c.telephone AS client_telephone, c.address AS client_adresse, c.date AS client_createAt " +
                "FROM \"public\".\"DebtRequest\" dr " +
                "JOIN \"public\".\"Client\" c ON dr.client_id = c.id";
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public DebtRequest convertToObject(ResultSet rs) {
        DebtRequest debtRequest = new DebtRequest();
        try {
            debtRequest.setId(rs.getInt("id"));
            debtRequest.setDate(rs.getDate("date"));
            debtRequest.setTotalAmount(rs.getInt("totalAmount"));
            debtRequest.setStatus(rs.getString("status"));

            // Mapping du client
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debtRequest;
    }

    @Override
    public DebtRequest getDebtRequestByClient(Client client) {
        String req = String.format("SELECT dr.id, dr.date, dr.totalAmount, " +
                "c.id AS client_id, c.surname AS client_surname, " +
                "c.telephone AS client_telephone, c.adresse AS client_adresse, c.date AS client_createAt " +
                "FROM DebtRequest dr " +
                "JOIN \"public\".\"DebtRequest\" c ON dr.client_id = c.id" +
                "where clientId = '%d'", client.getId());
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
    public void updateStatus(int idDebtRequest, String status) {
        String req = String.format("Update \"public\".\"DebtRequest\" set status = '%s' where id = '%s'", status,
                idDebtRequest);
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DebtRequest selectById(int idDebtRequest) {
        String req = String.format("SELECT dr.id, dr.date, dr.totalAmount, " +
                "c.id AS client_id, c.surname AS client_surname, " +
                "c.telephone AS client_telephone, c.address AS client_adresse, c.date AS client_createAt " +
                "FROM \"public\".\"DebtRequest\" dr " +
                "JOIN \"public\".\"Client\" c ON dr.client_id = c.id" +
                " where dr.id = '%s'", idDebtRequest);
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

}
