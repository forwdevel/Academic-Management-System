package daovo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import ui.Alert;
import ui.Professor_Main;

public class ProfessorDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##user";
	String password = "user1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ProfessorVo pro(String id_txt) {
		try {
			connDB();

			String query = "SELECT * FROM PROFESSOR";
			query += " where id = '" + id_txt + "'";
			System.out.println(query);

			rs = stmt.executeQuery(query);
			rs.last();

			System.out.println(rs.getRow());

			if (rs.getRow() == 0) {
				// Empty
				System.out.println(":: Not Found ::");
			} else {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String college = rs.getString("college");
				String major = rs.getString("major");
				int enroll = rs.getInt("enroll");

				ProfessorVo data = new ProfessorVo(id, name, college, major, enroll);
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ProfessorVo();
	}

	public boolean enroll_pro(String name, String college, String major, int enroll) {
		try {
			String id = "";
			connDB();

			String query = "select * from professor where major = '" + major + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("inquiry SQL : " + query);
			System.out.println(major + "'s professor : " + rs.getRow());

			id = new EtcDao().returnMajor(major);

			if ((rs.getRow() + 1) < 10) {
				id += "0" + (rs.getRow() + 1);
			} else if (rs.getRow() < 100) {
				id += (rs.getRow() + 1);
			} else {
				new Alert("100명까지 등록 가능합니다.");
				return false;
			}

			query = "insert into professor values('" + id + "', '" + name + "', '" + college + "', '" + major + "',"
					+ enroll + ")";

			rs = stmt.executeQuery(query);
			System.out.println("Insert Into Professor SQL : " + query);

			query = "insert into member values('" + id + "', '" + name + "', '교수', '1234')";

			rs = stmt.executeQuery(query);
			System.out.println("Insert Into Member SQL : " + query);

		} catch (Exception e) {
			new Alert("등록에 실패하였습니다(SQL).");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Object[][] allProfessor() {
		try {
			connDB();

			String query = "select * from professor order by id";
			System.out.println("\nSQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();

			int n = rs.getRow();
			int i = 0;

			Object[][] object = new Object[n][4];

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				object[i][0] = rs.getString("id");
				object[i][1] = rs.getString("name");
				object[i][2] = rs.getString("college");
				object[i][3] = rs.getString("major");
				i++;
			}

			return object;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Object[][] temp = {};
		return temp;
	}

	public void enterLecture(ProfessorVo vo, String name, String com, String credit, String room, String limit,
			String current) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		String semester = "";

		// 1�썡 == 0 / 12�썡 == 11
		if (month < 3) {
			// 1, 2, 3
			// 1�븰湲� 怨쇰ぉ �벑濡앷린媛�
			semester = "1";
		} else if (month >= 3 && month < 6) {
			// 4, 5, 6
			// �뿬由꾧퀎�젅 怨쇰ぉ �벑濡앷린媛�
			semester = "여름계절";
		} else if (month >= 6 && month <= 8) {
			// 7, 8, 9
			// 2�븰湲� 怨쇰ぉ �벑濡앷린媛�
			semester = "2";
		} else {
			// 10, 11, 12
			// 寃⑥슱怨꾩젅 怨쇰ぉ �벑濡앷린媛�
			semester = "겨울계절";
		}

		if (semester.length() == 0) {
			// null
			System.out.println("semester is null");
			return;
		}

		if (limit == null) {
			limit = "";
		}

		try {
			connDB();

			// �븰�닔踰덊샇 �옉�뾽

			// 1. 援먯닔�쓽 �쟾怨듦낵 major �뀒�씠釉붿쓽 �쟾怨듭씠 �씪移섑븯�뒗 肄붾뱶瑜� 李얘린
			// 援먯닔�쓽 �쟾怨�
			String major = vo.getMajor();
			String query = "select * from major where major = '" + major + "'";
			System.out.println("SQL for finding major : " + query);
			rs = stmt.executeQuery(query);
			rs.last();

			// �븰�닔踰덊샇 肄붾뱶
			String code = rs.getString("code");
			System.out.println("Major Code : " + code);

			// 2. 怨쇰ぉ�씠由꾧낵 肄붾뱶媛� 媛숈쑝硫� �룞�씪 怨쇰ぉ�씠 �씠誘� 議댁옱�븳�떎�뒗 �삤瑜섎Ц 諛섑솚 諛� return
			// + year and semester
			query = "select * from lecture where major = '" + major + "' and name = '" + name + "' and year = '" + year
					+ "' and semester = '" + semester + "'";
			System.out.println("SQL for checking duplicate : " + query);
			rs = stmt.executeQuery(query);
			rs.last();

			if (rs.getRow() != 0) {
				new Alert("이미 존재하는 과목입니다.");
				return;
			}

			// 3. �닚�꽌��濡� 踰덊샇遺��뿬 000~999
			query = "select * from lecture where major = '" + major + "'";
			System.out.println("SQL for find all major lecture : " + query);
			rs = stmt.executeQuery(query);
			rs.last();

			if (rs.getRow() + 1 < 10) {
				code += "00" + (rs.getRow() + 1);
			} else if (rs.getRow() + 1 < 100) {
				code += "0" + (rs.getRow() + 1);
			} else if (rs.getRow() + 1 < 1000) {
				code += (rs.getRow() + 1);
			} else {
				// out of range
				System.out.println("out of range");
				new Alert("과목은 1000개만 입력 가능합니다.");
				return;
			}

			System.out.println("final code : " + code);

			query = "insert into lecture values ( '" + code + "', '" + com + "', '" + name + "', " + credit + ", '"
					+ room + "', " + year + ", '" + semester + "', '" + vo.getCollege() + "', '" + major + "', '"
					+ vo.getName() + "', '" + limit + "', " + current + ")";
			System.out.println("SQL for insert Lecture : " + query);
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		new Professor_Main(vo);
		new Alert("등록하였습니다.");

	}

	public Object[][] inquiryLectureForGrade(String pro_name) {
		try {
			connDB();

			String query = "select * from lecture where professor = '" + pro_name + "'";
			System.out.println("SQL for get lectures : " + query);

			rs = stmt.executeQuery(query);

			query = "select * from register where";

			while (rs.next()) {
				query += " lecid = '" + rs.getString("id") + "' or";
			}

			query = query.substring(0, query.length() - 2);
			System.out.println("SQL for get all student : " + query);

			rs = stmt.executeQuery(query);
			rs.last();

			int n = rs.getRow();
			int i = 0;
			
			System.out.println("getRow : " + n);

			Object[][] object = new Object[n][4];
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				object[i][0] = rs.getString("lecid");
				object[i][1] = rs.getString("stuid");
				object[i][2] = rs.getString("stuname");
				object[i][3] = rs.getString("grade");
				i++;
			}
			
			return object;

		} catch (Exception e) {
			e.printStackTrace();
		}

		Object[][] temp = {};
		return temp;
	}

	public void enterGrade(String lecid, String stuid, String stuname, String grade) {
		try {
			connDB();
			
				String query = "update register set grade = '" + grade + "' where lecid = '" + lecid + "' and stuid = '" + stuid + "' and stuname = '" + stuname + "'";
				System.out.println("SQL : " +query);
				rs = stmt.executeQuery(query);
				
		} catch(Exception e) {
			e.printStackTrace();
		}
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
