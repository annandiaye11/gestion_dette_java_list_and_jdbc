package com.baf.core.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.baf.core.database.Database;



public class DatabaseImpl implements Database {
    protected Connection conn = null;
    protected PreparedStatement ps;
    protected String dbName = "gestion_dette_test";
    protected String userName = "app";
    protected String userPassword = "root";

    @Override
    public void getConnection() throws SQLException {
        try {
            // Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, userName, userPassword);
            System.out.println("Connection to database " + dbName + " successful: "+ conn);
        }catch (SQLException e) {
            System.out.println("Connection to database " + dbName + " failed");
            e.printStackTrace();

        }
    }
    @Override
    public void closeConnection() throws SQLException {
        if (conn != null || !conn.isClosed()) {
            conn.close();
        }
    }

    @Override
    public ResultSet executeSelect(String req) throws SQLException {
        try {
            return this.ps.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }
    
    @Override
    public void initPreparedStatement(String req) throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            getConnection();
        }
        if (req.trim().toUpperCase().startsWith("SELECT")) {
            ps = conn.prepareStatement(req);
        } else {
            ps = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        }
    }

    @Override
    public void update(String req) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(req);
    }



}