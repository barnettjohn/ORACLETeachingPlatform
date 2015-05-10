package com.plat.orcl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	private static String classname;
	private static String url;
	private static String username;
	private static String password;
	static {
		try {
		
			InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("dbcfg.properties");
			Properties properties = new Properties();
			properties.load(in);
			
			classname = properties.getProperty("classname");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			Class.forName(classname);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static Connection getConn() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}

	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stmt!=null){
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	} 
}
