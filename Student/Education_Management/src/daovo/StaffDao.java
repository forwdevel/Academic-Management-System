package daovo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StaffDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##user";
	String password = "user1234";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public StaffVo stf(String id_txt, String pw_txt) {
		try {
			connDB();
			
			String query = "SELECT * FROM STAFF ";
			query += "where id = '" + id_txt + "' and pw = '" + pw_txt  + "'";
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println(rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected......");
			} else {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int enroll = rs.getInt("enroll");
				
				StaffVo vo = new StaffVo(id, name, enroll);
				return vo;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new StaffVo();
	}
	
	@SuppressWarnings("removal")
	public Object[][] allLecture() {
		try {
			connDB();
			
			String query = "select * from lecture";
			System.out.println(query + "\n");
			rs = stmt.executeQuery(query);
			rs.last();
			
			int n = rs.getRow();
			int i = 0;
			
			Object[][] object = new Object[n][6];

			rs = stmt.executeQuery(query);
			while(rs.next()) {
				object[i][0] = rs.getString("id");
				object[i][1] = rs.getString("com");
				object[i][2] = rs.getString("name");
				object[i][3] = new Integer(rs.getInt("credit"));
				object[i][4] = rs.getString("major");
				object[i][5] = rs.getString("professor");
				i++;
			}
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object[][] temp = {};
		return temp;
	}
	
	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
