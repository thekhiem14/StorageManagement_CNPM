package dao;

import view.user.LoginFrm;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	public static Connection con;
	
	public DAO(){
		if(con == null){
			String dbUrl = "jdbc:mysql://127.0.0.1:3306/StoreManagement?useSSL=false&serverTimezone=UTC";
			String dbClass = "com.mysql.cj.jdbc.Driver";
			try {
				Class.forName(dbClass);
				con = DriverManager.getConnection (dbUrl, "root", "12345");
				System.out.println("Connected to database");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
