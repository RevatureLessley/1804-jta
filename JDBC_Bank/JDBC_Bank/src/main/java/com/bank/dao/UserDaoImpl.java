package com.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.model.Product;
import com.bank.model.User;
import com.bank.util.ConnectionUtil;

public class UserDaoImpl implements UserDao{

	private static UserDaoImpl instance;
	
	private UserDaoImpl() {}
	
	public static UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_table ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getString("user_firstname"), rs.getString("user_lastname"), rs.getInt("user_approved"), rs.getInt("user_locked")));
			}
			return users;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public User getUser(String firstname, String lastname) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_table WHERE user_firstname = ? AND user_lastname = ?");
			stmt.setString(++index, firstname);
			stmt.setString(++index, lastname);
			ResultSet rs = stmt.executeQuery();
			// If can be used if just selecting one entry
			if (rs.next()) {
				return new User(rs.getString("user_firstname"), rs.getString("user_lastname"), 
						rs.getString("user_password"), rs.getInt("user_approved"), rs.getInt("user_locked"));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_user(?,?,?,?,?)}");
			stmt.setString(++index, user.getFirst_name());
			stmt.setString(++index, user.getLast_name());
			stmt.setString(++index, user.getPassword());
			stmt.setInt(++index, user.getApproved());
			stmt.setInt(++index, user.getLocked());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public String getPasswordHash(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?, ?) AS HASH FROM dual");
			stmt.setString(++index, user.getFirst_name());
			stmt.setString(++index, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	public int getUserId(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT user_id FROM user_table WHERE user_firstname = ?");
			stmt.setString(++index, user.getFirst_name());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("user_id");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return 0;
		}
		
		public int getProductId(Product product) {
			int index = 0;
			try (Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement("SELECT product_id FROM product WHERE product = ?");
				stmt.setString(++index, product.getProduct_name());
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return rs.getInt("product_id");
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			return 0;
			}

		public int getApproved(User user) {
			int index = 0;
			try (Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement("SELECT user_approved FROM user_table WHERE user_id = ? ");
				stmt.setInt(++index, user.getId());
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return rs.getInt("user_approved");
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			return 0;
		}

		@Override
		public int getLocked(User user) {
			int index = 0;
			try (Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement("SELECT user_locked FROM user_table WHERE user_id = ? ");
				stmt.setInt(++index, user.getId());
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return rs.getInt("user_locked");
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			return 0;
		}

		@Override
		public boolean approveAccount(User user) {
			int index = 0;
			try (Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement("Update user_table SET user_approved = 1 WHERE user_id = ?");
				stmt.setInt(++index, user.getId());
				return stmt.executeUpdate() > 0;
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			return false;
		}

		@Override
		public boolean lockAccount(User user) {
			int index = 0;
			try (Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement("Update user_table SET user_locked = 1 WHERE user_id = ? ");
				stmt.setInt(++index, user.getId());
				return stmt.executeUpdate() > 0;
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			return false;
		}

		@Override
		public boolean unlockAccount(User user) {
			int index = 0;
			try (Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement("Update user_table SET user_locked = 0 WHERE user_id = ? ");
				stmt.setInt(++index, user.getId());
				return stmt.executeUpdate() > 0;
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			return false;
		}
	
}
