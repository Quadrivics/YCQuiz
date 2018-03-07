package quizpack;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Maakt verbinding met een SQLite database en maakt query's mogelijk.
 */
public class Database {
	Console cnsl = new Console();
	ResultSet rs = null;
	Connection connection = null;
	Statement stmt = null;

	/**
	 * Maakt verbinding met SQLite database via JDBC driver om later informatie op
	 * te kunnen halen voor de quiz. De driver is opgenomen in de Referenced
	 * Libraries als sqlite-jdbc-3.21.0.jar.
	 * @return Connection connection
	 */
	public Connection connect() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:./data/data.db");
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			cnsl.println("connect() error: " + e.getMessage());
		}
		return connection;
	}

	/**
	 * Sluit interfaces binnen de Database class om resourceleaks te voorkomen.
	 */
	public void disconnect() {
		try {
			this.rs.close();
			this.stmt.close();
			this.connection.close();
		} catch (Exception SQLException) {
			cnsl.println("disconnect() error: " + SQLException.getMessage());
		}
	}

	/**
	 * Maakt een query voor de database op basis van een parameter. 
	 * @param String query
	 * @return ResultSet rs
	 */
	public ResultSet buildQuery(String query) {

		try {
			stmt = connect().createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception SQLException) {
			cnsl.println("buildQuery() error: " + SQLException.getMessage());
		}
		return rs;
	}
}
