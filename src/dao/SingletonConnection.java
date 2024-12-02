package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SingletonConnection {
	private static Connection connection;
	private SingletonConnection() throws Exception {
			
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost/scolarite","root","");
			
			}
	public static Connection getInstance()throws Exception{
		
		if (connection==null) new SingletonConnection();
		return connection;
				
	}
		}
	


