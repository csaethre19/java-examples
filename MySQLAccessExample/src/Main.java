
public class Main {

	public static void main (String[] args) {
		MySQLAccess db = new MySQLAccess();
		db.connectToDB();
		try {
			db.readOwners();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
	}
}
