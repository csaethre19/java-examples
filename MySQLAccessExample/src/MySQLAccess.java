import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	//private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	final private String host = "localhost:3306";
	final private String user = "root";
	final private String pw = "1550";
	final private String database = "alexamara";
	
	public void connectToDB() {
		try {
			// This will load the MySQL driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database, user, pw );
			System.out.println("DB connection success");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void readOwners() throws Exception {
		try {
			statement = (Statement) connect.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM " + database + ".owner");
			while (resultSet.next()) {
				String Id = resultSet.getString("OWNER_NUM");
				String name = resultSet.getString("FIRST_NAME");
				String city = resultSet.getString("CITY");
				
				System.out.println(String.format("Id: %5s Name: %-15s City: %5s", Id, name, city));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
