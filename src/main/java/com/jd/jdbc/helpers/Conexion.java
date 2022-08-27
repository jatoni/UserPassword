package com.jd.jdbc.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private static final String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private static final String user = "root";
	private static final String password = "admin";
	private static Connection conexion;
	
	public static Connection getConnection()throws SQLException{
		conexion = DriverManager.getConnection(url, user, password);
		return conexion;
	}
	
	public static void Close(Statement stmt) throws SQLException {
		stmt.close();
	}
	public static void Close(ResultSet rs) throws SQLException {
		rs.close();
	}
	public static void Close(PreparedStatement stmtm) throws SQLException {
		stmtm.close();
	}
	public static void Close(Connection conexion) throws SQLException {
		conexion.close();
	}
}
