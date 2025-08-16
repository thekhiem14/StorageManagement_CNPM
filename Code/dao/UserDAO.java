package dao;

import model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO {
    public UserDAO() {
        super();
    }

    public boolean checkLogin(User u) {
        boolean check = false;
        String sql = "SELECT fullname, role FROM tbluser WHERE username = ? AND password = ?";
        try{
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, u.getUsername());
            pr.setString(2, u.getPassword());
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                u.setFullname(rs.getString("fullname"));
                u.setRole(rs.getString("role"));
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
