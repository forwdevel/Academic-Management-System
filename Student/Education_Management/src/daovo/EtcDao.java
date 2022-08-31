package daovo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import ui.Alert;

public class EtcDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##user";
	String password = "user1234";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	// Inquiry college & major
	public int inquiry(String college, String major) {
		try {
			connDB();
			
			String query = "select college from major where college = '" + college + "'";
		
			rs = stmt.executeQuery(query);
			System.out.println("college : " + query);
			rs.last();
			
			if(rs.getRow() == 0) {
				new Alert("존재하지 않는 단과입니다.");
				return -1;
			} else {
				query = "select * from major where college = '" + college + "' and major = '"+major+"'";
				
				rs = stmt.executeQuery(query);
				System.out.println("major : " + query);
				rs.last();
				
				if(rs.getRow() == 0) {
					new Alert("존재하지 않는 전공입니다.");
					return -2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
		
		return 0;
	}
	
	// Return major
	public String returnMajor(String major) {
		String result = "";
		try {
			connDB();
			
			String query = "select * from major where major = '" + major + "'";
			System.out.println("returnMajor SQL : " +query);
			rs = stmt.executeQuery(query);
			rs.last();
			
			System.out.println("getRow() : " + rs.getRow());
			
			if(rs.getRow() != 0) {
				result = rs.getString("id");
				System.out.println("result : " + result);
			}
		}catch (Exception e) {
			
		}
		return result;
	}
	
	// return selected lecture list
	public Object[][] registerLecInquery(String query) {
		try {
			connDB();
			rs = stmt.executeQuery(query);
			rs.last();
			int n = rs.getRow();
			System.out.println("row : " + n);
			int i = 0;
			
			Object[][] object = new Object[n][8];
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				object[i][0] = rs.getString("id");
				object[i][1] = rs.getString("com");
				object[i][2] = rs.getString("name");
				object[i][3] = rs.getString("professor");
				object[i][4] = rs.getInt("credit");
				object[i][5] = rs.getString("room");
				object[i][6] = rs.getString("limit");
				object[i][7] = rs.getString("current");
				i++;
			}
			
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object[][] temp = {};
		return temp;
	}
	
	// register selected lecture to interest
	public int registerInterest(String stu_id, String lec_id) {
		try {
			connDB();
			
			// add credits of student
			// 1. find student
			String query = "select * from student where id = '" + stu_id + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			
			int stu_credit = rs.getInt("credit");
			String stu_name = rs.getString("name");
			
			// 2. find lecture credit
			query = "select * from lecture where id = '" + lec_id + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			
			String lec_name = rs.getString("name");
			stu_credit += rs.getInt("credit");
			System.out.println("Credit : " + stu_credit);
			
			if(stu_credit > 18) {
				new Alert("최대 학점을 초과하였습니다.");
			} else {
				query = "update student set credit = " + stu_credit;
				rs = stmt.executeQuery(query);
				
				query = "select * from \"REGISTER\" where stuid = '" + stu_id + "' and lecid = '" + lec_id + "'";
				rs = stmt.executeQuery(query);
				System.out.println("inquiry : " + query);
				rs.last();
				
				if(rs.getRow() != 0) {
					System.out.println("rs.row() == " + rs.getRow());
					new Alert("이미 존재하는 과목입니다.");
					query = "select * from lecture where id = '" + lec_id + "'";
					rs = stmt.executeQuery(query);
					stu_credit -= rs.getInt("credit");
					return stu_credit;
				}
				
				query = "select * from lecture where id = '" + lec_id + "'";
				rs = stmt.executeQuery(query);
				rs.last();
				System.out.println("sql : " + query);
				
				if(rs.getString("\"limit\"") != null && rs.getString("\"limit\"").equals(rs.getInt("\"current\""))) {
					new Alert("수강인원이 다 찼습니다.");
				} else {
					query = "update lecture set \"CURRENT\" = " + (rs.getInt("\"CURRENT\"") + 1) + " where id = '" + lec_id + "'";
					System.out.println("increase : " + query);
					rs = stmt.executeQuery(query);
					
					query = "insert into \"REGISTER\" values ('"+stu_id+"','"+lec_id+ "', '" + stu_name +"', '" + lec_name + "', '')";
					System.out.println("insert : " + query);
					rs = stmt.executeQuery(query);
				}
				
				new Alert("등록 하였습니다.");
				return stu_credit;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		System.out.println("success");
		return 0;
	}
	
	// return all inserest lecture list
	public Object[][] interestInquiry(String stu_id) {
		try {
			connDB();
			
			String query = "select lecid from register where stuid = '" + stu_id + "'";
			rs = stmt.executeQuery(query);
			
			List<String> lecIdTemp = new ArrayList<String>();
			
			while(rs.next()) {
				lecIdTemp.add(rs.getString("lecid"));
			}
			
			Object[][] object = new Object[lecIdTemp.size()][8];

			for(int i = 0; i < lecIdTemp.size(); i++) {
				query = "select * from lecture where id = '" + lecIdTemp.get(i) + "'";
				rs = stmt.executeQuery(query);
				rs.last();
				
				object[i][0] = rs.getString("id");
				object[i][1] = rs.getString("com");
				object[i][2] = rs.getString("name");
				object[i][3] = rs.getString("professor");
				object[i][4] = rs.getString("credit");
				object[i][5] = rs.getString("room");
			}
			return object;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object[][] temp = {};
		return temp;
	}
	
	// remove selected lecture at "RESISTER"
	public int removeInterest(String stu_id, String lec_id) {
		try {
			connDB();
			
			String query = "delete from register where stuid = '" + stu_id + "' and lecid = '" + lec_id + "'";
			rs = stmt.executeQuery(query);
			System.out.println("removeInterest query 1 : " + query);
			
			// find student credit
			query = "select * from student where id = '" + stu_id + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			
			int stu_credit = rs.getInt("credit");
			
			query = "select * from lecture where id = '" + lec_id + "'";
			rs = stmt.executeQuery(query);
			System.out.println("removeInterest query 2 : " + query);
			rs.last();
			
			stu_credit -= rs.getInt("credit");
			System.out.println(stu_credit);
			
			query = "update student set credit = " + stu_credit + " where id = '" + stu_id + "'";
			System.out.println("credit change SQL : " + query);
			rs = stmt.executeQuery(query);
			
			query = "select * from lecture where id = '" + lec_id + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			
			query = "update lecture set \"CURRENT\" = " + (rs.getInt("\"CURRENT\"") - 1) + " where id = '" + lec_id + "'";
			System.out.println("removeInterest query 3 : " + query);
			rs = stmt.executeQuery(query);
			
			query = "update student set credit = " + stu_credit;
			System.out.println(query);
			rs = stmt.executeQuery(query);
			return stu_credit;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// return grade
	// have to implement
	public Object[][] returnGrade(String stu_id) {
		try {
			connDB();
			
			String query = "select * from register where stuid = '" + stu_id + "'";
			System.out.println(query);
			rs = stmt.executeQuery(query);
			rs.last();
			int n = rs.getRow();
			int i = 0;
			
			Object[][] object = new Object[n][3];
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				object[i][0] = rs.getString("lecid");
				object[i][1] = rs.getString("lecname");
				object[i][2] = rs.getString("grade");
				i++;
			}
			
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object[][] object = {};
		return object;
	}
	
	// change password
	public void changePassword(String id, String pw) {
		try {
			connDB();
			
			String query = "update member set pw = '" + pw + "' where id = '" + id +"'";
			rs = stmt.executeQuery(query);
			
			System.out.println("success");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// return all lecture
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
				object[i][3] = rs.getInt("credit");
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
	
	// set enter to "scheduler"
	public void setEnter(boolean b) {
		try {
			connDB();
			
			int n = 0;
			
			if (b) {
				n = 1;
			}
			
			String query = "update scheduler set enter = " + n;
			System.out.println(query);
			rs = stmt.executeQuery(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// get enter to "scheduler"
	public boolean getEnter() {
		boolean b = false;
		try {
			connDB();
			
			String query = "select * from scheduler";
			System.out.println(query);
			rs = stmt.executeQuery(query);
			rs.last();

			int n = rs.getInt("enter");
			
			if(n == 1) {
				b = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	// set inquiry to "scheduler"
	public void setInquiry(boolean b) {
		try {
			connDB();
			
			int n = 0;
			
			if (b) {
				n = 1;
			}
			
			String query = "update scheduler set inquiry = " + n;
			System.out.println(query);
			rs = stmt.executeQuery(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// get inquiry to "scheduler"
	public boolean getInquiry() {
		boolean b = false;
		try {
			connDB();
			
			String query = "select * from scheduler";
			System.out.println(query);
			rs = stmt.executeQuery(query);
			rs.last();

			int n = rs.getInt("inquiry");
			
			if(n == 1) {
				b = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// connect to DB
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