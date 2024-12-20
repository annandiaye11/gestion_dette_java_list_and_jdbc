package com.baf.data.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baf.core.database.impl.DatabaseImpl;
import com.baf.core.enums.Role;
import com.baf.data.entities.User;
import com.baf.data.repositories.UserRepository;

public class UserRepositoryImplDB extends DatabaseImpl implements UserRepository {

    @Override
    public void insert(User data) {
        String req = String.format("INSERT INTO \"public\".\"User\" (email, login,  password, role, is_active) VALUES ('%s', '%s', '%s', '%s', '%s')",
                data.getEmail(), data.getLogin(), data.getPassword(), data.getRole().toString(), data.getIsActive());
        try {
            this.initPreparedStatement(req);
            this.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> selectAll() {
        String req = "SELECT * FROM \"public\".\"User\"";
        List<User> list = new ArrayList<>();
        try {
            this.initPreparedStatement(req);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                list.add(this.convertToObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User convertToObject(ResultSet rs) {
        User user = new User();
        try {
            System.out.println("test");

            System.out.println(rs.toString());
            user.setIdUser(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setIsActive(rs.getBoolean("is_active"));
            String str_role = rs.getString("role");
            switch (str_role) {
                case "ADMIN":
                    user.setRole(Role.ADMIN);
                    break;
                case "BOUTIQUIER":
                    user.setRole(Role.BOUTIQUIER);
                    break;
                case "CLIENT":
                    user.setRole(Role.CLIENT);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public void toggleUser(User user) {
        String req = String.format("UPDATE \"public\".\"User\" SET role = '%s' WHERE id = %d", user.getRole().toString(), user.getIdUser());
        try {
            this.initPreparedStatement(req);
            this.ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int userId) {
        String req = String.format("SELECT * FROM \"public\".\"User\" WHERE id = %d", userId);
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
    public User selectByLogin(String login) {
        String req = String.format("SELECT * FROM \"public\".\"User\" WHERE login = '%s'", login);
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
    public User selectByMail(String mail) {
        String req = String.format("SELECT * FROM \"public\".\"User\" WHERE email = '%s'", mail);
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
