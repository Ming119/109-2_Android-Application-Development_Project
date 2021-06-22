package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			String sql = "SELECT * FROM books WHERE sold=false ORDER BY id DESC";
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
	
//	public boolean CreateChatRecord(String from, String to, String message, int id)
	public boolean CreateChatRecord(String from, String to, String message) {
		connect();
		
		try {
			String sql = "INSERT INTO chatRecord (fromUID, toUID, message, id) VALUES (?, ?, ?, ?)";	// id not in uses;
//			String sql = "INSERT INTO chatRecord (fromUID, toUID, message, id) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, from);
			statement.setString(2, to);
			statement.setString(3, message);
//			statement.setInt(4, id);
			statement.setInt(4, 0);
			
			System.out.println(statement.toString());
			
			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Insert Chat Record Success!");
				return true;
			}
			
			System.out.println("Insert Chat Record Failed!");
			
		} catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
	
	public ResultSet ReadChatRecords() {
		connect();
		
		ResultSet resultSet = null;
		
		try {
			String sql = "SELECT * FROM chatRecord";
			PreparedStatement statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
		} catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return resultSet;
	}
	
//	public ResultSet ReadChatRecord(String from, String to, int id)
	public ResultSet ReadChatRecord(String from, String to) {
		connect();
		ResultSet resultSet = null;
		
		try {
			String sql = "SELECT * FROM chatRecord WHERE (fromUID=? AND toUID=?) OR (fromUID=? AND toUID=?)";
//			String sql = "SELECT * FROM chatRecord WHERE ((fromUID=? AND toUID=?) OR (fromUID=? AND toUID=?)) AND id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, from);
			statement.setString(2, to);
			statement.setString(3, to);
			statement.setString(4, from);
//			statement.setInt(5, id);
			
			resultSet = statement.executeQuery();
			
		} catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return resultSet;
	}
	
	
	
	public ResultSet GetLastChat(String uid) {
		connect();
		ResultSet resultSet = null;
		
		try {
			String sql = "SELECT t1.* FROM chatRecord t1 LEFT JOIN chatRecord t2 ON ((t2.toUID=? OR t2.fromUID=?) AND t1.toUID=t2.toUID AND t1.crid<t2.crid) WHERE t2.crid IS NULL AND (t1.fromUID=? OR t1.toUID=?) ORDER BY crid DESC LIMIT 1";
//			String sql = "SELECT fromUID, toUID, id FROM chatRecord WHERE (fromUID=? OR toUID=?) GROUP BY id";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, uid);
			statement.setString(2, uid);
			statement.setString(3, uid);
			statement.setString(4, uid);
			
			resultSet = statement.executeQuery();
			
		} catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return resultSet;
	}
	
	public boolean DeleteChatRecord(int crid) {
		connect();
		
		try {
			String sql = "DELETE FROM chatRecord WHERE crid=?";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, crid);

			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("Delete Chat Record Success");
				return true;
			}
			
			System.out.println("Delete Chat Record Failed!");
			
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		return false;
	}
}
