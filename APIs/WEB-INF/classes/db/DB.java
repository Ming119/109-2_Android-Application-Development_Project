package db;

import java.sql.*;

public class DB {
	
	private final String jdbcName = System.getenv("dbDriver");
	private final String database = System.getenv("dbConnectionUrl2");
	private final String username = System.getenv("dbUsername");
	private final String password = System.getenv("dbPassword");
	
	private Connection connection;

	public DB() {
		connection = null;
	}

	public void connect() {
		try {
			Class.forName(jdbcName);
			connection = DriverManager.getConnection(database, username, password);
			if (connection != null)
				System.out.println("Connect successfully!");
			else
				System.out.println("Connect failed!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	public boolean CreateUser(String uid, String name, String pw) {
		connect();
		
		try {
			String sql = "INSERT INTO users (uid, name, password) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, uid);
			statement.setString(2, name);
			statement.setString(3, pw);
			
			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Insert User Success!");
				return true;
			}
			
			System.out.println("Insert User Failed!");
			
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
	
	public ResultSet ReadUsers() {
		connect();
		
		ResultSet resultSet = null;
		try {
			String sql = "SELECT * FROM users";
			PreparedStatement statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}

		return resultSet;
	}
	
	public ResultSet ReadUser(String uid) {
		connect();
		
		ResultSet resultSet = null;
		try {
			String sql = "SELECT * FROM users WHERE uid='"+uid+"';";
			PreparedStatement statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}

		return resultSet;
	}
	
	public boolean UpdateUser(String uid, String name, String pw) {
		connect();

		try {
			String sql = "UPDATE users SET name=?, password=? WHERE uid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, pw);
			statement.setString(3, uid);

			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Update User Success!");
				return true;
			}
			
			System.out.println("Update User Failed!");

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
	
	public boolean DeleteUser(String uid) {
		connect();

		try {
			String sql = "DELETE FROM users WHERE uid=?";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, uid);

			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Delete User Success");
				return true;
			}
			
			System.out.println("Delete User Failed!");
			
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
	
	
	
	public boolean CreateItem(String title, String desc, int price, String uid) {
		connect();
		
		try {
			String sql = "INSERT INTO books (title, description, price, seller, sold) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, title);
			statement.setString(2, desc);
			statement.setInt(3, price);
			statement.setString(4, uid);
			statement.setBoolean(5, false);
			
			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Insert Book Success!");
				return true;
			}
			
			System.out.println("Insert Book Failed!");
			
		} catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
	
	public ResultSet ReadItems() {
		connect();
		ResultSet resultSet = null;
		
		try {
			String sql = "SELECT * FROM books";
			PreparedStatement statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
		} catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return resultSet;
	}
	
	public ResultSet ReadItem(int id) {
		connect();
		ResultSet resultSet = null;
		
		try {
			String sql = "SELECT * FROM books WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
		} catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return resultSet;
	}
	
	public boolean UpdateItem(int id, String title, String desc, int price) {
		connect();
		
		try {
			String sql = "UPDATE books SET title=?, description=?, price=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, title);
			statement.setString(2, desc);
			statement.setInt(3, price);
			statement.setInt(4, id);

			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Update Book Success!");
				return true;
			}
			
			System.out.println("Update Book Failed!");

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
	
	public boolean ItemSold(int id) {
		connect();
		
		try {
			String sql = "UPDATE books SET sold=true WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Book sold!");
				return true;
			}

			System.out.println("Fail to sell the book!");

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
	
	public boolean DeleteItem(int id) {
		connect();
		
		try {
			String sql = "DELETE FROM books WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);

			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Delete Book Success");
				return true;
			}
			
			System.out.println("Delete Book Failed!");
			
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
	
}
