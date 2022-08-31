package daovo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentVo {
	private String id;
	private String name;
	private String college;
	private String major;
	private int grade;
	private int enroll;
	private int credit;
	
	public StudentVo() {
		
	}

	public StudentVo(String id, String name, String college, String major, int grade, int enroll, int credit) {
		super();
		this.id = id;
		this.name = name;
		this.college = college;
		this.major = major;
		this.grade = grade;
		this.enroll = enroll;
		this.credit = credit;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCollege() {
		return college;
	}

	public String getMajor() {
		return major;
	}

	public int getGrade() {
		return grade;
	}

	public int getEnroll() {
		return enroll;
	}

	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
}