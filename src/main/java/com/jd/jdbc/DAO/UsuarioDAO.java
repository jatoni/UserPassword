package com.jd.jdbc.DAO;

import java.sql.*;
import java.util.*;

import com.jd.jdbc.entity.Usuario;
import static com.jd.jdbc.helpers.Conexion.*;

public class UsuarioDAO {
	private static final String SQL_SELECT = "SELECT * FROM usuario";
	private static final String SQL_INSERT = "INSERT INTO usuario (usuario, password) VALUES (?, ?)";
	private static final String SQL_UPDATE_NAME = "UPDATE usuario SET usuario = ? WHERE usuario = ?";
	private static final String SQL_UPDATE_PASSWORD = "UPDATE usuario SET password = ? WHERE password = ?";
	private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

	public List<Usuario> getAllUsers() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("usuario"), rs.getString("password"));
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close(stmt);
				Close(rs);
				Close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuarios;
	}

	public int CreateUser(Usuario usuario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int creados = 0;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getPassword());
			creados = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close(stmt);
				Close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return creados;
	}

	public int UpdateUserName(String userCurrent, String newUser) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int actualizados = 0;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_NAME);
			stmt.setString(1, newUser);
			stmt.setString(2, userCurrent);
			actualizados = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close(conn);
				Close(stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}

	public int UpdateUserPassword(String passwordCurrent, String newPassword) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int actulizados = 0;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_PASSWORD);
			stmt.setString(1, newPassword);
			stmt.setString(2, passwordCurrent);
			actulizados = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close(conn);
				Close(stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actulizados;
	}

	public int DeleteUser(Usuario user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int eliminados = 0;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, user.getId_usuario());
			eliminados = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close(conn);
				Close(stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eliminados;
	}

}
