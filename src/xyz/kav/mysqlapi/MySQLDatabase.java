package xyz.kav.mysqlapi;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MySQLDatabase {
	public static Connection connection;
	public static String host, database, username, password;

	public MySQLDatabase(String host, String database, String username, String password) {
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
		connect();

	}

	public static void connect() {
		try {
			connection = (Connection) DriverManager
					.getConnection("jdbc:mysql://" + host + "/" + database + "?autoReconnect=true", username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// System.out.println(sql.query("SELECT password FROM testing WHERE username
	// = 'kaveen'", "password"));
	public static String query(String conditioncolumn, String conditionanswer, String select, String table) {
		return query("SELECT " + select + " FROM " + table + " WHERE " + conditioncolumn + " = " + "'" + conditionanswer
				+ "'", select);

	}
    public static boolean contain(String conditioncolumn, String conditionanswer, String select, String table) {
    	return contain("SELECT " + select + " FROM " + table + " WHERE " + conditioncolumn + " = " + "'" + conditionanswer
				+ "'");
    	
    	
    	
    }
    
    public static void execute(String query) {

		String alt = "";
		try {
			
//stmt	.execute("INSERT INTO `verified`(`isverified`) VALUES ('"+username12+"')");
			Statement stmt = connection.createStatement();
			stmt.execute(query);
			
		} catch (Exception e) {
	e.printStackTrace();

			
		}
		

	}
    public static void insertDouble(String tablename, String column1, String column2, String value1, String value2) {
    	execute("INSERT INTO `"+tablename+"`(`"+column1+"`,`"+column2+"`) VALUES ('"+value1+"','"+value2+"')");
    	
    }
    public static void insertTriple(String tablename, String column1, String column2, String column3, String value1, String value2, String value3) {
    	execute("INSERT INTO `"+tablename+"`(`"+column1+"`,`"+column2+"`,`"+column3+"`) VALUES ('"+value1+"','"+value2+"','"+value3+"')");
    	
    }
    public static void insertSingle(String tablename, String columntoinsert,String value) {
    	execute("INSERT INTO `"+tablename+"`(`"+columntoinsert+"`) VALUES ('"+value+"')");
    	
    }
	public static boolean contain(String query) {

		
		try {
			

			Statement stmt = connection.createStatement();
			ResultSet set = stmt
					.executeQuery(query);
			if (!set.isBeforeFirst()) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
		
e.printStackTrace();
			return false;
		}
		

	}

	
	public static String query(String query, String selector) {

		String alt = "";
		try {

			Statement stmt = connection.createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			String pass;
			if (resultset.next()) {

				pass = resultset.getString(selector);

				return pass;
			} else {

				return "null";
			}
		} catch (Exception e) {
			e.printStackTrace();

			return "null";
		}
	}

}
