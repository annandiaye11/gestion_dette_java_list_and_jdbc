package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.data.entities.Payment;
import com.baf.data.repositories.PaymentRepository;

public class PaymentRepositoryImplDB extends DatabaseImpl implements PaymentRepository {

    public void insert(Payment data) {
        String req = String.format("Insert into \"public\".\"Payment\" (date, amount, debt_id) values (%s, %d, %d)",
                data.getDate(), data.getAmount(), data.getDebt().getIdDebt());
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Payment> selectAll() {
        String req = "SELECT p.id, p.date, p.amount, " +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.amount_paid AS debt_paidMount, d.remaining_amount AS debt_remainingMount, d.is_achievied AS debt_isAchieved, "
                +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, c.adresse AS client_adresse, c.date AS client_createAt "
                +
                "FROM \"public\".\"Payment\" p " +
                "JOIN \"public\".\"Debt\" d ON p.debt_id = d.id " +
                "JOIN \"public\".\"Client\" c ON d.client_id = c.id";

        List<Payment> list = new ArrayList<>();
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

    public Payment getPaymentById(int idPayment) {
        String req = String.format(
                "SELECT p.id, p.date, p.amount, " +
                        "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                        "d.amount_paid AS debt_paidMount, d.remaining_amount AS debt_remainingMount, d.is_achievied AS debt_isAchieved, "
                        +
                        "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, c.adresse AS client_adresse, c.date AS client_createAt "
                        +
                        "FROM \"public\".\"Payment\" p " +
                        "JOIN \"public\".\"Debt\" d ON p.debt_id = d.id " +
                        "JOIN \"public\".\"Client\" c ON d.client_id = c.id" +
                        "where id = %d",
                idPayment);
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

    public Payment getPaymentByDebtId(int idDebt) {
        String req = String.format("SELECT p.id, p.date, p.amount, " +
                "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
                "d.paidMount AS debt_paidMount, d.remainingMount AS debt_remainingMount, d.isAchieved AS debt_isAchieved, "
                +
                "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, c.adresse AS client_adresse, c.date AS client_createAt "
                +
                "FROM \"public\".\"Payment\" p " +
                "JOIN \"public\".\"Debt\" d ON p.debt_id = d.id " +
                "JOIN \"public\".\"Client\" c ON d.client_id = c.id" +
                "where id = %d", idDebt);
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

    public List<Payment> selectAllByDebtId(int idDebt) {
        List<Payment> list = new ArrayList<>();
        String req = String.format("SELECT p.id, p.date, p.amount, " +
             "d.id AS debt_id, d.mount AS debt_mount, d.date AS debt_date, " +
             "d.amount_paid AS debt_paidMount, d.remaining_amount AS debt_remainingMount, d.is_achievied AS debt_isAchieved, " +
             "c.id AS client_id, c.surname AS client_surname, c.telephone AS client_telephone, c.adresse AS client_adresse, c.date AS client_createAt " +
             "FROM \"public\".\"Payment\" p " +
             "JOIN \"public\".\"Debt\" d ON p.debt_id = d.id " +
             "JOIN \"public\".\"Client\" c ON d.client_id = c.id" + 
             "where id = %d", idDebt);
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
    public Payment convertToObject(ResultSet rs) {
        Payment payment = new Payment();
        try {
            payment.setId(rs.getInt("id"));
            payment.setDate(rs.getDate("date"));
            payment.setAmount(rs.getDouble("amount"));

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

                // Mapping du client associé à la dette
                int clientId = rs.getInt("client_id");
                if (clientId != 0) {
                    Client client = new Client();
                    client.setId(clientId);
                    client.setSurname(rs.getString("client_surname"));
                    client.setTelephone(rs.getString("client_telephone"));
                    client.setAdresse(rs.getString("client_adresse"));
                    client.setCreateAt(rs.getTimestamp("client_createAt"));
                    debt.setClient(client);
                }

                payment.setDebt(debt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

}
