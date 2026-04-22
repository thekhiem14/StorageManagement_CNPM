package dao;

import view.user.LoginFrm;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	public static Connection con;
	
	public DAO(){
		if(con == null){
			String dbUrl = EnvConfig.get("DB_URL");
			String dbClass = EnvConfig.get("DB_DRIVER");
			String dbUsername = EnvConfig.get("DB_USERNAME");
			String dbPassword = EnvConfig.get("DB_PASSWORD");
			try {
				Class.forName(dbClass);
				con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
				System.out.println("Connected to database");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
